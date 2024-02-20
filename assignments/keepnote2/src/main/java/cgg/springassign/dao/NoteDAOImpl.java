package cgg.springassign.dao;

import cgg.springassign.model.Note;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
 * This class is implementing the NoteDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database
 * 					transaction. The database transaction happens inside the scope of a persistence
 * 					context.
 * */
@Repository("noteDAO")
public class NoteDAOImpl implements NoteDAO {

  /*
   * Autowiring should be implemented for the SessionFactory.
   */
  @Autowired
  private SessionFactory factory;

  public NoteDAOImpl(SessionFactory sessionFactory) {
    this.factory = sessionFactory;
  }

  /*
   * Save the note in the database(note) table.
   */
  @Transactional
  public boolean saveNote(Note note) {
    boolean f = false;
    factory.getCurrentSession().persist(note);
    f = true;
    return f;
  }

  /*
   * Remove the note from the database(note) table.
   */
  @Transactional
  public boolean deleteNote(int noteId) {
    boolean f = false;
    Note note = getNoteById(noteId);
    factory.getCurrentSession().remove(note);
    f = true;
    return f;
  }

  /*
   * retrieve all existing notes sorted by created Date in descending
   * order(showing latest note first)
   */
  @Transactional
  public List<Note> getAllNotes() {
    List<Note> notes = factory
      .getCurrentSession()
      .createQuery("from Note", Note.class)
      .getResultList();
    return notes;
  }

  /*
   * retrieve specific note from the database(note) table
   */
  @Transactional
  public Note getNoteById(int noteId) {
    Note note = factory.getCurrentSession().get(Note.class, noteId);
    return note;
  }

  /* Update existing note */
  @Transactional
  public boolean UpdateNote(Note note) {
    boolean f = false;
    factory.getCurrentSession().merge(note);
    f = true;
    return f;
  }
}
