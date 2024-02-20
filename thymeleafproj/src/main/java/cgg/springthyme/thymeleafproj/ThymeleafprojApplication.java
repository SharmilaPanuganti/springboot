package cgg.springthyme.thymeleafproj;

import cgg.springthyme.thymeleafproj.dao.StudentDAO;
import cgg.springthyme.thymeleafproj.entities.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThymeleafprojApplication implements CommandLineRunner {

  @Autowired
  private StudentDAO studentDAO;

  public ThymeleafprojApplication(StudentDAO studentDAO) {
    this.studentDAO = studentDAO;
  }

  public static void main(String[] args) {
    SpringApplication.run(ThymeleafprojApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println(this.studentDAO.createTable() + "Table created");
    createUser();
  }

  public void createUser() throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter id");
    int id = Integer.parseInt(br.readLine());
    System.out.println("Enter name");
    String name = br.readLine();
    System.out.println("Enter city");
    String city = br.readLine();
    Student student = new Student();
    student.setCity(city);
    student.setId(id);
    student.setName(name);
    int res = studentDAO.createStudent(student);
    System.out.println("Student created: " + res);
  }
}
