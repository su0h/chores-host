package com.su0h.Chores.entities;

import java.time.LocalDateTime;
import java.util.List;

public class TaskAssignmentResponse {
    private LocalDateTime lastModified;
    private List<SimplifiedTaskAssignment> taskAssignments;

    public TaskAssignmentResponse(LocalDateTime lastModified, List<SimplifiedTaskAssignment> taskAssignments) {
        this.lastModified = lastModified;
        this.taskAssignments = taskAssignments;
    }

    public static class SimplifiedTaskAssignment {
        private Long taskId;
        private String personName;
        private String taskName;
        private TaskAssignment.Status status;

        public SimplifiedTaskAssignment(Long taskId, String personName, String taskName, TaskAssignment.Status status) {
            this.taskId = taskId;
            this.personName = personName;
            this.taskName =  taskName;
            this.status = status;
        }

        public Long getTaskId() { return taskId; }

        public void setTaskId(Long taskId) { this.taskId = taskId; }

        public String getPersonName() {
            return personName;
        }

        public void setPersonName(String personName) {
            this.personName = personName;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public TaskAssignment.Status getStatus() {
            return status;
        }

        public void setStatus(TaskAssignment.Status status) {
            this.status = status;
        }
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public List<SimplifiedTaskAssignment> getTaskAssignments() {
        return taskAssignments;
    }

    public void setTaskAssignments(List<SimplifiedTaskAssignment> taskAssignments) {
        this.taskAssignments = taskAssignments;
    }
}
