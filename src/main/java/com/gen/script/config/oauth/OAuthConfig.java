package com.gen.script.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gen.script.api.auth.repository.UserRefreshTokenRepository;
import com.gen.script.config.AppProperties;
import com.gen.script.config.JwtProperties;
import com.gen.script.oauth.filter.TokenAuthenticationFilter;
import com.gen.script.oauth.handler.OAuth2AuthenticationFailureHandler;
import com.gen.script.oauth.handler.OAuth2AuthenticationSuccessHandler;
import com.gen.script.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.gen.script.oauth.token.AuthTokenProvider;

@Configuration
public class OAuthConfig {
	
	@Autowired private UserRefreshTokenRepository userRefreshTokenRepository;
	@Autowired private AuthTokenProvider authTokenProvider;
	@Autowired JwtProperties jwtProperties;
	
    /*
    * 토큰 필터 설정
    * */
    @Bean
    TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(authTokenProvider);
    }

    /*
    * 쿠키 기반 인가 Repository
    * 인가 응답을 연계 하고 검증할 때 사용.
    * */
    @Bean
    OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    /**
    * Oauth 인증 성공 핸들러
    * */
    @Bean
    OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new OAuth2AuthenticationSuccessHandler(
        		authTokenProvider,
        		jwtProperties,
        		appProperties(),
                userRefreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository()
        );
    }

    /**
     * Oauth 인증 실패 핸들러
     * */
    @Bean
    OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
    }
    
    @Bean
    AppProperties appProperties() {
    	return new AppProperties();
    }
}
