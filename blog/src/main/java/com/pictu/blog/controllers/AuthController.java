package com.pictu.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pictu.blog.config.security.JWTTokenHelper;
import com.pictu.blog.exceptions.ApiException;
import com.pictu.blog.payloads.JWTAuthRequest;
import com.pictu.blog.payloads.JWTAuthresponse;
import com.pictu.blog.payloads.UserDTO;
import com.pictu.blog.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JWTAuthresponse> createToken(
			@RequestBody JWTAuthRequest request ) throws Exception {
		
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.genreateToken(userDetails);
		JWTAuthresponse response = new JWTAuthresponse();
		response.setToken(token);
		return new ResponseEntity<JWTAuthresponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception  {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		try {
				this.authenticationManager.authenticate(authenticationToken);			
		}catch(BadCredentialsException e) {
			System.out.println("Invalid Details");
			throw new ApiException("Invalid username or password");
		}
			
	}
	
	@PostMapping("/register")
	private ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO){
		UserDTO registeredUser = this.userService.registerNewUser(userDTO);
		return new ResponseEntity<UserDTO>(registeredUser, HttpStatus.CREATED);
		
	}
}
