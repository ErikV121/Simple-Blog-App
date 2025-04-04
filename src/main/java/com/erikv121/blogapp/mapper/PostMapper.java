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
    @Mapping(target = "author", expression = "java(post.isAnonymous() ? \"Anonymous\" : post.getOriginalAuthor())")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "url", source = "url")
    PostResponse entityToDto(Post post);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "anonymous", source = "anonymous")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "url", source = "url")
    Post dtoToEntity(PostRequest postRequest);
}

