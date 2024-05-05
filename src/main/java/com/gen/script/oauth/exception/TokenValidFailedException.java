package com.gen.script.oauth.exception;

public class TokenValidFailedException extends RuntimeException {
	private static final long serialVersionUID = -6449731321291810968L;

	public TokenValidFailedException() {
        super("Failed to generate Token.");
    }
}

