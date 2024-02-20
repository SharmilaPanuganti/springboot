package cgg.springassign.service;

import cgg.springassign.dao.UserDAO;
import cgg.springassign.exceptions.UserAlreadyExistException;
import cgg.springassign.exceptions.UserNotFoundException;
import cgg.springassign.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDAO userDAO;

  public UserServiceImpl(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Override
  public boolean registerUser(User user) throws UserAlreadyExistException {
    boolean registerUser = userDAO.registerUser(user);
    return registerUser;
  }

  @Override
  public User updateUser(User user, String id) throws UserNotFoundException {
    User userById = userDAO.getUserById(id);
    if (userById == null) {
      throw new UserNotFoundException();
    } else {
      userById.setUserName(user.getUserName());
      userById.setUserMobile(user.getUserMobile());
      userById.setUserPassword(user.getUserPassword());
      userDAO.updateUser(userById);
    }
    return userById;
  }

  @Override
  public boolean deleteUser(String UserId) {
    boolean deleteUser = userDAO.deleteUser(UserId);
    return deleteUser;
  }

  @Override
  public boolean validateUser(String userName, String password)
    throws UserNotFoundException {
    boolean validateUser = userDAO.validateUser(userName, password);
    return validateUser;
  }

  @Override
  public User getUserById(String userId) throws UserNotFoundException {
    User userById = userDAO.getUserById(userId);
    return userById;
  }
}
