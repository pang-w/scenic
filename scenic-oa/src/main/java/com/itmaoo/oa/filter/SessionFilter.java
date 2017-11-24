package com.itmaoo.oa.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.itmaoo.oa.consts.Constants;
import com.itmaoo.oa.consts.MessageCode;
import com.itmaoo.oa.entity.ErrorInfo;
import com.itmaoo.oa.entity.RespData;
import com.itmaoo.oa.entity.vo.UserVo;

public class SessionFilter implements Filter {
	private List<String> excpUrlList;

	

	private ApplicationContext ctx;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 获取过滤例外
		String exception = filterConfig.getInitParameter("exception");
		if (StringUtils.isNotBlank(exception)) {
			String[] excpList = exception.split(Constants.COMMA);
			excpUrlList = Arrays.asList(excpList);
		}
	}

	private Boolean filterNonCheckUrl(String url) {
		if (!StringUtils.isEmpty(url)) {
			for (String excpUrl : excpUrlList) {
				// 判断是否不需要检查
				if (Pattern.compile(excpUrl).matcher(url).matches()) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();

		if (ctx == null) {
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(httpRequest.getSession().getServletContext());
			if (null == ctx) {
				throw new ExceptionInInitializerError("spring context is not loaded!");
			}
		}

		// 请求前路径
		String before = "";

		// 过滤例外
		String requestURI = httpRequest.getRequestURI();
		if (filterNonCheckUrl(requestURI)) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}

		UserVo loginUser = (UserVo) session.getAttribute(Constants.USER_SESSION_KEY);
		// 登录过滤
		if (null == loginUser) {
			// 判断ajax请求
			String header = httpRequest.getHeader("Accept");
			String pattern = ".*application/json.*";
			String pattern2 = "application/xhtml\\+xml";
			if (Pattern.compile(pattern).matcher(header).matches()
					|| Pattern.compile(pattern2).matcher(header).find()) {
				if (requestURI.contains(Constants.HTML)) {
					String contextPath = httpRequest.getContextPath();
					httpRequest.getSession().setAttribute(Constants.BEFORE_URL, before);
					httpResponse.sendRedirect(contextPath + "/views/login.html");
				}
				httpRequest.getSession().setAttribute(Constants.BEFORE_URL, before);
				RespData respData = new RespData();
				// 用户未登录，或session超时
				respData.setErrorInfo(new ErrorInfo(MessageCode.M9998, "ITMAOO"));
				response.getWriter().println(respData.toJsonString());
			} else {
				String contextPath = httpRequest.getContextPath();
				httpRequest.getSession().setAttribute(Constants.BEFORE_URL, before);
				httpResponse.sendRedirect(contextPath + "/views/login.html");
			}
			return;
		} else {
			chain.doFilter(httpRequest, httpResponse);
		}

	}

	@Override
	public void destroy() {

	}

}
