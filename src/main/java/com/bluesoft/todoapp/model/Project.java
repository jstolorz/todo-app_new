package com.bluesoft.todoapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Project's description must not be empty")
    private String description;

    @OneToMany(mappedBy = "project")
    private Set<TaskGroups> groups;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "project")
    private Set<ProjectStep> steps;

    public Project() {
    }

    int getId() {
        return id;
    }

    void setId(final int id) {
        this.id = id;
    }

    String getDescription() {
        return description;
    }

    void setDescription(final String description) {
        this.description = description;
    }

    Set<TaskGroups> getGroups() {
        return groups;
    }

    void setGroups(final Set<TaskGroups> groups) {
        this.groups = groups;
    }

    public Set<ProjectStep> getSteps() {
        return steps;
    }

    void setSteps(final Set<ProjectStep> steps) {
        this.steps = steps;
    }
}
