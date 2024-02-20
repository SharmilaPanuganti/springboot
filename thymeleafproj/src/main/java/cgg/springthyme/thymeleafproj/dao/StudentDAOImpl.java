package cgg.springthyme.thymeleafproj.dao;

import cgg.springthyme.thymeleafproj.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class StudentDAOImpl implements StudentDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public int createTable() {
    int update = jdbcTemplate.update(
      "create table if not exists student(id int primary key,name varchar(30),city varchar(50))"
    );
    return update;
  }

  @Override
  public int createStudent(Student student) {
    String query = "insert into student values(?,?,?)";
    int update = jdbcTemplate.update(
      query,
      new Object[] { student.getId(), student.getName(), student.getCity() }
    );
    return update;
  }

  @Override
  public int getStudents() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'getStudents'"
    );
  }
}
