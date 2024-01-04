package com.su0h.Chores.repositories;

import com.su0h.Chores.entities.Person;
import com.su0h.Chores.entities.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {
    Person findById(long id);
}
