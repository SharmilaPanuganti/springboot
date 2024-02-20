package cgg.blogapp.blogappspring.controllers;

import cgg.blogapp.blogappspring.config.AppConstants;
import cgg.blogapp.blogappspring.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogappspring.payloads.PostDTO;
import cgg.blogapp.blogappspring.payloads.PostResponse;
import cgg.blogapp.blogappspring.services.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.validation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@CrossOrigin("*")
@SecurityRequirement(name = "bearerScheme")
public class PostController {

  @Autowired
  private PostService postService;

  // @PostMapping("/")
  // public ResponseEntity<PostDTO> createPost(
  //   @Valid @RequestBody PostDTO postDTO
  // ) {
  //   PostDTO post = postService.createPost(postDTO);
  //   return new ResponseEntity<PostDTO>(post, HttpStatus.CREATED);
  // }
  @PostMapping("/user/{userid}/category/{categoryId}")
  public ResponseEntity<PostDTO> createPost(
    @Valid @RequestBody PostDTO postDto,
    @PathVariable("userid") Integer userId,
    @PathVariable Integer categoryId
  ) throws ResourceNotFoundException {
    System.out.println("In post controller");
    PostDTO createPost =
      this.postService.createPost(postDto, userId, categoryId);
    return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
  }

  @GetMapping("/")
  public ResponseEntity<PostResponse> getAllPosts(
    @RequestParam(
      value = "pageNumber",
      defaultValue = AppConstants.PAGE_NUMBER,
      required = false
    ) Integer pageNumber,
    @RequestParam(
      value = "pageSize",
      defaultValue = AppConstants.PAGE_SIZE,
      required = false
    ) Integer pageSize,
    @RequestParam(
      value = "sortBy",
      defaultValue = AppConstants.SORT_BY,
      required = false
    ) String sortBy,
    @RequestParam(
      value = "sortDir",
      defaultValue = AppConstants.SORT_DIR,
      required = false
    ) String sortDir
  ) {
    PostResponse postResponse = postService.getAllPosts(
      pageNumber,
      pageSize,
      sortBy,
      sortDir
    );
    return ResponseEntity.ok(postResponse);
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<ProblemDetail> deletePost(
    @PathVariable("postId") int id
  ) throws ResourceNotFoundException {
    postService.deletePostById(id);

    return ResponseEntity
      .of(ProblemDetail.forStatusAndDetail(HttpStatus.OK, "Deleted succesfuly"))
      .build();
  }

  @GetMapping("/{postId}")
  public ResponseEntity<PostDTO> getPost(@PathVariable("postId") int id)
    throws ResourceNotFoundException {
    PostDTO postDTO = postService.getPostById(id);
    return ResponseEntity.ok(postDTO);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<PostDTO>> getPostsByUser(
    @PathVariable Integer userId
  ) throws ResourceNotFoundException {
    List<PostDTO> posts = this.postService.getPostsByUser(userId);
    return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
  }

  // get by category

  @GetMapping("/category/{categoryId}/posts")
  public ResponseEntity<List<PostDTO>> getPostsByCategory(
    @PathVariable Integer categoryId
  ) throws ResourceNotFoundException {
    List<PostDTO> posts = this.postService.getPostsByCategory(categoryId);
    return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
  }

  @GetMapping("/posts/search/{keywords}")
  public ResponseEntity<List<PostDTO>> searchPostByTitle(
    @PathVariable String keywords
  ) {
    List<PostDTO> result = this.postService.getPostsByTitleContaining(keywords);
    return new ResponseEntity<List<PostDTO>>(result, HttpStatus.OK);
  }
}
