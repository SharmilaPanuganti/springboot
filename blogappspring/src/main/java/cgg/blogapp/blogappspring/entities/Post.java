package cgg.blogapp.blogappspring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "posts")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer postId;

  @Column(name = "post_title", length = 100, nullable = false)
  private String title;

  @Column(length = 10000)
  private String content;

  private String imageName;

  @Temporal(TemporalType.TIMESTAMP)
  private final LocalDateTime addedDate = LocalDateTime.now();

  @ManyToOne
  @JoinColumn(name = "category_id")
  // @JsonIgnore
  private Category category;

  @ManyToOne
  @JoinColumn(name = "user_id")
  // @JsonIgnore
  private User user;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
  private Set<Comment> comments = new HashSet<>();
}
