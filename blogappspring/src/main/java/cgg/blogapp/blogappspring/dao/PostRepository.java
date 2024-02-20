package cgg.blogapp.blogappspring.dao;

import cgg.blogapp.blogappspring.entities.Category;
import cgg.blogapp.blogappspring.entities.Post;
import cgg.blogapp.blogappspring.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
  List<Post> findByUser(User user);
  List<Post> findByCategory(Category category);

  List<Post> findByTitleContaining(String title);
}
