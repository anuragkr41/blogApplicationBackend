package com.pictu.blog.payloads;

import lombok.Data;

@Data
public class JWTAuthRequest {
	private String username;
	private String password;

}
