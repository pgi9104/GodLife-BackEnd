package com.gen.script.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
	@NotBlank private String privateKey;
	@NotBlank private String publicKey;
	@NotBlank private String password;
	@NotBlank private String alias;
	@NotBlank private long tokenExpiry;
	@NotBlank private long refreshTokenExpiry;
}