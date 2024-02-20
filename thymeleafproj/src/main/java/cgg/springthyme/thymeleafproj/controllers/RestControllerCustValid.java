package cgg.springthyme.thymeleafproj.controllers;

import cgg.springthyme.thymeleafproj.entities.LoginData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerCustValid {

  @PostMapping("/loginurl")
  public ResponseEntity<LoginData> createUser(
    @Valid @RequestBody LoginData loginData
  ) {
    System.out.println(loginData);
    return ResponseEntity.ok(loginData);
  }
}
