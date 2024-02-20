package cgg.blogapp.blogappspring.controllers;

import cgg.blogapp.blogappspring.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogappspring.payloads.ApiResponse;
import cgg.blogapp.blogappspring.payloads.UserDTO;
import cgg.blogapp.blogappspring.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "bearerScheme")
@CrossOrigin("*")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/")
  public ResponseEntity<UserDTO> createUser(
    @Valid @RequestBody UserDTO userDTO
  ) {
    UserDTO user = userService.createUser(userDTO);
    return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<UserDTO> updateUser(
    @RequestBody UserDTO userDTO,
    @PathVariable("userId") int id
  ) throws ResourceNotFoundException {
    UserDTO updateUser = userService.updateUser(userDTO, id);
    return ResponseEntity.ok(updateUser);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserDTO> getUser(@PathVariable("userId") int id)
    throws ResourceNotFoundException {
    UserDTO user = userService.getUserById(id);
    return ResponseEntity.ok(user);
  }

  @GetMapping("/")
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    List<UserDTO> users = userService.getAllUsers();
    return ResponseEntity.ok(users);
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<ProblemDetail> deleteUser(
    @PathVariable("userId") int id
  ) throws ResourceNotFoundException {
    userService.deleteUserById(id);
    // return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    // return ResponseEntity.ok(Map.of("msg", "deleted successfully"));

    // return new ResponseEntity<ApiResponse>(
    //   new ApiResponse("deleted successfully", true),
    //   HttpStatus.OK
    // );

    return ResponseEntity
      .of(ProblemDetail.forStatusAndDetail(HttpStatus.OK, "Deleted succesfuly"))
      .build();
  }
}
