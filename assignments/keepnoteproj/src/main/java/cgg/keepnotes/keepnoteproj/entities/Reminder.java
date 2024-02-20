package cgg.keepnotes.keepnoteproj.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "reminders")
public class Reminder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "reminder_id")
  private Long reminderId;

  @Column(name = "reminder_name")
  private String reminderName;

  @Column(name = "reminder_description")
  private String reminderDescription;

  @Column(name = "reminder_type")
  private String reminderType;

  @Column(name = "reminder_created_by")
  private String reminderCreatedBy;

  @Column(name = "reminder_creation_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date reminderCreationDate;

  @OneToMany(
    mappedBy = "reminder",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  @JsonIgnore
  private List<Note> notes;

  // No-arg constructor
  public Reminder() {
    this.reminderCreationDate = new Date();
  }
}
