package com.bluesoft.todoapp.logic;

import com.bluesoft.todoapp.TaskConfigurationProperties;
import com.bluesoft.todoapp.model.ProjectRepository;
import com.bluesoft.todoapp.model.TaskGroupRepository;
import com.bluesoft.todoapp.model.TaskGroups;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectServiceTest {

    @Test
    void createGroup_throws_illegalStateException() {
        // given
        TaskGroupRepository mockGroupRepository = getTaskGroupRepository();
        // and
        TaskConfigurationProperties mockConfig = configurationReturning(false);
        // system under test
        var toTest = new ProjectService(null, mockGroupRepository,null, mockConfig);

        // when
        var exception = catchThrowable(() -> toTest.createGroup(LocalDateTime.now(),0));

        // then
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                        .hasMessageContaining("Only one undone group");

        // when + then
            assertThatThrownBy(() -> {
                toTest.createGroup(LocalDateTime.now(),0);
            }).isInstanceOf(IllegalStateException.class);


            assertThatExceptionOfType(IllegalStateException.class)
                    .isThrownBy(() -> toTest.createGroup(LocalDateTime.now(),0));

            assertThatIllegalStateException()
                    .isThrownBy(() -> toTest.createGroup(LocalDateTime.now(),0));

    }

    @Test
    void createGroup_throws_illegalArgumentException() {
        // given
        var mockRepository = mock(ProjectRepository.class);
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());
        // and
        TaskConfigurationProperties mockConfig = configurationReturning(true);
        // system under test
        var toTest = new ProjectService(mockRepository, null,null, mockConfig);

        // when
        var exception = catchThrowable(() -> toTest.createGroup(LocalDateTime.now(),0));

        // then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Project with given");

    }

    @Test
    void createGroup() {
        // given
        TaskGroupRepository mockGroupRepository = getTaskGroupRepository();

        var mockRepository = mock(ProjectRepository.class);
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());
        // and
        TaskConfigurationProperties mockConfig = configurationReturning(false);
        // system under test
        var toTest = new ProjectService(mockRepository, mockGroupRepository,null, mockConfig);

        // when
        var exception = catchThrowable(() -> toTest.createGroup(LocalDateTime.now(),0));

        // then

    }

    @Test
    void shouldCreateNewTaskGroup(){
        TaskGroupRepository mockGroupRepository = getTaskGroupRepository();

        var mockRepository = mock(ProjectRepository.class);
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());
        // and
        TaskConfigurationProperties mockConfig = configurationReturning(true);
    }


    private TaskConfigurationProperties configurationReturning(final boolean t) {

        var mockTemplate = mock(TaskConfigurationProperties.Template.class);
        when(mockTemplate.isAllowMultipleTasks()).thenReturn(t);
        // and
        var mockConfig = mock(TaskConfigurationProperties.class);
        when(mockConfig.getTemplate()).thenReturn(mockTemplate);
        return mockConfig;
    }

    private TaskGroupRepository getTaskGroupRepository() {
        var mockGroupRepository = mock(TaskGroupRepository.class);
        when(mockGroupRepository.existsByDoneIsFalseAndProject_Id(anyInt())).thenReturn(true);
        return mockGroupRepository;
    }

    private TaskGroupRepository inMemoryGroupRepository(){
        return new TaskGroupRepository(){

            private int index = 0;
            private Map<Integer,TaskGroups> map = new HashMap<>();

            @Override
            public List<TaskGroups> findAll() {
                return new ArrayList<>(map.values());
            }

            @Override
            public Optional<TaskGroups> findById(final Integer id) {
                return Optional.ofNullable(map.get(id));
            }

            @Override
            public TaskGroups save(final TaskGroups entity) {
                if(entity.getId() != 0){
                    map.put(entity.getId(),entity);
                }else{
                    map.put(++index,entity);
                }
                return entity;
            }

            @Override
            public boolean existsByDoneIsFalseAndProject_Id(final Integer projectId) {
                return false;
            }
        };
    }
}