package com.bluesoft.todoapp.logic;

import com.bluesoft.todoapp.model.TaskGroupRepository;
import com.bluesoft.todoapp.model.TaskGroups;
import com.bluesoft.todoapp.model.TaskRepository;
import com.bluesoft.todoapp.model.projection.GroupReadModel;
import com.bluesoft.todoapp.model.projection.GroupWriteModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskGroupService {

    private final TaskRepository taskRepository;
    private final TaskGroupRepository repository;


    TaskGroupService(final TaskRepository taskRepository, final TaskGroupRepository repository) {
        this.taskRepository = taskRepository;
        this.repository = repository;
    }

    public GroupReadModel createGroup(GroupWriteModel source){
        final TaskGroups taskGroups = repository.save(source.toGroup());
        return new GroupReadModel(taskGroups);
    }

    public List<GroupReadModel> readAll(){
        return repository.findAll()
                .stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }

    public void toggleGroup(int groupId){
         if(taskRepository.existsByDoneIsFalseAndGroup_Id(groupId)){
             throw new IllegalStateException("Group has undone tasks!");
         }

        final TaskGroups taskGroups = repository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("TaskGroup with given id not found!"));

         taskGroups.setDone(!taskGroups.isDone());
    }

}
