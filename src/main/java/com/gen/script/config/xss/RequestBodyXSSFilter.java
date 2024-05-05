package com.gen.script.config.xss;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RequestBodyXSSFilter implements Filter {
	private List<String> extUrl;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		RequestWrapper reqWrapper = null;
		String path = ((HttpServletRequest) req).getServletPath();

		try {
			if (!extUrl.isEmpty() && !extUrl.stream().anyMatch(ex -> path.contains(ex))) {
				reqWrapper = new RequestWrapper(request);
				chain.doFilter(reqWrapper, response);
			} else {
				chain.doFilter(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String excludePattern = filterConfig.getInitParameter("extUrls");
		extUrl = Arrays.asList(excludePattern.split(","));
	}

	@Override
	public void destroy() {
	}
}
