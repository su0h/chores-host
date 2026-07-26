package com.su0h.Chores.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int sequence;

    @OneToOne(mappedBy = "task")
    // https://stackoverflow.com/questions/75757132/failure-while-trying-to-resolve-exception-org-springframework-http-converter-ht
    @JsonIgnore
    private TaskAssignment taskAssignment;

    protected Task() {}

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, int sequence) {
        this.name = name;
        this.sequence = sequence;
    }

    public String toString() {
        return String.format(
                "Task[id=%d, name='%s']",
                this.id, this.name
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public TaskAssignment getTaskAssignment() {
        return taskAssignment;
    }

    public void setTaskAssignment(TaskAssignment taskAssignment) {
        this.taskAssignment = taskAssignment;
    }
}
