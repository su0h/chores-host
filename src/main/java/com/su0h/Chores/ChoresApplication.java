package com.su0h.Chores;

import com.su0h.Chores.entities.*;
import com.su0h.Chores.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
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
//			MetadataRepository metadataRepository
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
//			for(int i = 0; i < personList.size(); i++)
//				taskAssignmentRepository.save(new TaskAssignment(personList.get(i), taskList.get(i)));
//
//			metadataRepository.save(new Metadata(Metadata.Key.LAST_MODIFIED, LocalDateTime.now().toString()));
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
