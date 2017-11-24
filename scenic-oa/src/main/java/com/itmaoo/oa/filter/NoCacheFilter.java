package com.itmaoo.oa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class NoCacheFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 设置静态资源不缓存
		HttpServletResponse sResponse = (HttpServletResponse) response;
		sResponse.setHeader("Cache-Control", "no-cache");
		sResponse.setHeader("Pragma", "no-cache");
		sResponse.setDateHeader("Expires", 0);

		chain.doFilter(request, response);
	}

}
