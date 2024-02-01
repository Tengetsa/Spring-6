package com.example.notes.service;


import com.example.notes.model.Note;
import com.example.notes.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteService {
    private final NoteRepository repository;

    /**
     * Добавление заметки.
     */
    public Note addNote(Note note) {return repository.save(note);}

    /**
     * Просмотр всех заметок.
     */
    public List<Note> getAllNotes() {return repository.findAll();}

    /**
     * Получение заметки по id.
     */
    public Optional<Note> getNoteById(Long id) { return repository.findById(id);}

    /**
     * Редактирование заметку по id.
     */
    public Note updateNote(Long id, Note detailsNote) {
        Optional<Note> optionalNote = repository.findById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setContent(detailsNote.getContent());
            note.setHeading(detailsNote.getHeading());
            return repository.save(note);
        } else {
            throw new IllegalArgumentException("Note not found with id: " + id);
        }
    }

    /**
     * Удаление заметку по id.
     */
    public void deleteNote(Long id) {
        repository.deleteById(id);
    }
}
