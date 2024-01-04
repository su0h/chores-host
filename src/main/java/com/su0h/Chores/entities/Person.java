package com.su0h.Chores.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "person")
    // https://stackoverflow.com/questions/75757132/failure-while-trying-to-resolve-exception-org-springframework-http-converter-ht
    @JsonIgnore
    private TaskAssignment taskAssignment;

    protected Person() {}

    public Person(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format(
                "User[id=%d, name='%s']",
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

    public TaskAssignment getTaskAssignment() {
        return taskAssignment;
    }

    public void setTaskAssignment(TaskAssignment taskAssignment) {
        this.taskAssignment = taskAssignment;
    }
}
