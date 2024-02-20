package cgg.springassign.controller;

import cgg.springassign.dao.NoteDAO;
import cgg.springassign.exceptions.CategoryNotFoundException;
import cgg.springassign.exceptions.NoteNotFoundException;
import cgg.springassign.exceptions.ReminderNotFoundException;
import cgg.springassign.model.Note;
import cgg.springassign.service.NoteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Annotate the class with @Controller annotation.@Controller annotation is used to mark
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 */
@RestController
public class NoteController {

  @Autowired
  private NoteService noteService;

  public NoteController(NoteService noteService) {
    //TODO Auto-generated constructor stub
    this.noteService = noteService;
  }

  @GetMapping("/notes")
  @Transactional
  public String getAllNotes(ModelMap model) {
    List<Note> notes = noteService.getAllNotesByUserId(null);

    return "index";
  }

  @PostMapping("/add")
  @Transactional
  public String addNote(@ModelAttribute Note n)
    throws ReminderNotFoundException, CategoryNotFoundException {
    noteService.createNote(n);
    return "redirect:/notes";
  }

  @GetMapping("/delete/{id}")
  @Transactional
  public String deletNote(@PathVariable("id") int id) {
    noteService.deleteNote(id);
    return "redirect:/notes";
  }

  @PostMapping("/update/{id}")
  @Transactional
  public String update(
    @ModelAttribute Note note,
    Model model,
    @PathVariable("id") int id
  )
    throws ReminderNotFoundException, NoteNotFoundException, CategoryNotFoundException {
    note.setNoteId(id);
    noteService.updateNote(note, id);
    model.addAttribute("page", "update");
    return "redirect:/notes";
  }
}
