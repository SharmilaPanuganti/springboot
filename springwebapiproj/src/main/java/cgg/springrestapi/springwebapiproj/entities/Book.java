package cgg.springrestapi.springwebapiproj.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book_table")
public class Book {

  @Id
  @GeneratedValue
  private int book_id;

  private String title;

  // private String author;
  @OneToOne(cascade = CascadeType.ALL)
  @JsonManagedReference
  private Author author;
}
