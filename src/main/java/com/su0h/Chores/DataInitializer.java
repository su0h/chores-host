package com.su0h.Chores;

import com.su0h.Chores.entities.*;
import com.su0h.Chores.repositories.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        this.logger.info("Initializing database content...");
//
//        this.logger.info("Adding persons...");
//        // Create Persons
//        List<Person> personList = new ArrayList<>();
//
//        personList.add(new Person("Andre"));
//        personList.add(new Person("Nikki"));
//        personList.add(new Person("Adrian"));
//
//        personRepository.saveAll(personList);
//
//        this.logger.info("Adding tasks...");
//        // Create Tasks
//        List<Task> taskList = new ArrayList<>();
//
//        taskList.add(new Task("Hain"));
//        taskList.add(new Task("Ligpit"));
//        taskList.add(new Task("Hugas"));
//
//        taskRepository.saveAll(taskList);
//
//        this.logger.info("Adding task assignments...");
//        // Create and save Task Assignments
//        for (int i = 0; i < personList.size(); i++) {
//            TaskAssignment taskAssignment = new TaskAssignment(personList.get(i), taskList.get(i));
//            taskAssignmentRepository.save(taskAssignment);
//        }
//
//        this.logger.info("Setting up the metadata...");
//        metadataRepository.save(new Metadata(Metadata.Key.LAST_MODIFIED, LocalDateTime.now().toString()));
//        metadataRepository.save(new Metadata(Metadata.Key.LAST_UNSHIFTED, LocalDate.of(2024, 1, 1).toString()));
//
//        this.logger.info("Adding holidays...");
//        this.initializeHolidays();
//
//        this.logger.info("Done");
    }

    private void initializeHolidays() {
        // Source: https://www.rappler.com/nation/list-holidays-philippines-2024/
        holidayRepository.save(new Holiday("New Year's Day", LocalDate.of(2024, 1, 1)));
        holidayRepository.save(new Holiday("Chinese New Year", LocalDate.of(2024, 2, 9)));
        holidayRepository.save(new Holiday("Maundy Thursday", LocalDate.of(2024, 3, 28)));
        holidayRepository.save(new Holiday("Good Friday", LocalDate.of(2024, 3, 29)));
        holidayRepository.save(new Holiday("Black Saturday", LocalDate.of(2024, 3, 30)));
        holidayRepository.save(new Holiday("Araw ng Kagitingan", LocalDate.of(2024, 4, 9)));
        holidayRepository.save(new Holiday("Labor Day", LocalDate.of(2024, 5, 1)));
        holidayRepository.save(new Holiday("Independence Day", LocalDate.of(2024, 6, 12)));
        holidayRepository.save(new Holiday("Ninoy Aquino Day", LocalDate.of(2024, 8, 21)));
        holidayRepository.save(new Holiday("National Heroes Day", LocalDate.of(2024, 8, 26)));
        holidayRepository.save(new Holiday("All Saints' Day", LocalDate.of(2024, 11, 1)));
        holidayRepository.save(new Holiday("All Souls' Day", LocalDate.of(2024, 11, 2)));
        holidayRepository.save(new Holiday("Bonifacio Day", LocalDate.of(2024, 11, 30)));
        holidayRepository.save(new Holiday("Feast of the Immaculate Conception of mary", LocalDate.of(2024, 12, 8)));
        holidayRepository.save(new Holiday("Christmas Evve", LocalDate.of(2024, 12, 24)));
        holidayRepository.save(new Holiday("Christmas Day", LocalDate.of(2024, 12, 25)));
        holidayRepository.save(new Holiday("Rizal Day", LocalDate.of(2024, 12, 30)));
        holidayRepository.save(new Holiday("Last Day of the Year", LocalDate.of(2024, 12, 31)));
    }
}