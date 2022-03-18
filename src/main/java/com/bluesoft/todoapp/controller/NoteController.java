package com.bluesoft.todoapp.controller;

import com.bluesoft.todoapp.model.Note;
import com.bluesoft.todoapp.model.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
class NoteController {
    private final Logger logger = LoggerFactory.getLogger(NoteController.class);
    private final NoteRepository repository;

    NoteController(final NoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/notes")
    ResponseEntity<?> readAllNotes(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/notes/{id}")
    ResponseEntity<?> readNoteById(@PathVariable int id){

        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(repository.findById(id));
    }


    @PostMapping("/notes")
    ResponseEntity<?> createNotes(@RequestBody Note note){
        repository.save(note);
        return ResponseEntity.status(201).build();
    }

// When a client needs to replace an existing Resource entirely, they can use PUT.
// When they're doing a partial update, they can use HTTP PATCH.

    @PutMapping("/notes/{id}")
    ResponseEntity<?> replaceNotes(@PathVariable int id, @RequestBody Note note){
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        note.setId(id);
        repository.save(note);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/notes/{id}")
    ResponseEntity<?> updateNotes(@PathVariable int id, @RequestBody Note note){

        Optional<Note> uNote = repository.findById(id);

        if(!uNote.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Note updateNote = uNote.get();

        if(note.getTitle() != null){
            updateNote.setTitle(note.getTitle());
        }

        logger.info("desc: " + note.getDescription());

        if(note.getDescription() != null){
            updateNote.setDescription(note.getDescription());
        }

        if(note.isPriority() != updateNote.isPriority()){
            updateNote.setPriority(note.isPriority());
        }

        repository.save(updateNote);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/notes")
    ResponseEntity<?> deleteNote(@RequestBody Note note){
        if (!repository.existsById(note.getId())) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(note);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/notes/{id}")
    ResponseEntity<?> deleteNoteById(@PathVariable int id){
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
