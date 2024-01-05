package com.su0h.Chores.services;

import com.su0h.Chores.entities.Task;
import com.su0h.Chores.entities.TaskAssignment;
import com.su0h.Chores.repositories.TaskAssignmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class TaskAssignmentService {
    private final TaskAssignmentRepository taskAssignmentRepository;

    public TaskAssignmentService(TaskAssignmentRepository taskAssignmentRepository) {
        this.taskAssignmentRepository = taskAssignmentRepository;
    }

    public ResponseEntity<List<TaskAssignment>> fetchAllTaskAssignments() {
        return ResponseEntity.ok(taskAssignmentRepository.findAll());
    }

    public ResponseEntity<String> shiftTaskAssignments() {
        LocalDate dateToday = LocalDate.now();
        LocalDate lastUpdated = taskAssignmentRepository.getLastModifiedDate();

        // Checking if shifting needs to be done
        if (dateToday.isEqual(lastUpdated)) {
            return ResponseEntity.ok("The tasks have been shifted for today already.");
        }

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
        tasks.add(0, tasks.remove(tasks.size() - 1));

        // Update task assignments
        for (int i = 0; i < taskAssignments.size(); i++) {
            taskAssignments.get(i).setTask(tasks.get(i));
            taskAssignments.get(i).setLastModified(dateToday);
        }

        // Save updated task assignments
        taskAssignmentRepository.saveAll(taskAssignments);

        return ResponseEntity.ok("Successfully shifted!");
    }
}
