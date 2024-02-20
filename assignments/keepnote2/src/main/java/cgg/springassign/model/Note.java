package cgg.springassign.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

/*
 * The class "Note" will be acting as the data model for the note Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the
 * process of looking through that particular Java object to recreate it as a table in your database.
 */
@Entity
public class Note {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "note_id")
  private int noteId;

  @Column(name = "note_title")
  private String noteTitle;

  @Column(name = "note_content")
  private String noteContent;

  @Column(name = "note_status")
  private String noteStatus;

  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "reminder_id")
  private Reminder reminder;

  @Column(name = "created_by")
  private String createdBy;

  // No-arg constructor
  public Note() {
    this.createdAt = new Date();
  }

  // Parameterized constructor
  public Note(
    String noteTitle,
    String noteContent,
    String noteStatus,
    Category category,
    Reminder reminder,
    String createdBy
  ) {
    this.noteTitle = noteTitle;
    this.noteContent = noteContent;
    this.noteStatus = noteStatus;
    this.category = category;
    this.reminder = reminder;
    this.createdBy = createdBy;
    this.createdAt = new Date();
  }

  // Getters and Setters

  public int getNoteId() {
    return noteId;
  }

  public void setNoteId(int noteId) {
    this.noteId = noteId;
  }

  public String getNoteTitle() {
    return noteTitle;
  }

  public void setNoteTitle(String noteTitle) {
    this.noteTitle = noteTitle;
  }

  public String getNoteContent() {
    return noteContent;
  }

  public void setNoteContent(String noteContent) {
    this.noteContent = noteContent;
  }

  public String getNoteStatus() {
    return noteStatus;
  }

  public void setNoteStatus(String noteStatus) {
    this.noteStatus = noteStatus;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Reminder getReminder() {
    return reminder;
  }

  public void setReminder(Reminder reminder) {
    this.reminder = reminder;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }
}
