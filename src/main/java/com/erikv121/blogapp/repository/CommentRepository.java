package com.erikv121.blogapp.repository;

import com.erikv121.blogapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
     List<Comment> findByPostId(UUID postId);
     List<Comment> findByUserId(UUID userId);
     List<Comment> findByPostIdOrderByCreatedAtAsc(UUID postId);

}
