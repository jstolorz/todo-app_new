package com.bluesoft.todoapp.logic;

import com.bluesoft.todoapp.TaskConfigurationProperties;
import com.bluesoft.todoapp.model.ProjectRepository;
import com.bluesoft.todoapp.model.TaskGroupRepository;
import com.bluesoft.todoapp.model.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LogicConfiguration {

    @Bean
    ProjectService service(final ProjectRepository repository,
                           final TaskGroupRepository taskGroupRepository,
                           final TaskGroupService taskGroupService,
                           final TaskConfigurationProperties configurationProperties){
        return new ProjectService(repository,taskGroupRepository,taskGroupService,configurationProperties);
    }

    @Bean
    TaskGroupService groupService(final TaskRepository taskRepository, final TaskGroupRepository repository){
        return new TaskGroupService(taskRepository,repository);
    }
}
