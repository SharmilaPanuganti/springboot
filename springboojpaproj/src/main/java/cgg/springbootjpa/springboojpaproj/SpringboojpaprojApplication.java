package cgg.springbootjpa.springboojpaproj;

import cgg.springbootjpa.springboojpaproj.dao.UserRepository;
import cgg.springbootjpa.springboojpaproj.entities.User;
import java.util.List;
import java.util.Optional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringboojpaprojApplication {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(
      SpringboojpaprojApplication.class,
      args
    );
    UserRepository ur = context.getBean(UserRepository.class);
    User user = new User();
    user.setName("Madhu");
    user.setCity("DRM");
    user.setStatus("Employee");
    User user2 = new User();
    user2.setName("Sharmila");
    user2.setCity("HYD");
    user2.setStatus("Software developer");

    // List<User> users = List.of(user, user2);
    // Iterable<User> savedUsers = ur.saveAll(users);
    // savedUsers.forEach(System.out::println);
    // System.out.println("saved. " +u);

    // updating
    // Optional<User> u1 = ur.findById(2);
    // User u2 = u1.get();
    // u2.setCity("Delhi");
    // User updated = ur.save(u2);
    // System.out.println(updated);

    //retreival
    // Iterable<User> all = ur.findAll();
    // all.forEach(System.out::println);

    //deleting
    // ur.deleteById(52);
    // ur.deleteAll(all);
    // System.out.println("deleted");

    //custom retrieval
    //     List<User> byName = ur.findByName("Madhu");
    //     byName.forEach(System.out::println);
    // List<User> byNameAndCity = ur.findByNameAndCity("SAI", "HYD");
    // byNameAndCity.forEach(System.out::println);

    //query methods
    // List<User> users = ur.getUsers();
    // users.forEach(System.out::println);
    // List<User> users = ur.getUsersByName("SAI");
    // users.forEach(System.out::println);
    List<User> byStatusLike = ur.findByStatusLike("t%");
    byStatusLike.forEach(System.out::println);
    List<User> byStatusContaining = ur.findByStatusContaining("Software");
    byStatusContaining.forEach(System.out::println);
  }
}
