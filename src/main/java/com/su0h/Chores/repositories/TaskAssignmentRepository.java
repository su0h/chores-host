package com.su0h.Chores.repositories;

import com.su0h.Chores.entities.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {
    TaskAssignment findById(long id);

    @Query(value = "SELECT last_modified FROM task_assignment LIMIT 1", nativeQuery = true)
    LocalDate getLastModifiedDate();
}
