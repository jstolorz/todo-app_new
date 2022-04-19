package com.bluesoft.todoapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "task_groups")
public class TaskGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Task groups description must be not null")
    private String description;
    private boolean done;
    @Embedded
    private Audit audit = new Audit();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private Set<Task> tasks;

    TaskGroups() {
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    Set<Task> getTasks() {
        return tasks;
    }

    void setTasks(final Set<Task> tasks) {
        this.tasks = tasks;
    }
}
