package cgg.blogapp.blogappspring.helpers;

import cgg.blogapp.blogappspring.payloads.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {

  private String token;
  private UserDTO user;
}
