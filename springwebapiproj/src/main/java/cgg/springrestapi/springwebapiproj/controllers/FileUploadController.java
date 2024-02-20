package cgg.springrestapi.springwebapiproj.controllers;

import cgg.springrestapi.springwebapiproj.helper.FileUploadHelper;
import java.nio.file.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {

  @Autowired
  private FileUploadHelper fileUploadHelper;

  @PostMapping("/upload")
  public ResponseEntity<String> uploadFile(
    @RequestParam("file") MultipartFile file
  ) {
    // System.out.println(file.getOriginalFilename());
    // System.out.println(file.getSize());
    // System.out.println(file.getContentType());
    // System.out.println(file.getName());
    if (file.isEmpty()) {
      return ResponseEntity
        .internalServerError()
        .body("request should contain file");
    }

    // if(!file.getContentType().equals("image/jpeg")){
    //   return ResponseEntity.internalServerError().body("Image should be jpeg");
    // }
    if (
      !file
        .getOriginalFilename()
        .substring(file.getOriginalFilename().lastIndexOf(".") + 1)
        .equals("jpg")
    ) {
      return ResponseEntity.internalServerError().body("Image should be jpeg");
    }
    if (fileUploadHelper.uploadFile(file)) {
      return ResponseEntity.ok(
        ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/images/")
          .path(file.getOriginalFilename())
          .toUriString()
      );
    } else {
      return ResponseEntity.internalServerError().body("unable to upload");
    }
  }
}
