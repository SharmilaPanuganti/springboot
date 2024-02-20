package cgg.springassign.service;

import cgg.springassign.dao.NoteDAO;
import cgg.springassign.exceptions.CategoryNotFoundException;
import cgg.springassign.exceptions.NoteNotFoundException;
import cgg.springassign.exceptions.ReminderNotFoundException;
import cgg.springassign.model.Note;
import java.util.List;
import org.eclipse.tags.shaded.org.apache.regexp.recompile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

  @Autowired
  private NoteDAO noteDAO;

  public NoteServiceImpl(NoteDAO noteDAO) {
    this.noteDAO = noteDAO;
  }

  @Override
  public boolean createNote(Note note)
    throws ReminderNotFoundException, CategoryNotFoundException {
    boolean f = noteDAO.saveNote(note);

    return f;
  }

  @Override
  public boolean deleteNote(int noteId) {
    boolean deleteNote = noteDAO.deleteNote(noteId);
    return deleteNote;
  }

  @Override
  public List<Note> getAllNotesByUserId(String userId) {
    List<Note> allNotes = noteDAO.getAllNotes();
    return allNotes;
  }

  @Override
  public Note getNoteById(int noteId) throws NoteNotFoundException {
    return noteDAO.getNoteById(noteId);
  }

  @Override
  public Note updateNote(Note note, int id)
    throws ReminderNotFoundException, NoteNotFoundException, CategoryNotFoundException {
    boolean updated = noteDAO.UpdateNote(note);
    return note;
  }
}
