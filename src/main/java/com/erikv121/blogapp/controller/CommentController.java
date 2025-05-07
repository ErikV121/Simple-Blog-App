package com.erikv121.blogapp.controller;

import com.erikv121.blogapp.dto.request.CommentRequestDto;
import com.erikv121.blogapp.service.CommentService;
import com.erikv121.blogapp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/blog")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @PostMapping("/posts/{id}/comments")
    public String createComment(@PathVariable(value = "id") UUID postId,
                                @Valid @ModelAttribute CommentRequestDto commentRequest,
                                BindingResult bindingResult,
                                Model model,
                                OAuth2AuthenticationToken oauth2AuthenticationToken) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("comment", commentRequest);
            String uri = postService.getPostById(postId).getUrl();
            return "/blog/posts/" + uri;
        }

        OidcUser oidcUser = (OidcUser) oauth2AuthenticationToken.getPrincipal();
        String username = oidcUser.getPreferredUsername();

        commentService.createComment(postId, commentRequest, username);
        return "redirect:/blog/posts/" + postService.getPostById(postId).getUrl();
    }

    @GetMapping("/posts/{id}/comments")
    public String getComments(@PathVariable(value = "id") UUID postId, Model model) {
        model.addAttribute("comments", commentService.getCommentsByPostId(postId));
        return "comments";
    }
}
