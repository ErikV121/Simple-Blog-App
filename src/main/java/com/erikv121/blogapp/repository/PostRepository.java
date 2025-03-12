package com.erikv121.blogapp.repository;

import com.erikv121.blogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    Optional<Post> findByUrl(String url);
}
