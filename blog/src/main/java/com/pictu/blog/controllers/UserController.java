package com.pictu.blog.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pictu.blog.payloads.ApiResponse;
import com.pictu.blog.payloads.UserDTO;
import com.pictu.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	// Create User POST
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
		UserDTO createdUserDto = this.userService.createUser(userDTO);
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
	}

	// Update User - PUT API

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable("id") Integer Id) {

		UserDTO updatedUserDto = this.userService.updateUser(userDTO, Id);

		return new ResponseEntity<UserDTO>(updatedUserDto, HttpStatus.OK);

	}

	// Get Users

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	
	
	// Get Single User
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
	// Delete User
	
	// Only Admin can delete the user
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser( @PathVariable("id") Integer id) {
		this.userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true) ,HttpStatus.OK);
	}
	
	

}
