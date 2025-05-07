package com.erikv121.blogapp.controller;

import com.erikv121.blogapp.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {
    private final PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/")
    public String viewBlogPosts(Model model) {
        model.addAttribute("posts", postService.findAllPostsRandomly());
        return "/view_all_blogs";
    }
}
