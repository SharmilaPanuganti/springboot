package cgg.blogapp.blogappspring.services;

import cgg.blogapp.blogappspring.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogappspring.payloads.PostDTO;
import cgg.blogapp.blogappspring.payloads.PostResponse;
import java.util.List;

public interface PostService {
  public PostDTO createPost(
    PostDTO postDTO,
    Integer userId,
    Integer categoryId
  ) throws ResourceNotFoundException;

  public PostDTO getPostById(int id) throws ResourceNotFoundException;

  public PostResponse getAllPosts(
    Integer pageNumber,
    Integer pageSize,
    String sortBy,
    String sortDir
  );

  public void deletePostById(int id) throws ResourceNotFoundException;

  public List<PostDTO> getPostsByCategory(int catId)
    throws ResourceNotFoundException;

  public List<PostDTO> getPostsByUser(int userId)
    throws ResourceNotFoundException;

  public List<PostDTO> getPostsByTitleContaining(String title);
}
