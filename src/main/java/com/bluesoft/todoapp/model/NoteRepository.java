package com.bluesoft.todoapp.model;

import java.util.List;
import java.util.Optional;

public interface NoteRepository {
    List<Note> findAll();
    Note save(Note entity);
    boolean existsById(Integer id);
    Optional<Note> findById(Integer id);
    void deleteById(Integer id);
    void delete(Note entity);
}
