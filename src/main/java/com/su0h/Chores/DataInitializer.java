package com.su0h.Chores;

import com.su0h.Chores.entities.*;
import com.su0h.Chores.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskAssignmentRepository taskAssignmentRepository;

    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private MetadataRepository metadataRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Create Persons
        List<Person> personList = new ArrayList<>();

        personList.add(new Person("Alice"));
        personList.add(new Person("Bob"));
        personList.add(new Person("Jorhe"));
//        personList.add(new Person("Charlie"));
//        personList.add(new Person("Mae"));

        // Create Tasks
        List<Task> taskList = new ArrayList<>();

        taskList.add(new Task("Hain"));
        taskList.add(new Task("Ligpit"));
        taskList.add(new Task("Hugas"));
//        taskList.add(new Task("Feed Dog"));
//        taskList.add(new Task("Clean Bathroom"));

        // Save entities to the database
        personRepository.saveAll(personList);
        taskRepository.saveAll(taskList);

        // Create and save Task Assignments
        for (int i = 0; i < personList.size(); i++) {
            TaskAssignment taskAssignment = new TaskAssignment(personList.get(i), taskList.get(i));
            taskAssignmentRepository.save(taskAssignment);
        }

        metadataRepository.save(new Metadata("lastModified", LocalDate.now().toString()));

        this.initializeHolidays();
    }

    private void initializeHolidays() {
        holidayRepository.save(new Holiday("New Year's Day", LocalDate.of(2024, 1, 1)));
        holidayRepository.save(new Holiday("Chinese New Year", LocalDate.of(2024, 2, 10)));
        holidayRepository.save(new Holiday("Maundy Thursday", LocalDate.of(2024, 3, 28)));
        holidayRepository.save(new Holiday("Good Friday", LocalDate.of(2024, 3, 29)));
        holidayRepository.save(new Holiday("New Year's Day", LocalDate.of(2024, 1, 1)));

//        holidayRepository.save(new Holiday("Test Holiday", LocalDate.of(2024, 1, 5)));
    }
}