package cgg.blogapp.blogappspring.controllers;

import cgg.blogapp.blogappspring.dao.CommentRepo;
import cgg.blogapp.blogappspring.entities.Comment;
import cgg.blogapp.blogappspring.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogappspring.payloads.ApiResponse;
import cgg.blogapp.blogappspring.payloads.CommentDTO;
import cgg.blogapp.blogappspring.services.CommentService;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
@CrossOrigin("*")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private CommentRepo commentRepo;

  @PostMapping("/post/{postId}/comments")
  public ResponseEntity<CommentDTO> createComment(
    @RequestBody Comment commentDto,
    @PathVariable Integer postId
  ) throws ResourceNotFoundException {
    CommentDTO comment = modelMapper.map(commentDto, CommentDTO.class);
    CommentDTO createComment =
      this.commentService.createComment(comment, postId);
    return new ResponseEntity<CommentDTO>(createComment, HttpStatus.CREATED);
  }

  @DeleteMapping("/comments/{commentId}")
  public ResponseEntity<ApiResponse> deleteComment(
    @PathVariable Integer commentId
  ) throws ResourceNotFoundException {
    Comment orElseThrow = commentRepo
      .findById(commentId)
      .orElseThrow(() -> new ResourceNotFoundException("comment", "id", 0));
    return new ResponseEntity<ApiResponse>(
      new ApiResponse("Comment deleted successfully!!", true),
      HttpStatus.OK
    );
  }
}
