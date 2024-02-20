package cgg.multipledb.multipledbdemo.repos;

import cgg.multipledb.multipledbdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {}
