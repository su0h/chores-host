package com.su0h.Chores.entities;

public class TaskAcknowledgeResponse {
    private final Long taskId;
    private final String taskName;
    private final String personName;
    private final TaskAssignment.Status status;

    public TaskAcknowledgeResponse(Long taskId, String taskName, String personName, TaskAssignment.Status status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.personName = personName;
        this.status = status;
    }

    // getters
    public Long getTaskId() { return taskId; }
    public String getTaskName() { return taskName; }
    public String getPersonName() { return personName; }
    public TaskAssignment.Status getStatus() { return status; }
}
