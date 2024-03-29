package com.stackroute.keepnote.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

/*
 * The class "Note" will be acting as the data model for the note Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the
 * process of looking through that particular Java object to recreate it as a table in your database.
 */
@Entity
public class Note {

  @Id
  @GeneratedValue
  private int noteId;

  private String noteTitle;
  private String noteContent;
  private String noteStatus;
  private LocalDateTime createdAt;

  public Note(
    int noteId,
    String noteTitle,
    String noteContent,
    String noteStatus,
    LocalDateTime createdAt
  ) {
    this.noteId = noteId;
    this.noteTitle = noteTitle;
    this.noteContent = noteContent;
    this.noteStatus = noteStatus;
    this.createdAt = createdAt;
  }

  public Note() {}

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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
