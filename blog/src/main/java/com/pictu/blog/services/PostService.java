package com.pictu.blog.services;

import java.util.List;

import com.pictu.blog.entities.Post;
import com.pictu.blog.payloads.PostDto;

public interface PostService {

	// Create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// Update
	Post updatePost(PostDto postDto, Integer posId);

	// Delete
	void deletePost(Integer postId);

	// Get all posts
	List<Post> getAllPost();

	// Get single post
	Post getPostById(Integer postId);

	// Get all posts by category
	List<Post> getPostsByCategory(Integer categoryId);

	// Get all posts by user
	List<Post> getPostsByUser(Integer userId);

	// Search Posts
	List<Post> searchPosts(String keyword);

}
