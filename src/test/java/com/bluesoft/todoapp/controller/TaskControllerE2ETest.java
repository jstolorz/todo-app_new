package com.bluesoft.todoapp.controller;

import com.bluesoft.todoapp.model.Task;
import com.bluesoft.todoapp.model.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    TaskRepository repository;

    @Test
    void httpGet_returnAllTasks(){
        int initial = repository.findAll().size();
        repository.save(new Task("foo", LocalDateTime.now()));
        repository.save(new Task("bar", LocalDateTime.now()));

        final Task[] tasks = testRestTemplate.getForObject("http://localhost:" + port + "/tasks", Task[].class);

        assertThat(tasks).hasSize(initial + 2);
    }

}