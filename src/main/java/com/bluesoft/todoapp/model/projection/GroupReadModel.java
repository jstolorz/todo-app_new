package com.bluesoft.todoapp.model.projection;

import com.bluesoft.todoapp.model.Task;
import com.bluesoft.todoapp.model.TaskGroups;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupReadModel {
    private int id;
    private String description;
    private LocalDateTime deadline;
    private Set<GroupTaskReadModel> tasks;

    public GroupReadModel(TaskGroups source) {
        description = source.getDescription();
        source.getTasks().stream()
                .map(Task::getDeadline)
                .max(LocalDateTime::compareTo)
                .ifPresent(date -> deadline = date);
        tasks = source.getTasks().stream()
                .map(GroupTaskReadModel::new)
                .collect(Collectors.toSet());
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(final LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Set<GroupTaskReadModel> getTasks() {
        return tasks;
    }

    public void setTasks(final Set<GroupTaskReadModel> tasks) {
        this.tasks = tasks;
    }
}
