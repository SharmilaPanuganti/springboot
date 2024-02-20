package cgg.blogapp.blogappspring.exceptions;

import cgg.blogapp.blogappspring.payloads.ApiResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> handleResourceExcetion(
    ResourceNotFoundException ex
  ) {
    return new ResponseEntity<ApiResponse>(
      new ApiResponse(ex.getMessage(), false),
      HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> argValidExceptionHandler(
    MethodArgumentNotValidException ex
  ) {
    Map<String, String> resp = new HashMap<String, String>();
    List<ObjectError> errors = ex.getBindingResult().getAllErrors();
    errors.forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String msg = error.getDefaultMessage();
      resp.put(fieldName, msg);
    });
    return new ResponseEntity<Map<String, String>>(
      resp,
      HttpStatus.BAD_REQUEST
    );
  }
}
