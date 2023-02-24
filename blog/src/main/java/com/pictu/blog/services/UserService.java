package com.pictu.blog.services;

import java.util.List;

import com.pictu.blog.payloads.UserDTO;

public interface UserService {
	
	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO user, Integer id);
	UserDTO getUserById(Integer id);
	List<UserDTO> getAllUsers();
	
	void deleteUser(Integer userId);
}
