package cgg.springthyme.thymeleafproj.dao;

import cgg.springthyme.thymeleafproj.entities.Student;

public interface StudentDAO {
  public int createTable();

  public int createStudent(Student student);

  public int getStudents();
}
