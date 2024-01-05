package com.su0h.Chores.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "holiday")
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate date;

    protected Holiday() { }

    public Holiday(String name, LocalDate localDate) {
        this.name = name;
        this.date = localDate;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate localDate) {
        this.date = localDate;
    }
}
