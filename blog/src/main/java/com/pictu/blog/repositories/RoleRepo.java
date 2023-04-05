package com.pictu.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pictu.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
