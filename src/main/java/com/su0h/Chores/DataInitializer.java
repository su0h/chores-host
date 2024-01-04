package com.su0h.Chores;

import com.su0h.Chores.entities.Person;
import com.su0h.Chores.entities.Task;
import com.su0h.Chores.entities.TaskAssignment;
import com.su0h.Chores.repositories.PersonRepository;
import com.su0h.Chores.repositories.TaskAssignmentRepository;
import com.su0h.Chores.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskAssignmentRepository taskAssignmentRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Create sample data
        Person person1 = new Person("Alice");
        Person person2 = new Person("Bob");
        Person person3 = new Person("Jorhe");

        Task task1 = new Task("Prepare Table");
        Task task2 = new Task("Clean Table");
        Task task3 = new Task("Wash Dishes");

        // Save entities to the database
        personRepository.saveAll(List.of(person1, person2, person3));
        taskRepository.saveAll(List.of(task1, task2, task3));

        // Create TaskAssignments
        TaskAssignment assignment1 = new TaskAssignment(person1, task1);
        TaskAssignment assignment2 = new TaskAssignment(person2, task2);
        TaskAssignment assignment3 = new TaskAssignment(person3, task3);

        // Save TaskAssignments to the database
        taskAssignmentRepository.saveAll(List.of(assignment1, assignment2, assignment3));
    }
}