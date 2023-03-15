package com.pictu.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private int id;
	
	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 characters")
	private String name;
	
	@Email(message = "Email Address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min=3, max=10, message = "Password must be minimum of 3 and maximum of 10 chars")
	private String password;
	
	@NotEmpty
	private String about;
}
