package com.erikv121.blogapp.service;

import com.erikv121.blogapp.dto.request.CommentRequestDto;
import com.erikv121.blogapp.dto.response.CommentResponseDTO;
import com.erikv121.blogapp.entity.Comment;
import com.erikv121.blogapp.entity.Post;
import com.erikv121.blogapp.entity.User;
import com.erikv121.blogapp.exception.ResourceNotFoundException;
import com.erikv121.blogapp.mapper.CommentMapper;
import com.erikv121.blogapp.repository.CommentRepository;
import com.erikv121.blogapp.repository.PostRepository;
import com.erikv121.blogapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository; // Inject UserRepository

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

//    TODO this whole class need to be refactored

    public void createComment(UUID postId, CommentRequestDto commentRequest, String username) {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));

        Comment comment = commentMapper.toEntity(commentRequest);
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);

    }

    public List<CommentResponseDTO> getCommentsByPostId(UUID postId) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post", "id", postId.toString());
        }
//        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId); // Or Desc
        return commentRepository.findByPostId(postId).stream()
                .map(commentMapper::toResponseDto)
                .toList();

    }

    public Comment updateComment(UUID commentId, CommentRequestDto commentRequest, User currentUser) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId.toString()));

        // Authorization check: Only the owner can update
        if (!existingComment.getUser().getId().equals(currentUser.getId())) {
            // Throw an appropriate exception (e.g., AccessDeniedException or a custom ForbiddenException)
            throw new SecurityException("User not authorized to update this comment");
        }
        existingComment.setBody(commentRequest.getBody());
        return commentRepository.save(existingComment);
    }

    public void deleteComment(UUID commentId, User currentUser) {
        Comment commentToDelete = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId.toString()));

        // Authorization check: Only the owner (or maybe an Admin) can delete
        if (!commentToDelete.getUser().getId().equals(currentUser.getId())) {
            // Add admin role check here if needed: && !currentUser.getRoles().contains("ROLE_ADMIN")
            throw new SecurityException("User not authorized to delete this comment");
        }

        commentRepository.delete(commentToDelete);
    }
}