package com.stackroute.keepnote.controller;

import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.model.Note;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Annotate the class with @Controller annotation.@Controller annotation is used to mark
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 */
@Controller
public class NoteController {

  @Autowired
  private NoteDAO noteDAO;

  public NoteController(NoteDAO noteDao) {
    //TODO Auto-generated constructor stub
    this.noteDAO = noteDao;
  }

  /*
   * From the problem statement, we can understand that the application requires
   * us to implement the following functionalities.
   *
   * 1. display the list of existing notes from the persistence data. Each note
   * should contain Note Id, title, content, status and created date.
   * 2. Add a new note which should contain the note id, title, content and status.
   * 3. Delete an existing note
   * 4. Update an existing note
   *
   */

  /*
   * Autowiring should be implemented for the NoteDAO.
   * Create a Note object.
   *
   */

  /*
   * Define a handler method to read the existing notes from the database and add
   * it to the ModelMap which is an implementation of Map, used when building
   * model data for use with views. it should map to the default URL i.e. "/index"
   */
  @GetMapping("/")
  public String getAllNotes(ModelMap model) {
    List<Note> notes = noteDAO.getAllNotes();
    model.addAttribute("notes", notes);
    return "index";
  }

  /*
   * Define a handler method which will read the NoteTitle, NoteContent,
   * NoteStatus from request parameters and save the note in note table in
   * database. Please note that the CreatedAt should always be auto populated with
   * system time and should not be accepted from the user. Also, after saving the
   * note, it should show the same along with existing messages. Hence, reading
   * note has to be done here again and the retrieved notes object should be sent
   * back to the view using ModelMap This handler method should map to the URL
   * "/add".
   */
  @PostMapping("/add")
  public String addNote(@ModelAttribute Note n) {
    n.setCreatedAt(LocalDateTime.now());
    noteDAO.saveNote(n);
    return "redirect:/";
  }

  /*
   * Define a handler method which will read the NoteId from request parameters
   * and remove an existing note by calling the deleteNote() method of the
   * NoteRepository class.This handler method should map to the URL "/delete".
   */
  @GetMapping("/delete/{id}")
  public String deletNote(@PathVariable int id) {
    noteDAO.deleteNote(id);
    return "redirect:/";
  }

  /*
   * Define a handler method which will update the existing note. This handler
   * method should map to the URL "/update".
   */
  @PostMapping("/update/{id}")
  public String update(@ModelAttribute Note note) {
    noteDAO.UpdateNote(note);
    return "redirect:/";
  }
}
