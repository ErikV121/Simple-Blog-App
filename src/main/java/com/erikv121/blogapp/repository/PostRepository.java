package com.erikv121.blogapp.repository;

import com.erikv121.blogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
