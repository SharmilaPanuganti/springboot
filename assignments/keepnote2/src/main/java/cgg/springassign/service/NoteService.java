package cgg.springassign.service;

import cgg.springassign.exceptions.CategoryNotFoundException;
import cgg.springassign.exceptions.NoteNotFoundException;
import cgg.springassign.exceptions.ReminderNotFoundException;
import cgg.springassign.model.Note;
import java.util.List;

public interface NoteService {
  public boolean createNote(Note note)
    throws ReminderNotFoundException, CategoryNotFoundException;

  public boolean deleteNote(int noteId);

  public List<Note> getAllNotesByUserId(String userId);

  public Note getNoteById(int noteId) throws NoteNotFoundException;

  public Note updateNote(Note note, int id)
    throws ReminderNotFoundException, NoteNotFoundException, CategoryNotFoundException, ReminderNotFoundException, NoteNotFoundException;
}
