package com.su0h.Chores.services;

import com.su0h.Chores.entities.Metadata;
import com.su0h.Chores.entities.Task;
import com.su0h.Chores.entities.TaskAssignment;
import com.su0h.Chores.entities.TaskAssignmentResponse;
import com.su0h.Chores.repositories.MetadataRepository;
import com.su0h.Chores.repositories.TaskAssignmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableScheduling
public class TaskAssignmentService {
    private final TaskAssignmentRepository taskAssignmentRepository;
    private final MetadataRepository metadataRepository;
    private final MetadataService metadataService;

    private final DateService dateService;

    public TaskAssignmentService(
            TaskAssignmentRepository taskAssignmentRepository,
            MetadataRepository metadataRepository,
            MetadataService metadataService,
            DateService dateService
    ) {
        this.taskAssignmentRepository = taskAssignmentRepository;
        this.metadataRepository = metadataRepository;
        this.metadataService = metadataService;
        this.dateService = dateService;
    }

    public ResponseEntity<TaskAssignmentResponse> fetchAllTaskAssignments() {
        List<TaskAssignment> taskAssignments = taskAssignmentRepository.findAll();
        List<TaskAssignmentResponse.SimplifiedTaskAssignment> simplifiedTaskAssignments = new ArrayList<>();

        for (TaskAssignment taskAssignment : taskAssignments) {
            simplifiedTaskAssignments.add(new TaskAssignmentResponse.SimplifiedTaskAssignment(
                taskAssignment.getPerson().getName(),
                taskAssignment.getTask().getName()
            ));
        }

        LocalDateTime lastModified = metadataService.getLastModifiedDate();

        return ResponseEntity.ok(new TaskAssignmentResponse(
                lastModified,
                simplifiedTaskAssignments
        ));
    }

    // https://www.baeldung.com/spring-scheduled-tasks
    @Scheduled(cron = "* 0 0 * * *") // Runs every 12:00 AM
    private void performWeekdayScheduledShifting() {
        this.shiftTaskAssignments();
    }

    @Scheduled(cron = "* 0 17 * * *") // Runs every 5:00 PM
    private void performDoubleTaskScheduledShifting() {
        // Run only if today is a double task day (i.e., holiday, weekend)
        if (dateService.isDoubleTaskDay(LocalDate.now()))
            this.shiftTaskAssignments();
    }

    public ResponseEntity<String> shiftTaskAssignments() {
        // Save all task assignments
        List<TaskAssignment> taskAssignments = taskAssignmentRepository.findAll();

        // Empty table of task assignments
        // Note: ensure that cascade is not set to ALL
        taskAssignmentRepository.deleteAll();

        // Retrieve tasks stored in task assignments
        ArrayList<Task> tasks = new ArrayList<>();
        for (TaskAssignment taskAssignment : taskAssignments) {
            tasks.add(taskAssignment.getTask());
        }

        // Shift list of tasks
        this.shiftTasks(tasks, 1, false);

        // Update task assignments
        for (int i = 0; i < taskAssignments.size(); i++) {
            taskAssignments.get(i).setTask(tasks.get(i));
        }

        // Update Last Modified date
        metadataRepository.save(new Metadata(Metadata.Key.LAST_MODIFIED, LocalDateTime.now().toString()));

        // Save updated task assignments
        taskAssignmentRepository.saveAll(taskAssignments);

        return ResponseEntity.ok("Shifted successfully!");
    }

    // TODO: Try to merge with shiftTaskAssignments() (code duplication)
    public ResponseEntity<String> unshiftTaskAssignments() {
        LocalDate dateToday = LocalDate.now();
        LocalTime timeNow = LocalTime.now();
        LocalDate lastUnshifted = metadataService.getLastUnshifted();

        /*
            Unshift only if:
            (1) Today is a double task day
            (2) DB has not been unshifted today yet
            (3) It is after 5:00 PM already
         */
        if (dateService.isDoubleTaskDay(LocalDate.now()) &&
                !dateToday.isEqual(lastUnshifted) &&
                timeNow.isAfter(LocalTime.of(17, 0))
        ) {
            // Save all task assignments
            List<TaskAssignment> taskAssignments = taskAssignmentRepository.findAll();

            // Empty table of task assignments
            // Note: ensure that cascade is not set to ALL
            taskAssignmentRepository.deleteAll();

            // Retrieve tasks stored in task assignments
            ArrayList<Task> tasks = new ArrayList<>();
            for (TaskAssignment taskAssignment : taskAssignments) {
                tasks.add(taskAssignment.getTask());
            }

            // Unshift list of tasks
            this.shiftTasks(tasks, 1, true);

            // Update task assignments
            for (int i = 0; i < taskAssignments.size(); i++) {
                taskAssignments.get(i).setTask(tasks.get(i));
            }

            // Update Last Modified date
            metadataRepository.save(new Metadata(Metadata.Key.LAST_MODIFIED, LocalDateTime.now().toString()));
            metadataRepository.save(new Metadata(Metadata.Key.LAST_UNSHIFTED, LocalDate.now().toString()));

            // Save updated task assignments
            taskAssignmentRepository.saveAll(taskAssignments);

            return ResponseEntity.ok("Unshifted successfully!");
        }

        if (dateToday.isEqual(lastUnshifted))
            return ResponseEntity.ok("Unable to un-shift. Tasks for today have been shifted already!");
        else if (!timeNow.isAfter(LocalTime.of(17, 0)))
            return ResponseEntity.ok("Unable to un-shift. It is not 5:00 PM yet.");
        else
            return ResponseEntity.ok("Unable to un-shift. Today's not a double task day!");
    }

    private void shiftTasks(ArrayList<Task> tasks, int shiftAmount, boolean shiftRight) {
        for (int i = 0; i < shiftAmount; i++)
            if (shiftRight) {
                tasks.add(0, tasks.remove(tasks.size() - 1));
            } else {
                tasks.add(tasks.size() - 1, tasks.remove(0));
            }
    }
}
