package cgg.blogapp.blogappspring.services.impl;

import cgg.blogapp.blogappspring.dao.CommentRepo;
import cgg.blogapp.blogappspring.dao.PostRepository;
import cgg.blogapp.blogappspring.entities.Comment;
import cgg.blogapp.blogappspring.entities.Post;
import cgg.blogapp.blogappspring.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogappspring.payloads.CommentDTO;
import cgg.blogapp.blogappspring.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private PostRepository postRepo;

  @Autowired
  private CommentRepo commentRepo;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public CommentDTO createComment(CommentDTO commentDto, Integer postId)
    throws ResourceNotFoundException {
    Post post =
      this.postRepo.findById(postId)
        .orElseThrow(() ->
          new ResourceNotFoundException("Post", "post id", postId)
        );
    Comment comment = this.modelMapper.map(commentDto, Comment.class);
    comment.setPost(post);
    Comment savedComment = this.commentRepo.save(comment);

    return this.modelMapper.map(savedComment, CommentDTO.class);
  }

  @Override
  public void deleteComment(Integer commentId)
    throws ResourceNotFoundException {
    Comment com =
      this.commentRepo.findById(commentId)
        .orElseThrow(() ->
          new ResourceNotFoundException("Comment", "CommentId", commentId)
        );
  }
}
