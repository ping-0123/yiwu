package com.yinzhiwu.yiwu.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yinzhiwu.yiwu.context.JJWTConfig;
import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.util.SpringUtils;

/**
*@Author ping
*@Time  创建时间:2017年8月14日下午3:47:27
*
*/

public class ApiUserFilter extends OncePerRequestFilter {
	
	private static final String LOGIN_API_URL = "/api/distributer/login";
	private static final String REGISTER_API_URL = "/api/distributer/register.do";
	private static final String ERROR_API_URL = "/api/error";
	@SuppressWarnings("unused")
	private static final String ERROR_UNLOGIN_API_URL = "/api/error/unlogin";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(request.getServletPath().contains(LOGIN_API_URL)
				|| request.getServletPath().contains(ERROR_API_URL)
				|| request.getServletPath().contains(REGISTER_API_URL))
		{
			filterChain.doFilter(request, response);
			return;
		}
		
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(authHeader==null || !authHeader.startsWith(JJWTConfig.AUTHORIZATION_HEADER_PREFIX)){
			throw new ServletException("Missing or invaid Authorization header");
		}
		
		final String token = authHeader.substring(JJWTConfig.AUTHORIZATION_HEADER_PREFIX.length());
		
		String distributerId = request.getParameter("distributerId");
		if(distributerId !=null && distributerId.trim().length()>0){
			DistributerService disService = SpringUtils.getBean(DistributerService.class);
			Distributer distributer = disService.get(Integer.valueOf(distributerId));
			if(distributer !=null)
				UserContext.setDistributer(distributer);
		}
		 
		filterChain.doFilter(request, response);

	}

}
