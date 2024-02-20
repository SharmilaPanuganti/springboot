package cgg.keepnotes.keepnoteproj.respository;

import cgg.keepnotes.keepnoteproj.entities.Note;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class NoteRepository {

  

  public NoteRepository() {
    notes = new ArrayList<>();
    /* Initialize the variable using proper data type */
  }

  /* This method should return all the notes in the list */

  public List<Note> getList() {
    return notes;
  }

  /* This method should set the list variable with new list of notes */

  public void setList(List<Note> list) {}

  /*
   * This method should Note object as argument and add the new note object into
   * list
   */

  public void addNote(Note note) {
    notes.add(note);
  }

  /* This method should deleted a specified note from the list */

  public boolean deleteNote(int noteId) {
    /* Use list iterator to find matching note id and remove it from the list */
    if (exists(noteId)) {
      notes = notes.stream().filter(n -> n.getNoteId() != noteId).toList();
      return true;
    }
    return false;
  }

  /* This method should return the list of notes */

  public List<Note> getAllNotes() {
    return notes;
  }

  /*
   * This method should check if the matching note id present in the list or not.
   * Return true if note id exists in the list or return false if note id does not
   * exists in the list
   */

  public boolean exists(int noteId) {
    if (
      notes.stream().filter(n -> n.getNoteId() == noteId).findAny().isPresent()
    ) return true;
    return false;
  }
}
