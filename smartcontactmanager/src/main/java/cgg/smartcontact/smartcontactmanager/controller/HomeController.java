package cgg.smartcontact.smartcontactmanager.controller;

import cgg.smartcontact.smartcontactmanager.entities.Message;
import cgg.smartcontact.smartcontactmanager.entities.User;
import cgg.smartcontact.smartcontactmanager.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @GetMapping("/home")
  public String home(Model model) {
    model.addAttribute("title", "Home-Smart Contact Manager");
    return "home";
  }

  @GetMapping("/about")
  public String about(Model model) {
    model.addAttribute("title", "About-Smart Contact Manager");
    return "about";
  }

  @GetMapping("/signup")
  public String signUp(Model model) {
    model.addAttribute("title", "Register-smart Contact Manager");
    model.addAttribute("user", new User());
    return "signup";
  }

  @PostMapping(value = "/do_register")
  public String registerUSer(
    @Valid @ModelAttribute("user") User user,
    BindingResult res,
    @RequestParam(
      value = "agreement",
      defaultValue = "false"
    ) boolean agreement,
    Model model,
    HttpSession session
  ) {
    try {
      if (res.hasErrors()) {
        System.out.println("ERROR" + res.toString());
        model.addAttribute("user", user);
        return "signup";
      }
      if (!agreement) {
        System.out.println("You have not agreed the terms and conditions");
        throw new Exception("agree to terms and conditions");
      }
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setRole("ROLE_USER");
      user.setEnabled(true);
      user.setImageUrl("default.png");
      System.out.println("Agreement" + agreement);
      System.out.println("USER" + user);
      User result = this.userRepository.save(user);

      model.addAttribute("user", new User());
      session.setAttribute(
        "msg",
        new Message("Successfully Registetred !!", "alert-success")
      );
      model.addAttribute("user", result);
      return "signup";
    } catch (Exception e) {
      e.printStackTrace();
      model.addAttribute("user", user);
      session.setAttribute(
        "msg",
        new Message("Something went wrong!!", "alert-danger")
      );
      return "signup";
    }
  }

  @GetMapping("/signin")
  public String customLogin(Model model) {
    model.addAttribute("title", "Login Page");
    return "login";
  }
}
