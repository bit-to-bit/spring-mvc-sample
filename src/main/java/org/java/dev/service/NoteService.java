package org.java.dev.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.java.dev.entity.Note;
import org.java.dev.exception.NoteServiceException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Service
@NoArgsConstructor
public class NoteService {
    private final List<Note> notes = new ArrayList<>();
    private static final long MAX_KEY = 9223372036854775806L;

    public List<Note> listAll() {
        return this.notes;
    }

    public Note add(Note note) throws NoteServiceException {
        if (Objects.isNull(note)) return null;
        note.setId(generateNoteId());
        notes.add(note);
        return note;
    }

    public void deleteById(long id) throws NoteServiceException {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == id) {
                notes.remove(i);
                return;
            }
        }
        throw new NoteServiceException("Note id = " + id + " didn't delete, because it was not found");
    }

    public void update(Note note) throws NoteServiceException {
        Long id = note.getId();
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId().equals(id)) {
                notes.get(i).setTitle(note.getTitle());
                notes.get(i).setContent(note.getContent());
                return;
            }
        }
        throw new NoteServiceException("Note id = " + id + " didn't delete, because it was not found");
    }

    public Note getById(long id) throws NoteServiceException {
        for (Note note : notes) {
            if (note.getId() == id) return note;
        }
        throw new NoteServiceException("Note id = " + id + "was not found");
    }

    public void generateNotes(int numberOfNotes) {
        for (int i = 0; i < numberOfNotes; i++) {
            String contentPhrase = "The quick brown fox jumps over the lazy dog... ";
            try {
                this.add(new Note(null, "Note " + i, StringUtils.repeat(contentPhrase, i)));
            } catch (NoteServiceException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private long generateNoteId() {
        Long id = null;
        while (Objects.isNull(id)) {
            long key = (long) (Math.random() * (MAX_KEY + 1L));
            try {
                this.getById(key);
            } catch (NoteServiceException e) {
                id = key;
            }
        }
        return id;
    }
}
