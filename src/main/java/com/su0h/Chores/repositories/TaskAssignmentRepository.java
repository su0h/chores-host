package com.su0h.Chores.repositories;

import com.su0h.Chores.entities.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {
    TaskAssignment findById(long id);
    Optional<TaskAssignment> findByTaskId(Long taskId);
    boolean existsByStatus(TaskAssignment.Status status);
}
