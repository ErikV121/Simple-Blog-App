package com.erikv121.blogapp.service;

import com.erikv121.blogapp.dto.request.PostRequest;
import com.erikv121.blogapp.dto.response.PostResponse;
import com.erikv121.blogapp.entity.Post;
import com.erikv121.blogapp.mapper.PostMapper;
import com.erikv121.blogapp.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public PostResponse createPost(PostRequest postRequest) {
        Post post = postMapper.dtoToEntity(postRequest);
        post = postRepository.save(post);
        return postMapper.entityToDto(post);
    }

    public List<PostResponse> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(postMapper::entityToDto).collect(Collectors.toList());
    }

}
