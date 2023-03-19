package com.pictu.blog.services;

import java.util.List;

import com.pictu.blog.entities.Post;
import com.pictu.blog.payloads.PostDto;
import com.pictu.blog.payloads.PostResponse;

public interface PostService {

	// Create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// Update
	PostDto updatePost(PostDto postDto, Integer posId);

	// Delete
	void deletePost(Integer postId);

	// Get all posts
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir );

	// Get single post
	PostDto getPostById(Integer postId);

	// Get all posts by category
	List<PostDto> getPostsByCategory(Integer categoryId);

	// Get all posts by user
	List<PostDto> getPostsByUser(Integer userId);

	// Search Posts
	List<Post> searchPosts(String keyword);

}
