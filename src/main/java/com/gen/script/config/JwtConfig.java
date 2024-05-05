package com.gen.script.config;

import java.security.KeyPair;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import com.gen.script.oauth.token.AuthTokenProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class JwtConfig {
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
    	return new JwtProperties();
    }
}
