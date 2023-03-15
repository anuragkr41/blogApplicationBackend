package com.pictu.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pictu.blog.entities.Category;
import com.pictu.blog.entities.Post;
import com.pictu.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	
	//Custom methods
	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

}
