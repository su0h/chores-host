package com.su0h.Chores.repositories;

import com.su0h.Chores.entities.Person;
import com.su0h.Chores.entities.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {
    Person findById(long id);

//    @Query(value = "UPDATE task_assignment\n" +
//            "SET task_id = CASE\n" +
//            "                WHEN person_id = 1 THEN 2\n" +
//            "                WHEN person_id = 2 THEN 3\n" +
//            "                WHEN person_id = 3 THEN 1\n" +
//            "             END;")
//    void shiftTaskAssignments();
}
