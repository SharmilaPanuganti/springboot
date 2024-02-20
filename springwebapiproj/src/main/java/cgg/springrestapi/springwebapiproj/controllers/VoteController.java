package cgg.springrestapi.springwebapiproj.controllers;

import cgg.springrestapi.springwebapiproj.exceptions.AgeNotValidException;
import cgg.springrestapi.springwebapiproj.services.VoteService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote")
public class VoteController {

  @Autowired
  private VoteService voteService;

  @PostMapping
  public ResponseEntity<?> voteHandler(@RequestParam("age") int age) {
    if (age >= 18) {
      voteService.vote(age);
      return ResponseEntity.ok("Voted");
    }
    throw new AgeNotValidException("Age is less than 18!!");
  }
}
