package cgg.keepnotes.keepnoteproj.respository;

import cgg.keepnotes.keepnoteproj.entities.Note;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class NoteDAOImpl implements NoteDAO {

  private SessionFactory factory;

  @Override
  public boolean createNote(Note note) {
   
  }

  @Override
  public boolean deleteNote(int noteId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'deleteNote'"
    );
  }

  @Override
  public List<Note> getAllNotesByUserId(String userId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'getAllNotesByUserId'"
    );
  }

  @Override
  public Note getNoteById(int noteId) throws NoteNotFoundException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'getNoteById'"
    );
  }

  @Override
  public boolean UpdateNote(Note note) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'UpdateNote'"
    );
  }
}
