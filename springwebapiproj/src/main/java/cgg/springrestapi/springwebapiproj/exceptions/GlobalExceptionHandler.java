package cgg.springrestapi.springwebapiproj.exceptions;

import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AgeNotValidException.class)
  public ResponseEntity<ProblemDetail> ageNotValid(AgeNotValidException e) {
    ProblemDetail pd = ProblemDetail.forStatus(
      HttpStatus.INTERNAL_SERVER_ERROR
    );
    pd.setTitle(e.getMessage());
    pd.setDetail("Age should be greater than 18 to cast vote");
    pd.setType(URI.create("http://localhost:8080/errors"));
    pd.setProperty("host", "localhost");
    pd.setProperty("port", "8080");
    return ResponseEntity.of(pd).build();
  }
}
