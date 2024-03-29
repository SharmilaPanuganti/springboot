package cgg.springassign.dao;

import cgg.springassign.model.Note;
import java.util.List;

public interface NoteDAO {
  /* You Should not modify this interface.  You have to implement these methods in corresponding Impl class*/

  public boolean saveNote(Note note);

  public boolean deleteNote(int noteId);

  public List<Note> getAllNotes();

  public Note getNoteById(int noteId);

  public boolean UpdateNote(Note note);
}
