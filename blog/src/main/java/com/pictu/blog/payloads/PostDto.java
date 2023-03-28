package com.pictu.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.pictu.blog.entities.Category;
import com.pictu.blog.entities.Comment;
import com.pictu.blog.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private Integer id;

	private String title;
	private String content;

	private String imageName;

	private Date addedDate;

	private CategoryDTO category;

	private UserDTO user;

	private Set<CommentDto> comments = new HashSet<>();

}
