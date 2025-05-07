package com.erikv121.blogapp.controller;

import com.erikv121.blogapp.dto.request.PostRequest;
import com.erikv121.blogapp.dto.response.CommentResponseDTO;
import com.erikv121.blogapp.dto.response.PostResponse;
import com.erikv121.blogapp.service.CommentService;
import com.erikv121.blogapp.service.PostService;
import com.erikv121.blogapp.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/blog")
public class PostController {
    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;


    public PostController(PostService postService, CommentService commentService, UserService userService) {
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/posts")
    public String createPost(@Valid @ModelAttribute("post") PostRequest postRequest,
                             BindingResult bindingResult,
                             Model model,
                             OAuth2AuthenticationToken oauth2AuthenticationToken) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postRequest);
            return "create_post";
        }
        OidcUser oidcUser = (OidcUser) oauth2AuthenticationToken.getPrincipal();
        String username = oidcUser.getPreferredUsername();

        userService.createOrCheckUserExists(username);

        postService.createPost(postRequest, username);
        model.addAttribute("name", username);
        return "redirect:/blog/posts";
    }

    @GetMapping("/posts/create")
    public String postForm(Model model,OAuth2AuthenticationToken oauth2AuthenticationToken) {
        OidcUser oidcUser = (OidcUser) oauth2AuthenticationToken.getPrincipal();
        String username = oidcUser.getPreferredUsername();

        model.addAttribute("post", new PostRequest());
        model.addAttribute("name", username);
        return "create_post";
    }


    @GetMapping("/posts")
    public String posts(Model model, OAuth2AuthenticationToken oauth2AuthenticationToken) {

        OidcUser oidcUser = (OidcUser) oauth2AuthenticationToken.getPrincipal();
        String username = oidcUser.getPreferredUsername();

        userService.createOrCheckUserExists(username);

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
