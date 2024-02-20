package cgg.springassign.dao;

import cgg.springassign.exceptions.UserNotFoundException;
import cgg.springassign.model.User;

public interface UserDAO {
  public boolean registerUser(User user);

  public boolean updateUser(User user);

  public User getUserById(String UserId);

  public boolean validateUser(String userName, String password)
    throws UserNotFoundException;

  public boolean deleteUser(String UserId);
}
