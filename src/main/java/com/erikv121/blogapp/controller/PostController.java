package com.erikv121.blogapp.controller;

import com.erikv121.blogapp.dto.request.PostRequest;
import com.erikv121.blogapp.dto.response.PostResponse;
import com.erikv121.blogapp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
@RequestMapping("/blog")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public String createPost(@Valid @ModelAttribute("post") PostRequest postRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postRequest);
            return "create_post";
        }
        postService.createPost(postRequest);
        return "redirect:/blog/posts";
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/posts/create")
    public String postForm(Model model) {
        model.addAttribute("post", new PostRequest());
        return "create_post";
    }


    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postService.findAllPosts());
        return "posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPostForm(@PathVariable UUID id, Model model) {
        PostResponse post = postService.getPostById(id);
        PostRequest postRequest = PostRequest.fromResponse(post);
        model.addAttribute("post", postRequest);
        return "create_post";
    }

    @PostMapping("/posts/edit")
    public String updatePost(@Valid @ModelAttribute("post") PostRequest postRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postRequest);
            return "create_post";
        }

        postService.updatePost(postRequest);
        return "redirect:/blog/posts";
    }

    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
        return "redirect:/blog/posts";
    }

    @GetMapping("/posts/{url}")
    public String viewPost(@PathVariable String url, Model model) {
        PostResponse post = postService.getPostByUrl(url);
        model.addAttribute("post", post);
        return "view_post";
    }
}
