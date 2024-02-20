package cgg.blogapp.blogappspring.services.impl;

import cgg.blogapp.blogappspring.dao.CategoryRepository;
import cgg.blogapp.blogappspring.dao.PostRepository;
import cgg.blogapp.blogappspring.dao.UserRepository;
import cgg.blogapp.blogappspring.entities.Category;
import cgg.blogapp.blogappspring.entities.Post;
import cgg.blogapp.blogappspring.entities.User;
import cgg.blogapp.blogappspring.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogappspring.payloads.PostDTO;
import cgg.blogapp.blogappspring.payloads.PostResponse;
import cgg.blogapp.blogappspring.services.PostService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

  @Autowired
  private PostRepository pRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private CategoryRepository categoryRepo;

  // @Override
  // public PostDTO createPost(PostDTO postDTO) {
  //   Post post = new Post();
  //   post = modelMapper.map(postDTO, Post.class);
  //   Post save = pRepository.save(post);
  //   return modelMapper.map(save, PostDTO.class);
  // }
  @SuppressWarnings("null")
  @Override
  public PostDTO createPost(
    PostDTO postDto,
    Integer userId,
    Integer categoryId
  ) throws ResourceNotFoundException {
    System.out.println("In post service");
    User user =
      this.userRepo.findById(userId)
        .orElseThrow(() ->
          new ResourceNotFoundException("User", "User id", userId)
        );
    System.out.println(user);
    Category category =
      this.categoryRepo.findById(categoryId)
        .orElseThrow(() ->
          new ResourceNotFoundException("Category", "Category id", categoryId)
        );

    Post post = this.modelMapper.map(postDto, Post.class);
    post.setImageName("default.png");

    post.setUser(user);
    post.setCategory(category);
    Post newPost = pRepository.save(post);
    return this.modelMapper.map(newPost, PostDTO.class);
  }

  @Override
  public PostDTO getPostById(int id) throws ResourceNotFoundException {
    Post post = pRepository
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
    return modelMapper.map(post, PostDTO.class);
  }

  @Override
  public void deletePostById(int id) throws ResourceNotFoundException {
    pRepository
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
    pRepository.deleteById(id);
  }

  @Override
  public List<PostDTO> getPostsByCategory(int catId)
    throws ResourceNotFoundException {
    Category category = categoryRepo
      .findById(catId)
      .orElseThrow(() ->
        new ResourceNotFoundException("Category", "category id", catId)
      );
    List<Post> byCategory = pRepository.findByCategory(category);
    List<PostDTO> list = byCategory
      .stream()
      .map(p -> modelMapper.map(p, PostDTO.class))
      .toList();
    return list;
  }

  @Override
  public List<PostDTO> getPostsByUser(int userId)
    throws ResourceNotFoundException {
    User user =
      this.userRepo.findById(userId)
        .orElseThrow(() ->
          new ResourceNotFoundException("User", "User id", userId)
        );
    List<PostDTO> list = pRepository
      .findByUser(user)
      .stream()
      .map(p -> modelMapper.map(p, PostDTO.class))
      .toList();
    return list;
  }

  @Override
  public List<PostDTO> getPostsByTitleContaining(String title) {
    List<PostDTO> list = pRepository
      .findByTitleContaining(title)
      .stream()
      .map(p -> modelMapper.map(p, PostDTO.class))
      .toList();
    return list;
  }

  @Override
  public PostResponse getAllPosts(
    Integer pageNumber,
    Integer pageSize,
    String sortBy,
    String sortDir
  ) {
    Sort sort = (sortDir.equalsIgnoreCase("asc"))
      ? Sort.by(sortBy).ascending()
      : Sort.by(sortBy).descending();

    PageRequest p = PageRequest.of(pageNumber, pageSize, sort);
    Page<Post> pagePost = this.pRepository.findAll(p);
    List<Post> allPosts = pagePost.getContent();
    List<PostDTO> postDtos = allPosts
      .stream()
      .map(post -> this.modelMapper.map(post, PostDTO.class))
      .collect(Collectors.toList());

    PostResponse postResponse = new PostResponse();

    postResponse.setContent(postDtos);
    postResponse.setPageNumber(pagePost.getNumber());
    postResponse.setPageSize(pagePost.getSize());
    postResponse.setTotalElements(pagePost.getTotalElements());
    postResponse.setTotalPages(pagePost.getTotalPages());
    postResponse.setLastPage(pagePost.isLast());

    return postResponse;
  }
}
