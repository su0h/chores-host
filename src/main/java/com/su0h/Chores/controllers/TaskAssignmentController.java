package com.su0h.Chores.controllers;

import com.su0h.Chores.entities.TaskAcknowledgeResponse;
import com.su0h.Chores.entities.TaskAssignmentResponse;
import com.su0h.Chores.services.TaskAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(
        origins = {
            "${env.cors-origin}"
        },
        maxAge = 3600
)
@RestController
@RequestMapping("/api/v1.0")
public class TaskAssignmentController {
    // https://www.baeldung.com/spring-boot-logging
    private final Logger logger = LoggerFactory.getLogger(TaskAssignmentController.class);

    // https://www.geeksforgeeks.org/easiest-way-to-create-rest-api-using-spring-boot/
    private final TaskAssignmentService taskAssignmentService;

    public TaskAssignmentController(TaskAssignmentService taskAssignmentService) {
        this.taskAssignmentService = taskAssignmentService;
    }

    @GetMapping("/task-assignments")
    public ResponseEntity<TaskAssignmentResponse> getAllTaskAssignments() {
        logger.info("Request made to /task_assignments");
        return ResponseEntity.ok(taskAssignmentService.fetchAllTaskAssignments());
    }

    @PostMapping("/task-assignments/unshift")
    public ResponseEntity<?> unshiftTaskAssignments() {
        logger.info("Request made to /task-assignments/unshift");
        return ResponseEntity.ok(taskAssignmentService.shiftTaskAssignments(false));
    }

    @PostMapping("/task-assignments/shift")
    public ResponseEntity<TaskAssignmentResponse> shiftTaskAssignments() {
        logger.info("Request made to /task-assignments/shift");
        return ResponseEntity.ok(taskAssignmentService.shiftTaskAssignments(true));
    }

    @GetMapping("/task-assignments/acknowledge/{taskId}")
    public ResponseEntity<TaskAcknowledgeResponse> getAcknowledgeInfo(@PathVariable Long taskId) {
        logger.info("Request made to GET /task-assignments/acknowledge/{}", taskId);
        return ResponseEntity.ok(taskAssignmentService.getAcknowledgeInfo(taskId));
    }

    @PostMapping("/task-assignments/acknowledge/{taskId}")
    public ResponseEntity<TaskAcknowledgeResponse> acknowledgeTask(@PathVariable Long taskId) {
        logger.info("Request made to POST /task-assignments/acknowledge/{}", taskId);
        return ResponseEntity.ok(taskAssignmentService.acknowledgeTask(taskId));
    }
}
