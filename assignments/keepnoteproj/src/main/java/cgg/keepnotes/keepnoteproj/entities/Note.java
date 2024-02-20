package cgg.keepnotes.keepnoteproj.entities;

import java.time.LocalDate;
import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Note {

   @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "note_id")
  private Long noteId;

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
  public Note(){
    createdAt=new Date();
  }

}
