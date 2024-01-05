package com.su0h.Chores.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "task_assignment")
public class TaskAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //    @OneToOne(cascade = CascadeType.ALL)
    @OneToOne()
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

//    @OneToOne(cascade = CascadeType.ALL)
    @OneToOne()
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_modified")
    private LocalDate lastModified;

    protected TaskAssignment() { }

    public TaskAssignment(Person person, Task task) {
        // https://stackoverflow.com/questions/11104897/hibernate-attempted-to-assign-id-from-null-one-to-one-property-employee
        this.person = person;
        this.person.setTaskAssignment(this);

        this.task = task;
        this.task.setTaskAssignment(this);

        this.lastModified = LocalDate.now();
    }

    @Override
    public String toString() {
        return String.format(
                "TaskAssignment[id=%d, lastModified='%s', p_id=%d, t_id=%d]",
                this.id, this.lastModified, this.person.getId(), this.task.getId()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }
}
