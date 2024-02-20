package cgg.multipledb.multipledbdemo.repos;

import cgg.multipledb.multipledbdemo.entities.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
  List<Product> findByName(String name);
}
