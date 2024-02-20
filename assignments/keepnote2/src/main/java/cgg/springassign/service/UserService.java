package cgg.springassign.service;

import cgg.springassign.exceptions.UserAlreadyExistException;
import cgg.springassign.exceptions.UserNotFoundException;
import cgg.springassign.model.User;

public interface UserService {
  public boolean registerUser(User user) throws UserAlreadyExistException;

  public User updateUser(User user, String id) throws UserNotFoundException;

  public boolean deleteUser(String UserId);

  public boolean validateUser(String userName, String password)
    throws UserNotFoundException;

  public User getUserById(String userId) throws UserNotFoundException;
}
