package cgg.blogapp.blogappspring.services.impl;

import cgg.blogapp.blogappspring.dao.UserRepository;
import cgg.blogapp.blogappspring.entities.User;
import cgg.blogapp.blogappspring.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogappspring.payloads.UserDTO;
import cgg.blogapp.blogappspring.services.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDTO createUser(UserDTO userDto) {
    User user = userDtoToUser(userDto);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User save = userRepo.save(user);
    return userToUserDTO(save);
  }

  @Override
  public UserDTO updateUser(UserDTO userDTO, int id)
    throws ResourceNotFoundException {
    User updatedUser = userRepo
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
    updatedUser.setUsername(userDTO.getUsername());
    updatedUser.setEmail(userDTO.getEmail());
    updatedUser.setAbout(userDTO.getAbout());
    updatedUser.setPassword(userDTO.getPassword());
    User updated = userRepo.save(updatedUser);
    return userToUserDTO(updated);
  }

  @Override
  public UserDTO getUserById(int id) throws ResourceNotFoundException {
    User user = userRepo
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
    return userToUserDTO(user);
  }

  @Override
  public List<UserDTO> getAllUsers() {
    List<User> users = userRepo.findAll();
    List<UserDTO> userdtos = users
      .stream()
      .map(u -> userToUserDTO(u))
      .collect(Collectors.toList());
    return userdtos;
  }

  @Override
  public void deleteUserById(int id) throws ResourceNotFoundException {
    userRepo
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
    userRepo.deleteById(id);
  }

  public User userDtoToUser(UserDTO userDTO) {
    // User user = new User();
    // user.setId(userDTO.getId());
    // user.setName(userDTO.getName());
    // user.setEmail(userDTO.getEmail());
    // user.setPassword(userDTO.getPassword());
    // user.setAbout(userDTO.getAbout());
    return modelMapper.map(userDTO, User.class);
  }

  public UserDTO userToUserDTO(User user) {
    // UserDTO userDTO = new UserDTO();
    // userDTO.setId(user.getId());
    // userDTO.setName(user.getName());
    // userDTO.setEmail(user.getEmail());
    // userDTO.setPassword(user.getPassword());
    // userDTO.setAbout(user.getAbout());
    return modelMapper.map(user, UserDTO.class);
  }
}
