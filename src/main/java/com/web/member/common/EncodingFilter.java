package com.web.member.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
@SuppressWarnings("serial")
@WebFilter("/*")
public class EncodingFilter extends HttpFilter implements Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4106652596898269445L;

	public EncodingFilter() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
		
	}

}
