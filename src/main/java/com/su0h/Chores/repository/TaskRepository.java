package com.su0h.Chores.repository;

import com.su0h.Chores.entity.Task;
import com.su0h.Chores.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Person> findByName(String name);

    Task findById(long id);
}
