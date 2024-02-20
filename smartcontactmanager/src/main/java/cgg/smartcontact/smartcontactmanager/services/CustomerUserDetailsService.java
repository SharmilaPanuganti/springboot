package cgg.smartcontact.smartcontactmanager.services;

import cgg.smartcontact.smartcontactmanager.entities.CustomUserDetails;
import cgg.smartcontact.smartcontactmanager.entities.User;
import cgg.smartcontact.smartcontactmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    User byName = userRepository.findByName(username);
    if (byName == null) {
      throw new UsernameNotFoundException("User Not found");
    }
    return new CustomUserDetails(byName);
  }
}
