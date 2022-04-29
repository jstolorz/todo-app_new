package com.bluesoft.todoapp.model;

import java.util.List;
import java.util.Optional;

public interface TaskGroupRepository {
    List<TaskGroups> findAll();
    Optional<TaskGroups> findById(Integer id);
    TaskGroups save(TaskGroups entity);
}
