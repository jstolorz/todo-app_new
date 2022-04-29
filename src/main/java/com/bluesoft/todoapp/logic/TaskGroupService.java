package com.bluesoft.todoapp.logic;

import com.bluesoft.todoapp.model.TaskGroupRepository;
import com.bluesoft.todoapp.model.TaskGroups;
import com.bluesoft.todoapp.model.projection.GroupReadModel;
import com.bluesoft.todoapp.model.projection.GroupWriteModel;
import org.springframework.stereotype.Service;

@Service
public class TaskGroupService {

    private final TaskGroupRepository repository;

    TaskGroupService(final TaskGroupRepository repository) {
        this.repository = repository;
    }

    public GroupReadModel createGroup(GroupWriteModel source){
        final TaskGroups taskGroups = repository.save(source.toGroup());
        return new GroupReadModel(taskGroups);
    }

}
