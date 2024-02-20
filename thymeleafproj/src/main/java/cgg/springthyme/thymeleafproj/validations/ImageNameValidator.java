package cgg.springthyme.thymeleafproj.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.logging.Logger;

public class ImageNameValidator
  implements ConstraintValidator<ImageNameValid, String> {

  private Logger logger = Logger.getLogger(ImageNameValidator.class.getName());

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    logger.info("Message from invalid " + value);
    if (value.isBlank()) {
      return false;
    }
    return true;
  }
}
