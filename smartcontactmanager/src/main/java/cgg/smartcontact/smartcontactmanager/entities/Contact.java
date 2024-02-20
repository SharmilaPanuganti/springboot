package cgg.smartcontact.smartcontactmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contact")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int cId;

  private String name;
  private String secondName;
  private String work;
  private String email;
  private String phone;
  private String image;

  @Column(length = 50000)
  private String description;

  @ManyToOne
  @JsonIgnore
  private User user;
}
