package com.su0h.Chores.controllers;

import com.su0h.Chores.entities.TaskAssignmentResponse;
import com.su0h.Chores.services.TaskAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0")
public class TaskAssignmentController {
    // https://www.geeksforgeeks.org/easiest-way-to-create-rest-api-using-spring-boot/
    private final TaskAssignmentService taskAssignmentService;

    public TaskAssignmentController(TaskAssignmentService taskAssignmentService) {
        this.taskAssignmentService = taskAssignmentService;
    }

    @GetMapping("/task_assignments")
    public ResponseEntity<TaskAssignmentResponse> getAllTaskAssignments() {
        return ResponseEntity.ok(taskAssignmentService.fetchAllTaskAssignments());
    }

    // Note: What if return the updated list?
    @PostMapping("/unshift_task_assignments")
    public ResponseEntity<String> unshiftTaskAssignments() {
        return ResponseEntity.ok(taskAssignmentService.unshiftTaskAssignments());
    }

    // TODO: Remove this; this is used for testing purposes only;
    //       no API endpoints doing this will be exposed in final version
    @PostMapping("/shift_task_assignments")
    public ResponseEntity<String> shiftTaskAssignments() {
//        return ResponseEntity.badRequest().body("No permission");
        return ResponseEntity.ok(taskAssignmentService.shiftTaskAssignments());
    }
}
