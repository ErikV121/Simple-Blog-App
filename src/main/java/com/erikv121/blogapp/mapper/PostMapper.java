package com.erikv121.blogapp.mapper;

import com.erikv121.blogapp.dto.request.PostRequest;
import com.erikv121.blogapp.dto.response.PostResponse;
import com.erikv121.blogapp.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "username", expression = "java(post.isAnonymous() ? \"Anonymous\" : post.getUser().getUsername())")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "url", source = "url")
    PostResponse entityToDto(Post post);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "anonymous", source = "anonymous")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "user", ignore = true) // User is set in service
    Post dtoToEntity(PostRequest postRequest);
}

