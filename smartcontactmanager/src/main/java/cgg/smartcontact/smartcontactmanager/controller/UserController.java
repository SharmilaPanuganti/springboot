package cgg.smartcontact.smartcontactmanager.controller;

import cgg.smartcontact.smartcontactmanager.entities.Contact;
import cgg.smartcontact.smartcontactmanager.entities.Message;
import cgg.smartcontact.smartcontactmanager.entities.User;
import cgg.smartcontact.smartcontactmanager.repositories.ContactRepository;
import cgg.smartcontact.smartcontactmanager.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ContactRepository contactRepository;

  @GetMapping("/index")
  public String dashboard(Model model, Principal principal) {
    String userName = principal.getName();
    System.out.println("USERNAME" + userName);
    User user = userRepository.findByName(userName);
    System.out.println("USER" + user);
    model.addAttribute("user", user);

    return "normal/user_dashboard";
  }

  @ModelAttribute
  public void addCommonData(Model model, Principal principal) {
    String userName = principal.getName();
    System.out.println("USERNAME" + userName);
    User user = userRepository.findByName(userName);
    System.out.println("USER" + user);
    model.addAttribute("user", user);
  }

  @GetMapping("/add-contact")
  public String openAddContactForm(Model model) {
    model.addAttribute("title", "Add Contact");
    model.addAttribute("contact", new Contact());
    return "normal/add-contact";
  }

  @PostMapping("/process-contact")
  public String processContact(
    @ModelAttribute Contact contact,
    Principal principal,
    @RequestParam("profileImage") MultipartFile file,
    HttpSession session
  ) throws IOException {
    try {
      String name = principal.getName();
      User user = this.userRepository.findByName(name);
      contact.setUser(user);

      System.out.println("DATA" + contact);
      System.out.println("Added to data base");
      if (file.isEmpty()) {
        System.out.println("File is empty");
        contact.setImage("contact.png");
      } else {
        contact.setImage(file.getOriginalFilename());
        System.out.println(contact.getImage());
        File saveFile = new ClassPathResource("static/img").getFile();
        Path path = Paths.get(
          saveFile.getAbsolutePath() +
          File.separator +
          file.getOriginalFilename()
        );
        Files.copy(
          file.getInputStream(),
          path,
          StandardCopyOption.REPLACE_EXISTING
        );
        user.getContacts().add(contact);
        this.userRepository.save(user);
        System.out.println(path);
        System.out.println("Image is uploded");
        contact.setImage(name);
        session.setAttribute(
          "msg",
          new Message("Your contact is added !! Add more..", "alert-success")
        );
      }
    } catch (Exception e) {
      session.setAttribute(
        "msg",
        new Message("Something went wrong !! try again..", "alert-danger")
      );
      System.out.println("Error adding " + e.getMessage());
    }

    return "redirect:/users/add-contact";
  }

  //show contacts handler
  @GetMapping("/show_contacts/{page}")
  public String showContacts(
    Model m,
    Principal principal,
    @PathVariable("page") Integer page
  ) {
    m.addAttribute("title", "Show User Contacts");
    String userName = principal.getName();
    User user = this.userRepository.findByName(userName);
    Pageable pageable = PageRequest.of(page, 5);
    Page<Contact> contacts = this.contactRepository.findByUser(user, pageable);
    m.addAttribute("contacts", contacts);
    m.addAttribute("contacts", contacts);
    m.addAttribute("currentPage", page);
    m.addAttribute("totalPages", contacts.getTotalPages());
    return "normal/show_contacts";
  }

  @GetMapping("/{cid}/contact")
  public String showContactDetail(
    @PathVariable("cid") Integer cId,
    Model model
  ) {
    System.out.println("CID" + cId);
    Optional<Contact> contactOptional = this.contactRepository.findById(cId);
    Contact contact = contactOptional.get();
    model.addAttribute("contact", contact);
    return "normal/contact_detail";
  }
}
