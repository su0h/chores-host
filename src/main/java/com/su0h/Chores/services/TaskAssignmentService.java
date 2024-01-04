package com.su0h.Chores.services;

import com.su0h.Chores.entities.TaskAssignment;
import com.su0h.Chores.repositories.TaskAssignmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskAssignmentService {
    private final TaskAssignmentRepository taskAssignmentRepository;

    public TaskAssignmentService(TaskAssignmentRepository taskAssignmentRepository) {
        this.taskAssignmentRepository = taskAssignmentRepository;
    }

    public ResponseEntity<List<TaskAssignment>> fetchAllTaskAssignments() {
        for (TaskAssignment taskAssignment : taskAssignmentRepository.findAll()) {
            System.out.println(taskAssignment);
        }
        return ResponseEntity.ok(taskAssignmentRepository.findAll());
    }

    public ResponseEntity<String> shiftTaskAssignments() {
        // TODO: Implement shifting in repository
        return ResponseEntity.ok("");
    }
}
