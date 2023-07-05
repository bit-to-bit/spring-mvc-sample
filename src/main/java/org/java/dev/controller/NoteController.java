package org.java.dev.controller;

import org.java.dev.entity.Note;
import org.java.dev.exception.NoteServiceException;
import org.java.dev.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/note")
public class NoteController {
@Autowired
NoteService noteService;
    @GetMapping("/list")
    public ModelAndView getNoteList() {
        ModelAndView result = new ModelAndView("note/noteList");
        result.addObject("noteList", noteService.listAll());
        result.addObject("dtoNote",new Note(null, null, null));
        result.addObject("rrr", "priVet");
        return result;
    }
    @PostMapping("/delete")
    public String greetingSubmit(@ModelAttribute Note deleteNote, Model model) throws NoteServiceException {
        noteService.deleteById(deleteNote.getId());
        System.out.println("deleteNote = " + deleteNote);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public ModelAndView getEditNoteForm(@RequestParam(name = "id") long id, Model model){
        ModelAndView result = new ModelAndView("note/noteEdit");
        try {
            result.addObject("dtoNote",noteService.getById(id));
        } catch (NoteServiceException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @PostMapping("/edit")
    public String editNote(@ModelAttribute Note dtoNote, Model model){
        ModelAndView result = new ModelAndView("note/noteEdit");
        try {
            noteService.update(dtoNote);
        } catch (NoteServiceException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/note/list";
    }



}
