package com.su0h.Chores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.su0h.Chores.entity.TaskAssignment;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {
    TaskAssignment findById(long id);
}
