package com.gen.script.oauth.token;

import java.security.KeyPair;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.gen.script.oauth.exception.TokenValidFailedException;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthTokenProvider {
    private final KeyPair keyPair;
    private static final String AUTHORITIES_KEY = "role";

    public AuthTokenProvider(KeyPair keyPair) {
    	this.keyPair = keyPair;
    }

    public AuthToken createAuthToken(String id, Date expiry) {
        return new AuthToken(id, expiry, keyPair.getPrivate());
    }

    public AuthToken createAuthToken(String id, String role, Date expiry) {
        return new AuthToken(id, role, expiry, keyPair.getPrivate());
    }

    public AuthToken convertAuthToken(String token) {
        return new AuthToken(token, keyPair.getPublic());
    }

    public Authentication getAuthentication(AuthToken authToken) throws TokenValidFailedException{
        if(!authToken.validate()) {
            throw new TokenValidFailedException();
        }

        Claims claims = authToken.getTokenClaims();
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(new String[]{claims.get(AUTHORITIES_KEY).toString()})
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        log.debug("\n[JWT][claims subject][{}]", claims.getSubject());
        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, authToken, authorities);
    }
}

