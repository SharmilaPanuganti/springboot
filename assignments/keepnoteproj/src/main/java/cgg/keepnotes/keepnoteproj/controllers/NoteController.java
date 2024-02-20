package cgg.keepnotes.keepnoteproj.controllers;

import cgg.keepnotes.keepnoteproj.entities.Note;
import cgg.keepnotes.keepnoteproj.respository.NoteRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NoteController {

  @Autowired
  private NoteRepository noteRepo;

  /*Define a handler method to read the existing notes by calling the getAllNotes() method
   * of the NoteRepository class and add it to the ModelMap which is an implementation of Map
   * for use when building model data for use with views. it should map to the default URL i.e. "/" */
  @GetMapping("/")
  public String getAllNotes(ModelMap model) {
    List<Note> notes = noteRepo.getAllNotes();
    model.addAttribute("notes", notes);
    return "index";
  }

  /*Define a handler method which will read the Note data from request parameters and
   * save the note by calling the addNote() method of NoteRepository class. Please note
   * that the createdAt field should always be auto populated with system time and should not be accepted
   * from the user. Also, after saving the note, it should show the same along with existing
   * notes. Hence, reading notes has to be done here again and the retrieved notes object
   * should be sent back to the view using ModelMap.
   * This handler method should map to the URL "/saveNote".
   */
  @PostMapping("/saveNote")
  public String addNote(@ModelAttribute Note n) {
    noteRepo.addNote(n);
    return "redirect:/";
  }

  /* Define a handler method to delete an existing note by calling the deleteNote() method
   * of the NoteRepository class
   * This handler method should map to the URL "/deleteNote"
   */
  @GetMapping("/deleteNote/{id}")
  public String deletNote(@PathVariable int id) {
    noteRepo.deleteNote(id);
    return "index";
  }

  @GetMapping("/addNote")
  public String addNoteForm() {
    return "addNote";
  }
}
