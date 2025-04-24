package com.erikv121.blogapp.controller;

import com.erikv121.blogapp.dto.request.PostRequest;
import com.erikv121.blogapp.dto.response.CommentResponseDTO;
import com.erikv121.blogapp.dto.response.PostResponse;
import com.erikv121.blogapp.service.CommentService;
import com.erikv121.blogapp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/blog")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;


    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping("/posts")
    public String createPost(@Valid @ModelAttribute("post") PostRequest postRequest,
                             BindingResult bindingResult,
                             Principal principal,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postRequest);
            return "create_post";
        }

        // Get username from authenticated user
        String username = principal.getName();
        postService.createPost(postRequest, username);
        return "redirect:/blog/posts";
    }

    @GetMapping("/posts/create")
    public String postForm(Model model) {
        model.addAttribute("post", new PostRequest());
        return "create_post";
    }


    @GetMapping("/posts")
    public String posts(Model model, Principal principal) {
        String username = principal.getName();
        System.out.println("Username: " + username);
        model.addAttribute("posts", postService.findPostsByUser(username));
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

        List<CommentResponseDTO> comments = commentService.getCommentsByPostId(post.getId());
        model.addAttribute("comments", comments);
        return "view_post";
    }

    @GetMapping("/posts/search")
    public String searchPosts(@RequestParam(required = false) String title, Model model) {
        if (title == null || title.trim().isEmpty()) {
            return "redirect:/blog/posts";
        }
        model.addAttribute("posts", postService.findPostsByTitle(title));
        return "posts";
    }

}
