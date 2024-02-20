package cgg.springrestapi.springwebapiproj.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Author {

  @Id
  @GeneratedValue
  private int author_id;

  private String name;
  private String language;

  @OneToOne(mappedBy = "author")
  @JsonBackReference
  private Book books;
}
