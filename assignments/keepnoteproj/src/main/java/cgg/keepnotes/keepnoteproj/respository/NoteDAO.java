package cgg.keepnotes.keepnoteproj.respository;

import cgg.keepnotes.keepnoteproj.entities.Note;
import java.util.List;

public interface NoteDAO {
  /*
   * Should not modify this interface. You have to implement these methods in
   * corresponding Impl classes
   */

  public boolean createNote(Note note);

  public boolean deleteNote(int noteId);

  public List<Note> getAllNotesByUserId(String userId);

  public Note getNoteById(int noteId) throws NoteNotFoundException;

  public boolean UpdateNote(Note note);
}
