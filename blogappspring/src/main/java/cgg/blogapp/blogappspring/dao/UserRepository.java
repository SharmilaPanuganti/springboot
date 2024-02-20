package cgg.blogapp.blogappspring.dao;

import cgg.blogapp.blogappspring.entities.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  public Optional<User> findByUsername(String username);
}
