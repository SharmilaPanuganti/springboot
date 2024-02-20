package cgg.springthyme.thymeleafproj.controllers;

import cgg.springthyme.thymeleafproj.entities.LoginData;
import jakarta.validation.Valid;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

  @GetMapping("/about")
  public String about(Model m) {
    System.out.println("about handler");
    m.addAttribute("name", "sharmila");
    m.addAttribute("date", new Date());
    return "about";
  }

  @GetMapping("/iterator")
  public String iterationHandler(Model m) {
    System.out.println("iteration handler");
    //collections
    List<String> names = List.of("Sharmila", "Sai", "Madhu");
    m.addAttribute("names", names);
    return "iteratorEx";
  }

  @GetMapping("/condition")
  public String conditionalHandler(Model m) {
    System.out.println("Condition handler");
    m.addAttribute("gender", "F");
    m.addAttribute("isActive", false);
    List<Integer> list = List.of(1, 2, 3, 4, 5);
    m.addAttribute("mylist", list);
    return "condition";
  }

  @GetMapping("/service")
  public String service(Model m) {
    System.out.println("fragement handler");
    m.addAttribute("title", "CGG");
    m.addAttribute("subtitle", "thymeleaf");
    return "service";
  }

  @GetMapping("/contact")
  public String contact() {
    return "contact";
  }

  @GetMapping("/fragmentinh")
  public String fragmentInh() {
    return "fragmentInh";
  }

  @GetMapping("/form")
  public String showForm(Model m) {
    m.addAttribute("loginData", new LoginData());
    System.out.println("showing form");
    return "form";
  }

  @PostMapping("/process")
  public String formHandler(
    @Valid @ModelAttribute LoginData loginData,
    BindingResult result
  ) {
    System.out.println(loginData);
    //process
    if (result.hasErrors()) {
      System.out.println(result);
      return "form";
    }
    return "success";
  }
}
