package cgg.keepnotes.keepnoteproj.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "user_password")
  private String userPassword;

  @Column(name = "user_mobile")
  private String userMobile;

  @Column(name = "user_added_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date userAddedDate;

  // No-arg constructor
  public User() {
    this.userAddedDate = new Date();
  }
}
