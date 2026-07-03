package com.su0h.Chores.entities;

public class TaskAcknowledgeResponse {
    private Long taskId;
    private String taskName;
    private TaskAssignment.Status status;

    public TaskAcknowledgeResponse(Long taskId, String taskName, TaskAssignment.Status status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    // getters
    public Long getTaskId() { return taskId; }
    public String getTaskName() { return taskName; }
    public TaskAssignment.Status getStatus() { return status; }
}
