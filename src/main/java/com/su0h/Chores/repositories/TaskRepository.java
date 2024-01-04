package com.su0h.Chores.repositories;

import com.su0h.Chores.entities.Task;
import com.su0h.Chores.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Person> findByName(String name);

    Task findById(long id);
}
