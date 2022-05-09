package com.bluesoft.todoapp.controller;

import com.bluesoft.todoapp.logic.TaskService;
import com.bluesoft.todoapp.model.Task;
import com.bluesoft.todoapp.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/tasks")
class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository repository;
    private final TaskService service;

    TaskController(final TaskRepository repository, final TaskService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping(value = "/", params = {"!sort","!page","!size"})
    CompletableFuture<ResponseEntity<List<Task>>> readAllTasks(){
        logger.warn("Exposing all the tasks");
        return service.findAllAsync().thenApply(ResponseEntity::ok);
    }

    @GetMapping
    ResponseEntity<List<Task>> readAllTasks(Pageable page){
        logger.warn("Custom pageable");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @GetMapping("/{id}")
    ResponseEntity<Task> readTask(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/done")
    ResponseEntity<List<Task>> readDoneTasks(@RequestParam(defaultValue = "true") boolean state){
        return ResponseEntity.ok(
                repository.findByDone(state)
        );
    }


    @PostMapping
    ResponseEntity<?> createNotes(@RequestBody Task task){
        repository.save(task);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody Task toUpdate){
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        repository.findById(id)
                        .ifPresent(task -> {
                            task.updateFrom(toUpdate);
                            repository.save(task);
                        });

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<?> toggleTask(@PathVariable int id){
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        repository.findById(id)
                .ifPresent(task -> task.setDone(!task.isDone()));

        return ResponseEntity.noContent().build();
    }

}
