package cgg.blogapp.blogappspring.helpers;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtRequest {

  private String username;
  private String password;
}
