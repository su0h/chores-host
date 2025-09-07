package com.su0h.Chores.task.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Person> findByName(String name);

    Task findById(long id);
}
