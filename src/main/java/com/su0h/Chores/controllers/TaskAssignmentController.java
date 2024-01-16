package com.su0h.Chores.controllers;

import com.su0h.Chores.entities.TaskAssignmentResponse;
import com.su0h.Chores.services.TaskAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/task_assignments")
    public ResponseEntity<TaskAssignmentResponse> getAllTaskAssignments() {
        logger.info("Request to /task_assignments made");
        return taskAssignmentService.fetchAllTaskAssignments();
    }

    @PostMapping("/unshift_task_assignments")
    public String unshiftTaskAssignments() {
        logger.info("Request to /unshift_task_assignments made");
        return taskAssignmentService.unshiftTaskAssignments().getBody();
    }

    // TODO: Remove this; this is used for testing purposes only;
    //       no API endpoints doing this will be exposed in final version
    @PostMapping("/shift_task_assignments")
    public ResponseEntity<String> shiftTaskAssignments() {
        logger.warn("Request to /shift_task_assignments made; this normally shouldn't be accessible");
        return taskAssignmentService.shiftTaskAssignments();
    }
}
