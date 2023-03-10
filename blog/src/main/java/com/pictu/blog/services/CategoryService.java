package com.pictu.blog.services;

import java.util.List;

import com.pictu.blog.payloads.CategoryDTO;

public interface CategoryService {
	// create
	CategoryDTO createCategory(CategoryDTO categoryDTO);

	// update
	CategoryDTO updateeCategory(CategoryDTO categoryDTO, Integer categoryId);

	// delete
	void deleteCategory(Integer categoryId);

	// Get Single category
	CategoryDTO getCategory(Integer categoryId);

	// Get All Categories
	List<CategoryDTO> getCategories();

}
