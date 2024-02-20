package cgg.springrestapi.springwebapiproj.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

  public final String upload_dir = new ClassPathResource("static/images")
    .getFile()
    .getAbsolutePath();

  // public final String upload_dir =
  //   "C:\\Users\\CGG\\Desktop\\springboot\\springwebapiproj\\src\\main\\resources\\static\\images";
  public FileUploadHelper() throws IOException {}

  public boolean uploadFile(MultipartFile file) {
    boolean f = false;
    try {
      System.out.println("upload dir:" + upload_dir);
      Files.copy(
        file.getInputStream(),
        Paths.get(upload_dir + File.separator + file.getOriginalFilename()),
        StandardCopyOption.REPLACE_EXISTING
      );
      f = true;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return f;
  }
}
