package cgg.smartcontact.smartcontactmanager.repositories;

import cgg.smartcontact.smartcontactmanager.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  public User findByName(String name);
}
