package com.pictu.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pictu.blog.entities.User;
@Repository()
public interface UserRepository extends JpaRepository<User, Integer> {

}
