package com.su0h.Chores.entities;

import java.time.LocalDate;
import java.util.List;

public class TaskAssignmentResponse {
    private LocalDate lastModified;
    private List<SimplifiedTaskAssignment> taskAssignments;

    public TaskAssignmentResponse (LocalDate lastModified, List<SimplifiedTaskAssignment> taskAssignments) {
        this.lastModified = lastModified;
        this.taskAssignments = taskAssignments;
    }

    public static class SimplifiedTaskAssignment {
        private String personName;
        private String taskName;

        public SimplifiedTaskAssignment(String personName, String taskName) {
            this.personName = personName;
            this.taskName =  taskName;
        }

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
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    public List<SimplifiedTaskAssignment> getTaskAssignments() {
        return taskAssignments;
    }

    public void setTaskAssignments(List<SimplifiedTaskAssignment> taskAssignments) {
        this.taskAssignments = taskAssignments;
    }
}
