package cgg.springthyme.thymeleafproj.entities;

import cgg.springthyme.thymeleafproj.validations.ImageNameValid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginData {

  @NotBlank(message = "User name cannot be empty")
  @Size(min = 5, max = 12, message = "must be between 5 to 12 characters")
  private String userName;

  @Pattern(
    regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$",
    message = "Invalid email"
  )
  private String email;

  @NotBlank(message = "password cannot be empty")
  private String password;

  @AssertTrue
  private boolean agreed;

  @ImageNameValid
  private String imageName;
}
