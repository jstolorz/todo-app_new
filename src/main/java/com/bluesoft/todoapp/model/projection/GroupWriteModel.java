package com.bluesoft.todoapp.model.projection;

import com.bluesoft.todoapp.model.Project;
import com.bluesoft.todoapp.model.TaskGroups;

import java.util.Set;
import java.util.stream.Collectors;

public class GroupWriteModel {
    private String description;
    private Set<GroupTaskWriteModel> tasks;

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Set<GroupTaskWriteModel> getTasks() {
        return tasks;
    }

    public void setTasks(final Set<GroupTaskWriteModel> tasks) {
        this.tasks = tasks;
    }

    public TaskGroups toGroup(final Project project){
        var result = new TaskGroups();
        result.setDescription(description);
        result.setTasks(tasks.stream().
                map(GroupTaskWriteModel::toTask)
                .collect(Collectors.toSet()));
       result.setProject(project);
       return result;
    }


}
