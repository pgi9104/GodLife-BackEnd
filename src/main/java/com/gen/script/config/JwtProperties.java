package com.gen.script.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtProperties {
	@NotBlank private String privateKey;
	@NotBlank private String publicKey;
	private String password;
	private String alias;
	private long tokenExpiry;
	private long refreshTokenExpiry;
}