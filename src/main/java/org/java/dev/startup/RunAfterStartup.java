package org.java.dev.startup;

import org.java.dev.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RunAfterStartup {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    @Autowired
    NoteService noteService;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        int numberOfNotes = 42;
        noteService.generateNotes(numberOfNotes);
        System.out.println(ANSI_CYAN+ "Yaaah, I am running........ and create " + numberOfNotes + " Notes!"+ ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Try me by link: http://localhost:8080/note/list" + ANSI_RESET);

    }
}