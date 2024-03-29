package com.pictu.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pictu.blog.config.AppConstants;
import com.pictu.blog.entities.Role;
import com.pictu.blog.entities.User;
import com.pictu.blog.exceptions.ResourceNotFoundException;
import com.pictu.blog.payloads.UserDTO;
import com.pictu.blog.repositories.RoleRepo;
import com.pictu.blog.repositories.UserRepository;
import com.pictu.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	// @Qualifier("userRepository")
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDTO createUser(UserDTO userDto) {

		// ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(userDto, User.class);
		User savedUser = userRepository.save(user);

		return modelMapper.map(savedUser, UserDTO.class);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer id) {
		// ModelMapper modelMapper = new ModelMapper();

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
		// ModelMapper modelMapper = new ModelMapper();
		User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		// ModelMapper modelMapper = new ModelMapper();

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

	@Override
	public UserDTO registerNewUser(UserDTO userDTO) {
		User user = this.modelMapper.map(userDTO, User.class);

		// encode the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

		user.getRoles().add(role);

		User newUser = this.userRepository.save(user);

		return this.modelMapper.map(newUser, UserDTO.class);
	}
}
