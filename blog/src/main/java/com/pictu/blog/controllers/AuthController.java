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
import com.pictu.blog.payloads.JWTAuthRequest;
import com.pictu.blog.payloads.JWTAuthresponse;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JWTAuthresponse> createToken(
			@RequestBody JWTAuthRequest request ) {
		
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.genreateToken(userDetails);
		JWTAuthresponse response = new JWTAuthresponse();
		response.setToken(token);
		return new ResponseEntity<JWTAuthresponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password)  {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		
			this.authenticationManager.authenticate(authenticationToken);			
	
			
			
		
			
	}
}
