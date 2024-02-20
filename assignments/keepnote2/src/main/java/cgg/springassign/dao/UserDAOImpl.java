package cgg.springassign.dao;

import cgg.springassign.exceptions.UserNotFoundException;
import cgg.springassign.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

  @Autowired
  private SessionFactory factory;

  public UserDAOImpl(SessionFactory factory) {
    this.factory = factory;
  }

  @Override
  @Transactional
  public boolean registerUser(User user) {
    boolean f = false;
    factory.getCurrentSession().persist(user);
    f = true;
    return f;
  }

  @Override
  @Transactional
  public boolean updateUser(User user) {
    boolean f = false;
    factory.getCurrentSession().merge(user);
    f = true;
    return f;
  }

  @Override
  @Transactional
  public User getUserById(String UserId) {
    User user = factory.getCurrentSession().get(User.class, UserId);
    return user;
  }

  @Override
  @Transactional
  public boolean validateUser(String userName, String password)
    throws UserNotFoundException {
    Query<User> query = factory
      .getCurrentSession()
      .createQuery(
        "from User u where u.user_name=" +
        userName +
        " and password=" +
        password,
        User.class
      );
    User singleResult = query.getSingleResult();
    if (singleResult == null) {
      throw new UserNotFoundException();
    }
    return true;
  }

  @Override
  @Transactional
  public boolean deleteUser(String UserId) {
    boolean f = false;
    User user = factory
      .getCurrentSession()
      .get(User.class, Integer.parseInt(UserId));
    factory.getCurrentSession().remove(user);
    f = true;
    return f;
  }
}
