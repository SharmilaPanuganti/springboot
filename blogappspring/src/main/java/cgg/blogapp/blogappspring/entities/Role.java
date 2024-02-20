package cgg.blogapp.blogappspring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Role {

  @Id
  private int id;

  private String name;
}
