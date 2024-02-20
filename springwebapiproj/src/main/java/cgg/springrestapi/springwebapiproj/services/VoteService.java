package cgg.springrestapi.springwebapiproj.services;

import org.springframework.stereotype.Service;

@Service
public class VoteService {

  public void vote(int age) {
    System.out.println("Voted");
  }
}
