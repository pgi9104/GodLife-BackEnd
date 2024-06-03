package com.gen.script.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;

import com.gen.script.oauth.exception.RestAuthenticationEntryPoint;
import com.gen.script.oauth.filter.TokenAuthenticationFilter;
import com.gen.script.oauth.handler.OAuth2AuthenticationFailureHandler;
import com.gen.script.oauth.handler.OAuth2AuthenticationSuccessHandler;
import com.gen.script.oauth.handler.TokenAccessDeniedHandler;
import com.gen.script.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.gen.script.oauth.service.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
    private CustomOAuth2UserService oAuth2UserService;
	@Autowired
    private TokenAccessDeniedHandler tokenAccessDeniedHandler;
	@Autowired
	private TokenAuthenticationFilter tokenAuthenticationFilter;
	@Autowired
	private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
	@Autowired
	private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
	@Autowired
	private OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository;
	@Autowired
	private CorsConfigurationSource corsConfigurationSource;

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() { // security를 적용하지 않을 리소스
        return web -> web.ignoring()
                // error endpoint를 열어줘야 함, favicon.ico 추가!
                .requestMatchers("/error", "/favicon.ico", "/docs/**","/api","/","/explorer/**");
    }
    
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors(cors -> cors.configurationSource(corsConfigurationSource))
			.csrf(csrf -> csrf.disable())
			.formLogin(formLogin -> formLogin.disable())
			.httpBasic(httpBasic -> httpBasic.disable())
            .headers(headers ->
            	headers
            		.frameOptions(FrameOptionsConfig::disable)	 // X-Frame-Options 비활성화(iframe을 사용하지 않음으로 disable)
            		.disable())
			.sessionManagement(sessionManagement -> 
				sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))	//JWT를 사용함으로써 세션을 사용하지 않는다. 
			.authorizeHttpRequests(authorized ->
				authorized
					.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
					.requestMatchers("/token").permitAll()		//토큰 재발급은 인증 없이 접근 가능
					.requestMatchers("*/oauth2/**").permitAll()
					.requestMatchers("/api/menu/**").anonymous()
					.requestMatchers("/api/**").permitAll()
					.anyRequest().authenticated())	//설정된 값 이외의 나머지 URL, 인증된 사용자, 로그인한 사용자만 볼 수 있음
			.exceptionHandling(exceptionHandling ->
				exceptionHandling
					.accessDeniedHandler(tokenAccessDeniedHandler)
					.authenticationEntryPoint(new RestAuthenticationEntryPoint()))
			.oauth2Login(oauth2Login ->
				oauth2Login
					.authorizationEndpoint(oauthorizationEndpoint -> {
						oauthorizationEndpoint
							.baseUri("/oauth2/authorization")
							.authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository);	//Authorization request와 관련된 state가 저장됨
					})
					.redirectionEndpoint(redirectionEndpoint -> {
						//endpoint로 인증요청을 받으면, Spring security의 Oauth2 사용자를 provider가 제공하는 AuthorizationUri로 Redirect
						redirectionEndpoint
							.baseUri("/*/oauth2/code/*");
					})
					.userInfoEndpoint(userInfoEndpoint ->{
						userInfoEndpoint.userService(oAuth2UserService);	//소셜로그인 성공시 사용자 정보를 가져오거나 등록할 서비스 등록
					})
					.successHandler(oAuth2AuthenticationSuccessHandler)		//인증을 성공한 경우
					.failureHandler(oAuth2AuthenticationFailureHandler))	//인증을 실패한 경우
			.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);	//토큰을 헤더에서 확인할 커스텀 필터
		
		return http.build();
	}
}