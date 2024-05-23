package com.gen.script.config;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import com.gen.script.oauth.token.AuthTokenProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class JwtConfig {
	@Value("${jwt.privateKey}")
	private String privateKey;
	@Value("${jwt.publicKey}")
	private String publicKey;	
	@Value("${jwt.password}")
	private String password;
	@Value("${jwt.alias}")
	private String alias;
	@Value("${jwt.tokenExpiry}")
	private long tokenExpiry;
	@Value("${jwt.refreshTokenExpiry}")
	private long refreshTokenExpiry;
	
	
    @Bean
    AuthTokenProvider authTokenProvider() {
    	String key = jwtProperties().getPrivateKey();
    	String password = jwtProperties().getPassword();
    	String alias = jwtProperties().getAlias();
    	
    	log.debug("\n[JWT][SETTING][key: {}][password: {}][alias: {}]", key, password, alias);

    	KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(key), password.toCharArray());
    	
    	KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias);
        return new AuthTokenProvider(keyPair);
    }
    
    @Bean
    JwtProperties jwtProperties() {
    	JwtProperties jwtProperties = new JwtProperties();
    	log.debug("[JWT][PUBLIC_KEY][{}]",publicKey);
    	log.debug("[JWT][ALIAS][{}]",alias);
    	log.debug("[JWT][TOKEN_EXPIRY][{}]",tokenExpiry);
    	log.debug("[JWT][TOKEN_REFRESH_TOKEN_EXPIRY][{}]",refreshTokenExpiry);
    	jwtProperties.setPassword(password);
    	jwtProperties.setAlias(alias);
    	jwtProperties.setPrivateKey(privateKey);
    	jwtProperties.setPublicKey(publicKey);
    	jwtProperties.setRefreshTokenExpiry(refreshTokenExpiry);
    	jwtProperties.setTokenExpiry(tokenExpiry);
    	return jwtProperties;
    }
}
