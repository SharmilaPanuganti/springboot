package cgg.blogapp.blogappspring.dao;

import cgg.blogapp.blogappspring.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {}
