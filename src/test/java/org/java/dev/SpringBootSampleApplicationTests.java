package org.java.dev;

import org.java.dev.entity.Note;
import org.java.dev.exception.NoteServiceException;
import org.java.dev.service.NoteService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class SpringBootSampleApplicationTests {
    @Autowired
    NoteService noteService;

    @BeforeEach
    void init() {
    }

    @Order(1)
    @Test
    void noteServiceAdd() throws NoteServiceException {
        System.out.println("\n====== ADD ======\n");
        long expected = noteService.listAll().size() + 10;
        for (int i = 10; i < 20; i++) {
            noteService.add(new Note(null, "Note_" + i, "Content_" + i));
        }
        System.out.println("All notes after add:");
        noteService.listAll().forEach(e -> System.out.println(e.toString()));
        long actual = noteService.listAll().size();
        Assertions.assertEquals(expected, actual);
    }

    @Order(2)
    @Test
    void noteServiceUpdate() throws NoteServiceException {
        System.out.println("\n====== UPDATE ======\n");
        Note tmpNote = noteService.listAll().get(0);
        System.out.println("BEFORE UPDATE tmpNode = " + tmpNote);
        tmpNote.setTitle("Note_UPDATED");
        tmpNote.setContent("Content_UPDATED");
        noteService.update(tmpNote);
        System.out.println("AFTER UPDATE tmpNode = " + noteService.getById(tmpNote.getId()));
        String actual = noteService.getById(tmpNote.getId()).getTitle();
        String expected = "Note_UPDATED";
        System.out.println("All notes after update:");
        noteService.listAll().forEach(e -> System.out.println(e.toString()));
        Assertions.assertEquals(expected, actual);
    }

    @Order(3)
    @Test
    void noteServiceDelete() throws NoteServiceException {
        System.out.println("\n====== DELETE ======\n");
        long expected = noteService.listAll().size() - 1;
        noteService.deleteById(noteService.listAll().get(0).getId());
        System.out.println("All notes after delete:");
        noteService.listAll().forEach(e -> System.out.println(e.toString()));
        long actual = noteService.listAll().size();
        Assertions.assertEquals(expected, actual);
    }
}
