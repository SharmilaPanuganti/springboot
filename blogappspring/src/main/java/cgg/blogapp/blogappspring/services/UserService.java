package cgg.blogapp.blogappspring.services;

import cgg.blogapp.blogappspring.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogappspring.payloads.UserDTO;
import java.util.List;

public interface UserService {
  public UserDTO createUser(UserDTO userDto);

  public UserDTO updateUser(UserDTO userDTO, int id)
    throws ResourceNotFoundException;

  public UserDTO getUserById(int id) throws ResourceNotFoundException;

  public List<UserDTO> getAllUsers();

  public void deleteUserById(int id) throws ResourceNotFoundException;
}
