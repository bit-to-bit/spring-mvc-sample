package org.java.dev.startup;

import org.java.dev.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RunAfterStartup {
    @Autowired
    NoteService noteService;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        int numberOfNotes = 50;
        noteService.generateNotes(numberOfNotes);
        System.out.println("Yaaah, I am running........ and create " + numberOfNotes + " Notes!");
    }
}