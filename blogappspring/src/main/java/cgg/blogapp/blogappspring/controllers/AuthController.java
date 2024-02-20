package cgg.blogapp.blogappspring.controllers;

import cgg.blogapp.blogappspring.dao.UserRepository;
import cgg.blogapp.blogappspring.entities.User;
import cgg.blogapp.blogappspring.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogappspring.helpers.JwtHelper;
import cgg.blogapp.blogappspring.helpers.JwtRequest;
import cgg.blogapp.blogappspring.helpers.JwtResponse;
import cgg.blogapp.blogappspring.payloads.UserDTO;
import cgg.blogapp.blogappspring.services.UserService;
import jakarta.validation.Valid;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {

  @Autowired
  private UserDetailsService uDetailsService;

  @Autowired
  private UserService userService;

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private JwtHelper helper;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping("/login")
  public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request)
    throws ResourceNotFoundException {
    this.doAuthenticate(request.getUsername(), request.getPassword());
    System.out.println("username : " + request.getUsername());
    UserDetails userDetails = uDetailsService.loadUserByUsername(
      request.getUsername()
    );
    User byUsername = userRepository
      .findByUsername(request.getUsername())
      .orElseThrow(() -> new ResourceNotFoundException("user", "name", 0));
    UserDTO user = modelMapper.map(byUsername, UserDTO.class);
    String token = helper.generateToken(userDetails);
    JwtResponse response = JwtResponse
      .builder()
      .token(token)
      .user(user)
      .build();

    return ResponseEntity.ok(response);
  }

  private void doAuthenticate(String userName, String password) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
      userName,
      password
    );
    try {
      authManager.authenticate(authenticationToken);
    } catch (BadCredentialsException ex) {
      throw new BadCredentialsException("Invalide username or password");
    }
  }

  @ExceptionHandler(BadCredentialsException.class)
  public String exceptionHandler(BadCredentialsException ex) {
    return ex.getMessage();
  }

  @PostMapping("/register")
  public ResponseEntity<UserDTO> registerUser(
    @Valid @RequestBody UserDTO usetDto
  ) {
    usetDto.setRole("ROLE_USER");
    UserDTO registeredUser = this.userService.createUser(usetDto);

    return new ResponseEntity<UserDTO>(registeredUser, HttpStatus.CREATED);
  }
}
