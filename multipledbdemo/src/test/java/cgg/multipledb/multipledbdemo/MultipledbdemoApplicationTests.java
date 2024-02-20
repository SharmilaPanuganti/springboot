package cgg.multipledb.multipledbdemo;

import cgg.multipledb.multipledbdemo.entities.Product;
import cgg.multipledb.multipledbdemo.entities.User;
import cgg.multipledb.multipledbdemo.repos.ProductRepo;
import cgg.multipledb.multipledbdemo.repos.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MultipledbdemoApplicationTests {

  @Test
  void contextLoads() {}

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private ProductRepo productRepo;

  @Test
  public void dbTest() {
    User user = User.builder().firstName("a").id(12).build();
    Product product = Product
      .builder()
      .name("Apple iphone")
      .price(54000)
      .live(true)
      .description("this is apple product")
      .build();
    productRepo.save(product);
    userRepo.save(user);
    System.out.println("data saved");
  }

  @Test
  public void getData() {
    productRepo
      .findAll()
      .forEach(product -> System.out.println(product.getName()));
    userRepo.findAll().forEach(user -> System.out.println(user.getFirstName()));
  }
}
