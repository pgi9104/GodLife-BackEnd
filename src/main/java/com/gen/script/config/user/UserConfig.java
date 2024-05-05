package com.gen.script.config.user;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.ldap.DefaultLdapUsernameToDnMapper;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.LdapUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import com.gen.script.config.security.auth.passwordencoder.Sha512PasswordEncoder;

@Configuration
public class UserConfig {
	@Value("${auth.type}")
	private String userDetailsServiceType;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		return new AuthenticationProvider() {
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				String username = authentication.getName();
				String password = authentication.getCredentials().toString();
				
				UserDetails u = userDetailsService().loadUserByUsername(username);
				
				if (passwordEncoder().matches(password, u.getPassword())) {	//암호가 일치하면 필요한 세부 정보가 포함된 Authentication 계약의 구현을 반환.
					return new UsernamePasswordAuthenticationToken(username, password, u.getAuthorities());
				} else {	//암호가 일치하지 않으면 예외를 투척.
					throw new BadCredentialsException("Something went wrong!!");
				}
			}
			
			@Override
			public boolean supports(Class<?> authentication) {
				return authentication.equals(UsernamePasswordAuthenticationToken.class);
			}
		};
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		UserDetailsService uds = null;
		String type = userDetailsServiceType.isBlank()? "inmemory" : userDetailsServiceType.trim().toLowerCase();
		
		switch(type) {
			case "db":
				uds = jdbcUserDetailsManager();
				break;
				
			case "ldap":
				uds = ldapUserDetailsManager();
				break;
				
			default:
				//uds = inMemoryUserDetailsManager();
				break;
		}
		
		return uds;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		/**
		 * noop: 암호를 인코딩하지 않고 일반 텍스트로 유지한다. 실제구현에서는 사용하면 안된다.
		 * sha256: SHA-256을 이용해 암호를 해시한다. 이 구현은 이제 구식이며 새 구현에는 쓰지 말아야한다. 기존 어플리케이션이 SHA-256을 사용하는 경우 이용이 되는 경우가 있따.
		 * sha512: SHA-512을 이용해 암호를 해시한다.
		 * Pbkdf2: PBKDF2를 이용한다.
		 * bcrypt: bcrypt 강력 해싱 함수로 암호를 인코딩한다.
		 * scrypt: 해싱 함수로 암호를 인코딩한다.
		 */
		//encoders.put("noop", NoOpPasswordEncoder.getInstance());
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		//encoders.put("pbkdf2", new Pbkdf2PasswordEncoder("secret", 0, 185000, 256));// 시크릿	salt크기 반복횟수 해새의 크기
		//encoders.put("sha256", new StandardPasswordEncoder("secret"));
		encoders.put("sha512", new Sha512PasswordEncoder());
		encoders.put("scrypt", new SCryptPasswordEncoder(16384,8,1,32,64));	//cpu비용	메모리비용	병렬화 개수	키 길이	솔트길이
		
		return new DelegatingPasswordEncoder("bcrypt", encoders);
	}
	
	/**
	 * jdbc를 이용한 인증처리
	 * @return UserDetailsService
	 */
	private UserDetailsService jdbcUserDetailsManager() {
		String usersByUsernameQuery = "SELECT USERNAME, PASSWORD, ENABLED FROM USERS WHERE USERNAME = ?";
		String authsByUserQuery = "SELECT USERNAME, AUTHORITY FROM AUTHORITIES WHERE USERNAME = ?";
		
		JdbcUserDetailsManager judm = new JdbcUserDetailsManager(dataSource);
		judm.setUsersByUsernameQuery(usersByUsernameQuery);
		judm.setAuthoritiesByUsernameQuery(authsByUserQuery);
		return judm;
	}
	
	/**
	 * ldap을 이용한 로그인 처리
	 * @return UserDetailsService
	 */
	private UserDetailsService ldapUserDetailsManager() {
		DefaultSpringSecurityContextSource cs = new DefaultSpringSecurityContextSource("ldap://127.0.0.1:33389/dc=springframework,dc=org");
		cs.afterPropertiesSet();
		
		LdapUserDetailsManager ldap = new LdapUserDetailsManager(cs);
		ldap.setUsernameMapper(new DefaultLdapUsernameToDnMapper("ou=groups", "uid"));	//사용자 이름 매퍼를 설정해 사용자를 검색하는 방법 지시
		ldap.setGroupSearchBase("ou=groups");	//사용자를 검색하는 데 필요한 그룹 검색 기준 설정
		
		return ldap;
	}

	/**
	 * 메모리를 이용한 인증
	 * @return UserDetailsService
	 */
	/*
	 * private UserDetailsService inMemoryUserDetailsManager() { UserDetails u = new
	 * User("john", "12345", "read"); List<UserDetails> users = List.of(u); return
	 * new InMemoryUserDetailsManager(users); }
	 */
}
