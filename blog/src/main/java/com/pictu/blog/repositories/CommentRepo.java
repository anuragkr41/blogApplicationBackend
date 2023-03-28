package com.pictu.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pictu.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
