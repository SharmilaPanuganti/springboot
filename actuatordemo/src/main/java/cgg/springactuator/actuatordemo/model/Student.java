package cgg.springactuator.actuatordemo.model;

import org.springframework.stereotype.Component;

@Component
public class Student {

  public Student() {
    System.out.println("Creating student object");
  }
}
