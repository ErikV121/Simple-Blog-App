package com.erikv121.blogapp.service;

import com.erikv121.blogapp.dto.request.PostRequest;
import com.erikv121.blogapp.dto.response.PostResponse;
import com.erikv121.blogapp.entity.Post;
import com.erikv121.blogapp.mapper.PostMapper;
import com.erikv121.blogapp.repository.PostRepository;
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

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

//    TODO need to add correct exception handling

    public void createPost(PostRequest postRequest) {
        Post post = postMapper.dtoToEntity(postRequest);
        log.info("id: "+ post.getId());
        post.setUrl(postRequest.getTitle());
        Post savedPost = postRepository.save(post);
        postMapper.entityToDto(savedPost);
    }

    public List<PostResponse> findAllPosts() {
        return postRepository.findAll().stream()
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
        existingPost.setAuthor(postRequest.getAuthor());
        existingPost.setAnonymous(postRequest.isAnonymous());
        existingPost.setUrl(postRequest.getTitle().toLowerCase().replace(" ", "-"));
        log.info("checking id AFTER: "+ existingPost.getId());

        postRepository.save(existingPost);
    }

    public void deletePost(UUID id) {
        postRepository.deleteById(id);
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
