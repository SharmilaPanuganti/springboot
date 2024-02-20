package cgg.springbootjpa.springboojpaproj.dao;

import cgg.springbootjpa.springboojpaproj.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {
  //   @Query("select u from User u")
  //   public List<User> getUsers();
  @Query(value = "select * from user_table u", nativeQuery = true)
  public List<User> getUsers();

  @Query("select u from User u where u.name=:n")
  public List<User> getUsersByName(@Param("n") String name);

  @Query("select u from User u where u.name=:n and city=:c")
  public List<User> getUsersByNameAndCity(
    @Param("n") String name,
    @Param("c") String city
  );

  List<User> findByName(String name);

  List<User> findByNameAndCity(String name, String city);

  List<User> findByStatusContaining(String word);

  List<User> findByStatusLike(String pattern);
}
