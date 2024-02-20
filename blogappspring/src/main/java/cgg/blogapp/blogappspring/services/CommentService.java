package cgg.blogapp.blogappspring.services;

import cgg.blogapp.blogappspring.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogappspring.payloads.CommentDTO;

public interface CommentService {
  CommentDTO createComment(CommentDTO commentDto, Integer postId)
    throws ResourceNotFoundException;
  void deleteComment(Integer commentId) throws ResourceNotFoundException;
}
