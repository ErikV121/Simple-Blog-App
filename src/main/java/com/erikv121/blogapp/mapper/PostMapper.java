package com.erikv121.blogapp.mapper;

import com.erikv121.blogapp.dto.request.PostRequest;
import com.erikv121.blogapp.dto.response.PostResponse;
import com.erikv121.blogapp.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post dtoToEntity(PostRequest postRequest);
    
    PostResponse entityToDto(Post post);
}
