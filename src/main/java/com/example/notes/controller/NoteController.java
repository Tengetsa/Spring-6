package com.example.notes.controller;

import com.example.notes.model.Note;
import com.example.notes.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {
    private NoteService service;

    /**
     * addNote - обавление заметки.
     */
    @PostMapping("/add")
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        return new ResponseEntity<>(service.addNote(note), HttpStatus.CREATED);
    }

    /**
     * getAllNotes - просмотр всех заметок.
     */
    @GetMapping()
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(service.getAllNotes(), HttpStatus.OK);
    }

    /**
     * getNoteById - получения данных по id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Note>> getNoteById(@PathVariable("id") Long id) {
        Optional<Note> noteById;
        try {
            noteById = service.getNoteById(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Optional.of(new Note()));
        }
        return new ResponseEntity<>(noteById, HttpStatus.OK);
//        return new ResponseEntity<>(service.getNoteById(id), HttpStatus.OK) ;
    }

    /**
     * updateNote - редактирование заметки по id.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note) {
        return new ResponseEntity<>(service.updateNote(id, note), HttpStatus.OK);
    }

    /**
     * deleteTask - удаление заметки по id.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id) {
        service.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}
