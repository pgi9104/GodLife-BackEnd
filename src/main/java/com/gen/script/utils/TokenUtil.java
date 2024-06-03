package com.gen.script.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class TokenUtil {
	public static String getUserName() {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		return userId;
	}
}
