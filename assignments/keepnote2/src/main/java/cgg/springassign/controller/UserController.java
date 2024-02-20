package cgg.springassign.controller;

import cgg.springassign.exceptions.UserAlreadyExistException;
import cgg.springassign.model.User;
import cgg.springassign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/user")
  public ResponseEntity<String> createUser(@RequestBody User user) {
    try {
      userService.registerUser(user);
    } catch (UserAlreadyExistException e) {
      return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body("user already exists");
    }
    //TODO: process POST request
    return ResponseEntity.ok("User created successfully");
  }
}
