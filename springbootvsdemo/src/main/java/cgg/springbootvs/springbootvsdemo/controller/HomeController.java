package cgg.springbootvs.springbootvsdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/home")
  public String homeHandler() {
    System.out.println("home handler");
    return "index";
  }

  @GetMapping("/about")
  public String about() {
    System.out.println("about page");
    return "about";
  }
}
