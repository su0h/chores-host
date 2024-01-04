package com.su0h.Chores.controllers;

import com.su0h.Chores.entities.TaskAssignment;
import com.su0h.Chores.services.TaskAssignmentService;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<TaskAssignment> getAllTaskAssignments() {
        return taskAssignmentService.fetchAllTaskAssignments().getBody();
    }

}
