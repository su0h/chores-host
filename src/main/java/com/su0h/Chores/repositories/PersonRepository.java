package com.su0h.Chores.repositories;

import com.su0h.Chores.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);

    Person findById(long id);
}
