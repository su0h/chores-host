package com.su0h.Chores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChoresApplication {

//	private static final Logger log = LoggerFactory.getLogger(SpringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ChoresApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(PersonRepository personRepository, TaskRepository taskRepository) {
//		return (args) -> {
//			personRepository.save(new Person("Alice"));
//			personRepository.save(new Person("Bob"));
//			personRepository.save(new Person("Jorhe"));
//
//			taskRepository.save(new Task("Wash Dishes"));
//			taskRepository.save(new Task("Prepare Table"));
//			taskRepository.save(new Task("Clean Table"));
//
//			log.info("Users found with findAll():");
//			log.info("---------------------------");
//			personRepository.findAll().forEach(person -> {
//				log.info(person.toString());
//			});
//			log.info("");
//
//			Person person = personRepository.findById(1L);
//			log.info("Customer found with findById(1L):");
//			log.info("--------------------------------");
//			log.info(person.toString());
//			log.info("");
//
//			log.info("Customer found with findByName('Jorhe'):");
//			log.info("---------------------------------------");
//			personRepository.findByName("Jorhe").forEach(jorhe -> {
//				log.info(jorhe.toString());
//			});
//			log.info("");
//		};
//	}

}
