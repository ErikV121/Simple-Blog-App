package com.erikv121.blogapp.mapper;

import com.erikv121.blogapp.dto.request.CommentRequestDto;
import com.erikv121.blogapp.dto.response.CommentResponseDTO;
import com.erikv121.blogapp.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "username", source = "user.username")
    CommentResponseDTO toResponseDto(Comment comment);

    Comment toEntity(CommentRequestDto commentRequestDto);
}
