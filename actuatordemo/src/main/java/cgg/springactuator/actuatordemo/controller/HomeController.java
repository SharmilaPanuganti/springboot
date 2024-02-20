package cgg.springactuator.actuatordemo.controller;

import cgg.springactuator.actuatordemo.model.Student;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @Autowired
  private Student student;

  @GetMapping("/get-data")
  public Map<String, String> getData() {
    return Map.of("name", "Sharmila");
  }
}
