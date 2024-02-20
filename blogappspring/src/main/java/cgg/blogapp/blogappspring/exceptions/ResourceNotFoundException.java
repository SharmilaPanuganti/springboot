package cgg.blogapp.blogappspring.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends Exception {

  private String resourceName;
  private String field;
  private long value;

  public ResourceNotFoundException(
    String resourceName,
    String field,
    long value
  ) {
    super(String.format("%s not found with %s:%s", resourceName, field, value));
    this.resourceName = resourceName;
    this.field = field;
    this.value = value;
  }
}
