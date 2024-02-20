package cgg.blogapp.blogappspring.payloads;

import cgg.blogapp.blogappspring.entities.Post;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

  private int id;

  @NotEmpty
  private String name;

  @NotNull
  private String desc;

  private List<Post> posts = new ArrayList<>();
}
