package com.pictu.blog.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pictu.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

	

}
