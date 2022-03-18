package com.bluesoft.todoapp.model;

import org.springframework.data.jpa.repository.JpaRepository;

interface SqlNoteRepository extends NoteRepository, JpaRepository<Note,Integer> {
}
