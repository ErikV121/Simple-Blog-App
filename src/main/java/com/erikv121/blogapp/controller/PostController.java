package com.erikv121.blogapp.controller;

import com.erikv121.blogapp.dto.request.PostRequest;
import com.erikv121.blogapp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public void addPost(@Valid @RequestBody PostRequest postRequest) {
        postService.createPost(postRequest);
    }

    @GetMapping("/admin/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postService.findAllPosts());
        return "posts";
    }
}
