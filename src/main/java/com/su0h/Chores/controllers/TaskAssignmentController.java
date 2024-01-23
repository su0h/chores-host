package com.su0h.Chores.controllers;

import com.su0h.Chores.entities.TaskAssignmentResponse;
import com.su0h.Chores.services.TaskAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(
        origins = {
                "http://localhost:4200",
                "https://localhost:4200",
                "http://127.0.0.1:4200",
                "https://127.0.0.1:4200",
                "http://192.168.1.92:4200",
                "https://192.168.1.92:4200",
                "http://chores.local",
                "https://chores.local"
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
        return ResponseEntity.ok(taskAssignmentService.basicUnshiftTaskAssignments());
    }

    @PostMapping("/task-assignments/shift")
    public ResponseEntity<String> shiftTaskAssignments() {
        logger.warn("Request made to /task-assignments/shift");
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
//        return ResponseEntity.ok(taskAssignmentService.shiftTaskAssignments());
    }
}
