package cgg.blogapp.blogappspring.payloads;

import cgg.blogapp.blogappspring.entities.Category;
import cgg.blogapp.blogappspring.entities.Comment;
import cgg.blogapp.blogappspring.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

  private Integer postId;

  @NotEmpty(message = "Title should not be empty")
  @Size(min = 5, max = 20, message = "Should be between 5 to 20 characters")
  private String title;

  @NotEmpty(message = "Content should not be empty")
  private String content;

  private String imageName;

  private LocalDateTime addedDate;

  // @JsonIgnore
  private Category category;

  // @JsonIgnore
  private User user;

  private Set<Comment> comments = new HashSet<>();
}
