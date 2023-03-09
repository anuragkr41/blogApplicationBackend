package com.pictu.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pictu.blog.entities.User;
import com.pictu.blog.exceptions.ResourceNotFoundException;
import com.pictu.blog.payloads.UserDTO;
import com.pictu.blog.repositories.UserRepository;
import com.pictu.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	// @Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO createUser(UserDTO userDto) {

		//ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(userDto, User.class);
		User savedUser = userRepository.save(user);

		return modelMapper.map(savedUser, UserDTO.class);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer id) {
		//ModelMapper modelMapper = new ModelMapper();

		User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updatedUser = this.userRepository.save(user);

		return modelMapper.map(updatedUser, UserDTO.class);
	}

	@Override
	public UserDTO getUserById(Integer id) {
		//ModelMapper modelMapper = new ModelMapper();
		User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		//ModelMapper modelMapper = new ModelMapper();

		List<User> users = this.userRepository.findAll();
		List<UserDTO> userDtoList = users.stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());

		return userDtoList;
	}

	@Override
	public void deleteUser(Integer userId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		this.userRepository.delete(user);
	}
}
