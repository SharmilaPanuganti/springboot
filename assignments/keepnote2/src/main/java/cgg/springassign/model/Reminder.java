package cgg.springassign.model;

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

  // Parameterized constructor
  public Reminder(
    String reminderName,
    String reminderDescription,
    String reminderType,
    String reminderCreatedBy
  ) {
    this.reminderName = reminderName;
    this.reminderDescription = reminderDescription;
    this.reminderType = reminderType;
    this.reminderCreatedBy = reminderCreatedBy;
    this.reminderCreationDate = new Date();
  }

  // Getters and Setters

  public Long getReminderId() {
    return reminderId;
  }

  public void setReminderId(Long reminderId) {
    this.reminderId = reminderId;
  }

  public String getReminderName() {
    return reminderName;
  }

  public void setReminderName(String reminderName) {
    this.reminderName = reminderName;
  }

  public String getReminderDescription() {
    return reminderDescription;
  }

  public void setReminderDescription(String reminderDescription) {
    this.reminderDescription = reminderDescription;
  }

  public String getReminderType() {
    return reminderType;
  }

  public void setReminderType(String reminderType) {
    this.reminderType = reminderType;
  }

  public String getReminderCreatedBy() {
    return reminderCreatedBy;
  }

  public void setReminderCreatedBy(String reminderCreatedBy) {
    this.reminderCreatedBy = reminderCreatedBy;
  }

  public Date getReminderCreationDate() {
    return reminderCreationDate;
  }

  public void setReminderCreationDate(Date reminderCreationDate) {
    this.reminderCreationDate = reminderCreationDate;
  }

  public List<Note> getNotes() {
    return notes;
  }

  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }

  @Override
  public String toString() {
    return (
      "Reminder{" +
      "reminderId=" +
      reminderId +
      ", reminderName='" +
      reminderName +
      '\'' +
      ", reminderDescription='" +
      reminderDescription +
      '\'' +
      ", reminderType='" +
      reminderType +
      '\'' +
      ", reminderCreatedBy='" +
      reminderCreatedBy +
      '\'' +
      ", reminderCreationDate=" +
      reminderCreationDate +
      ", notes=" +
      notes +
      '}'
    );
  }
}
