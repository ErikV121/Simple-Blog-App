package com.erikv121.blogapp.repository;

import com.erikv121.blogapp.entity.Post;
import com.erikv121.blogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    Optional<Post> findByUrl(String url);

    List<Post> findByTitleContainingIgnoreCase(String title);

    List<Post> findByUser(User user);

    @Query("SELECT p FROM Post p ORDER BY  p.createdAt DESC")
    List<Post> findAllPostOrderDESC();

    @Query("SELECT p FROM Post p ORDER BY FUNCTION('RAND')")
    List<Post> findAllPostsRandomly();

}
