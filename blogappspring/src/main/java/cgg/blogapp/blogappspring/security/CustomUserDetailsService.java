package cgg.blogapp.blogappspring.security;

import cgg.blogapp.blogappspring.dao.UserRepository;
import cgg.blogapp.blogappspring.entities.User;
import cgg.blogapp.blogappspring.exceptions.ResourceNotFoundException;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    System.out.println("user" + username);
    User user = null;
    try {
      user =
        userRepository
          .findByUsername(username)
          .orElseThrow(() -> new ResourceNotFoundException("user", "name", 0));
    } catch (ResourceNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return user;
  }
}
