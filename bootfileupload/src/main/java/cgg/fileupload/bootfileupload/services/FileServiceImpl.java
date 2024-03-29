package cgg.fileupload.bootfileupload.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

  @Override
  public String uploadImage(String path, MultipartFile file)
    throws IOException {
    String name = file.getOriginalFilename();
    String random = UUID.randomUUID().toString();
    String fileName = random.concat(name.substring(name.lastIndexOf(".")));
    String filePath = path + File.separator + fileName;
    File f = new File(path);
    if (!f.exists()) {
      f.mkdir();
    }
    Files.copy(file.getInputStream(), Paths.get(filePath));
    return name;
  }

  @Override
  public InputStream getResource(String path, String fileName)
    throws FileNotFoundException {
    String fullPath = path + File.separator + fileName;
    FileInputStream fis = new FileInputStream(fullPath);
    return fis;
  }
}
