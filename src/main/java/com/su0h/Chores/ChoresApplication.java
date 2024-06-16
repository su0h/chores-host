package com.su0h.Chores;

import com.su0h.Chores.entities.*;
import com.su0h.Chores.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ChoresApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringApplication.class);

	public static void main(String[] args) {
		logger.info("Starting application...");
		SpringApplication.run(ChoresApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner initializeDatabase(
//			PersonRepository personRepository,
//			TaskRepository taskRepository,
//			TaskAssignmentRepository taskAssignmentRepository,
//			MetadataRepository metadataRepository,
//			HolidayRepository holidayRepository
//	) {
//		return (args) -> {
//			// Note: use this in conjunction with spring.jpa.hibernate.ddl-auto=create-drop in application.properties
//			personRepository.save(new Person("Anne"));
//			personRepository.save(new Person("Bob"));
//			personRepository.save(new Person("Jorhe"));
//
//			taskRepository.save(new Task("Wash Dish"));
//			taskRepository.save(new Task("Prepare Table"));
//			taskRepository.save(new Task("Clean Up Table"));
//
//			List<Person> personList = personRepository.findAll();
//			List<Task> taskList = taskRepository.findAll();
//
//			for (int i = 0; i < personList.size(); i++)
//				taskAssignmentRepository.save(new TaskAssignment(personList.get(i), taskList.get(i)));
//
//			metadataRepository.save(new Metadata(Metadata.Key.LAST_MODIFIED, LocalDateTime.now().toString()));
//
//			List<Holiday> holidayList = new ArrayList<>();
//
//			// From: https://www.globe.com.ph/go/travel-food/article/official-list-holidays-philippines
//			holidayList.add(new Holiday("New Year's Day", LocalDate.of(2024, 1, 1)));
//			holidayList.add(new Holiday("Maundy Thursday", LocalDate.of(2024, 3, 28)));
//			holidayList.add(new Holiday("Good Friday", LocalDate.of(2024, 3, 29)));
//			holidayList.add(new Holiday("Day of Valor", LocalDate.of(2024, 4, 9)));
//			holidayList.add(new Holiday("Eid'l Fitr", LocalDate.of(2024, 4, 10)));
//			holidayList.add(new Holiday("Labor Day", LocalDate.of(2024, 5, 1)));
//			holidayList.add(new Holiday("Independence Day", LocalDate.of(2024, 6, 12)));
//			holidayList.add(new Holiday("Eid'l Adha", LocalDate.of(2024, 6, 17)));
//			holidayList.add(new Holiday("National Heroes Day", LocalDate.of(2024, 8, 26)));
//			holidayList.add(new Holiday("Bonifacio Day", LocalDate.of(2024, 11, 30)));
//			holidayList.add(new Holiday("Christmas Day", LocalDate.of(2024, 12, 25)));
//			holidayList.add(new Holiday("Rizal Day", LocalDate.of(2024, 12, 30)));
//			holidayList.add(new Holiday("Chinese New Year", LocalDate.of(2024, 2, 9)));
//			holidayList.add(new Holiday("Black Saturday", LocalDate.of(2024, 3, 30)));
//			holidayList.add(new Holiday("Ninoy Aquino Day", LocalDate.of(2024, 8, 21)));
//			holidayList.add(new Holiday("All Saints' Day", LocalDate.of(2024, 11, 1)));
//			holidayList.add(new Holiday("All Souls' Day", LocalDate.of(2024, 11, 2)));
//			holidayList.add(new Holiday("Feast of the Immaculate Conception of Mary", LocalDate.of(2024, 12, 8)));
//			holidayList.add(new Holiday("Christmas Eve", LocalDate.of(2024, 12, 24)));
//			holidayList.add(new Holiday("Last Day of the Year", LocalDate.of(2024, 12, 31)));
//
//			for (Holiday holiday : holidayList)
//				holidayRepository.save(holiday);
//
//			logger.info("Users found with findAll():");
//			logger.info("---------------------------");
//			personRepository.findAll().forEach(person -> {
//				logger.info(person.toString());
//			});
//			logger.info("");
//
//			Person person = personRepository.findById(1L);
//			logger.info("Customer found with findById(1L):");
//			logger.info("--------------------------------");
//			logger.info(person.toString());
//			logger.info("");
//
//			logger.info("Customer found with findByName('Jorhe'):");
//			logger.info("---------------------------------------");
//			personRepository.findByName("Andre").forEach(jorhe -> {
//				logger.info(jorhe.toString());
//			});
//			logger.info("");
//		};
//	}
}
