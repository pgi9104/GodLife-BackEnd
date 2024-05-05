package com.gen.script.config.security.auth.authentication.success;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 스프링 버전이 오르면서 빈으로 설정하기만 하면 자동지정됨.
 * @author rbdlf
 *
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Optional<? extends GrantedAuthority> auth = authorities.stream()
				 .filter(a -> a.getAuthority().equals("read"))
				 .findFirst();
		
		if(auth.isPresent()) {
			response.sendRedirect("/home");
		} else {
			response.sendRedirect("/error");
		}
	}
}
