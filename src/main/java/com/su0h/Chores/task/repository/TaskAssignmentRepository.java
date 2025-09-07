package com.su0h.Chores.task.repository;

import com.su0h.Chores.task.entity.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {
    TaskAssignment findById(long id);
}
