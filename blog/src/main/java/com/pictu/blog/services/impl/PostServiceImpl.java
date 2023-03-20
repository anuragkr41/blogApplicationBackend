package com.pictu.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pictu.blog.entities.Category;
import com.pictu.blog.entities.Post;
import com.pictu.blog.entities.User;
import com.pictu.blog.exceptions.ResourceNotFoundException;
import com.pictu.blog.payloads.PostDto;
import com.pictu.blog.payloads.PostResponse;
import com.pictu.blog.repositories.CategoryRepo;
import com.pictu.blog.repositories.PostRepo;
import com.pictu.blog.repositories.UserRepository;
import com.pictu.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "UserId", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);

		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getContent());

		Post updatedPost = this.postRepo.save(post);

		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = null;
		if (sortDir.equalsIgnoreCase("Asc")) {

			sort = Sort.by(sortBy).ascending();

		}
		// else if (sortDir.equalsIgnoreCase("Dsc")) {
		else {

			sort = Sort.by(sortBy).descending();

		}

		// Sort9 sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending() ;

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> page = this.postRepo.findAll(p);
		List<Post> allPosts = page.getContent();

		// List<Post> allPosts = this.postRepo.findAll();
		List<PostDto> postDtos = allPosts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(page.getNumber());
		postResponse.setPageSize(page.getSize());
		postResponse.setTotalElements(page.getTotalElements());

		postResponse.setTotalPages(page.getTotalPages());
		postResponse.setLastPage(page.isLast());

		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

		List<Post> postsByUser = this.postRepo.findByUser(user);
		List<PostDto> postDtos = postsByUser.stream().map(postByUser -> this.modelMapper.map(postByUser, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}
}