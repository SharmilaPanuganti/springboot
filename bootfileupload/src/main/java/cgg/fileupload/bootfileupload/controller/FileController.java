package cgg.fileupload.bootfileupload.controller;

import cgg.fileupload.bootfileupload.payload.FileResponse;
import cgg.fileupload.bootfileupload.services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

  Logger logger = LoggerFactory.getLogger(FileController.class);

  @Autowired
  private FileService fileService;

  @Value("${project.images}")
  String path;

  @PostMapping("/upload")
  public ResponseEntity<FileResponse> uploadFile(
    @RequestParam("images") MultipartFile file
  ) {
    String fileName = null;
    if (file.isEmpty()) {
      return new ResponseEntity<FileResponse>(
        new FileResponse(null, "append file "),
        HttpStatus.BAD_REQUEST
      );
    }
    if (!file.getContentType().equals("image/jpeg")) {
      return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new FileResponse(fileName, "only jpeg files are allowed"));
    }
    try {
      fileName = this.fileService.uploadImage(path, file);
    } catch (IOException e) {
      return new ResponseEntity<FileResponse>(
        new FileResponse(null, "Image is not uploaded due to error"),
        HttpStatus.INTERNAL_SERVER_ERROR
      );
    }
    return new ResponseEntity<FileResponse>(
      new FileResponse(fileName, "uploade successfuly"),
      HttpStatus.OK
    );
  }

  //method to serve file
  @GetMapping(
    value = "/images/{imageName}",
    produces = MediaType.IMAGE_JPEG_VALUE
  )
  public void downloadImage(
    @PathVariable String imageName,
    HttpServletResponse response
  ) throws IOException {
    InputStream is = fileService.getResource(path, imageName);
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    StreamUtils.copy(is, response.getOutputStream());
  }

  @PostMapping("/multiple")
  public ResponseEntity<?> uploadMultiple(
    @RequestParam("images") MultipartFile[] files
  ) {
    logger.info("number of files {}", files.length);
    Arrays
      .stream(files)
      .forEach(file -> {
        logger.info("file Name {}", file.getOriginalFilename());
        logger.info("file type {}", file.getContentType());
        logger.info("---------------------------------");
        try {
          fileService.uploadImage(path, file);
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    return ResponseEntity.ok("Files uploaded");
  }
}
