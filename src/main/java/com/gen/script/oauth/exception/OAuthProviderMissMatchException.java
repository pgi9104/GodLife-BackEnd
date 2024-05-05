package com.gen.script.oauth.exception;

public class OAuthProviderMissMatchException extends RuntimeException {
	private static final long serialVersionUID = -2278843423349909444L;

	public OAuthProviderMissMatchException(String message) {
        super(message);
    }
}