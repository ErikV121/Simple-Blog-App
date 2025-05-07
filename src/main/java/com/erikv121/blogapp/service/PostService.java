package com.erikv121.blogapp.service;

import com.erikv121.blogapp.dto.request.PostRequest;
import com.erikv121.blogapp.dto.response.PostResponse;
import com.erikv121.blogapp.entity.Post;
import com.erikv121.blogapp.entity.User;
import com.erikv121.blogapp.mapper.PostMapper;
import com.erikv121.blogapp.repository.PostRepository;
import com.erikv121.blogapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {

    private static final Logger log = LoggerFactory.getLogger(PostService.class);
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private UserRepository userRepository;

    public PostService(PostRepository postRepository, PostMapper postMapper, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userRepository = userRepository;
    }

    public void createPost(PostRequest postRequest, String username) {
        log.info("Create Post Username: " + username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postMapper.dtoToEntity(postRequest);
        post.setUser(user);
        post.setUrl(postRequest.getTitle());

        Post savedPost = postRepository.save(post);
        postMapper.entityToDto(savedPost);
    }

    public List<PostResponse> findAllPosts() {
        return postRepository.findAllPostOrderDESC().stream()
                .map(postMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public List<PostResponse> findAllPostsRandomly() {
        return postRepository.findAllPostsRandomly().stream()
                .map(postMapper::entityToDto)
                .collect(Collectors.toList());
    }

    // Add this method to PostService
    public List<PostResponse> findPostsByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return postRepository.findByUser(user).stream()
                .map(postMapper::entityToDto)
                .collect(Collectors.toList());
    }


    public void updatePost(PostRequest postRequest) {
        if (postRequest.getId() == null) {
            throw new IllegalArgumentException("Post ID must be provided for update");
        }
        log.info("checking id BEFORE: "+ postRequest.getId());

        Post existingPost = postRepository.findById(postRequest.getId())
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postRequest.getId()));

        existingPost.setTitle(postRequest.getTitle());
        existingPost.setBody(postRequest.getBody());
        existingPost.setCategory(postRequest.getCategory());
        existingPost.setAnonymous(postRequest.isAnonymous());
        existingPost.setUrl(postRequest.getTitle().toLowerCase().replace(" ", "-"));
        log.info("checking id AFTER: "+ existingPost.getId());

        postRepository.save(existingPost);
    }

    public void deletePost(UUID id) {
        postRepository.deleteById(id);
    }

    public List<PostResponse> findPostsByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(postMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public PostResponse getPostById(UUID id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        return postMapper.entityToDto(post);
    }

    public PostResponse getPostByUrl(String url) {
        Post post = postRepository.findByUrl(url).orElseThrow(() -> new RuntimeException("Post not found with url: " + url));
        return postMapper.entityToDto(post);
    }

}
