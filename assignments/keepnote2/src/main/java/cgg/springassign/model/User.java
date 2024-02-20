package cgg.springassign.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
public class User implements Serializable {

  private static final long serialVersionUID = -7788619177798333712L;

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

  // Parameterized constructor
  public User(String userName, String userPassword, String userMobile) {
    this.userName = userName;
    this.userPassword = userPassword;
    this.userMobile = userMobile;
    this.userAddedDate = new Date();
  }

  // Getters and Setters

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public String getUserMobile() {
    return userMobile;
  }

  public void setUserMobile(String userMobile) {
    this.userMobile = userMobile;
  }

  @JsonSerialize(using = DateSerializer.class)
  public Date getUserAddedDate() {
    return userAddedDate;
  }

  public void setUserAddedDate(Date userAddedDate) {
    this.userAddedDate = userAddedDate;
  }

  @Override
  public String toString() {
    return (
      "User{" +
      "userId=" +
      userId +
      ", userName='" +
      userName +
      '\'' +
      ", userPassword='" +
      userPassword +
      '\'' +
      ", userMobile='" +
      userMobile +
      '\'' +
      ", userAddedDate=" +
      userAddedDate +
      '}'
    );
  }
}
