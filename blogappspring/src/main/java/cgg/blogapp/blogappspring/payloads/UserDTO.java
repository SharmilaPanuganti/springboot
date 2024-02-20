package cgg.blogapp.blogappspring.payloads;

import cgg.blogapp.blogappspring.entities.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

  private int id;

  @NotEmpty
  private String username;

  @NotEmpty
  private String password;

  @NotEmpty
  @Email
  private String email;

  @NotEmpty
  private String about;

  // @JsonIgnore
  private List<Post> posts;

  private String role;
}
