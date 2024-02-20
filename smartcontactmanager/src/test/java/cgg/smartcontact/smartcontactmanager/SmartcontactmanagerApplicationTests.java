package cgg.smartcontact.smartcontactmanager;

import cgg.smartcontact.smartcontactmanager.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmartcontactmanagerApplicationTests {

  @Autowired
  private UserRepository userRepo;

  @Test
  void contextLoads() {}

  @Test
  void testRepo() {
    System.out.println("testing repo");
    System.out.println("class name: " + userRepo.getClass());
    System.out.println(userRepo.getClass().getPackageName());
  }
}
