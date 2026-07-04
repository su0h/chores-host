package com.su0h.Chores.entities;

public class TaskAssignmentNotFoundException extends RuntimeException {
    public TaskAssignmentNotFoundException(Long taskId) {
        super("No task assignment found for task ID: " + taskId);
    }
}
