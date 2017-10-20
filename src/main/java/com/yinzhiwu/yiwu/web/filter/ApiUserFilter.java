package com.yinzhiwu.yiwu.web.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yinzhiwu.yiwu.context.Constants;
import com.yinzhiwu.yiwu.context.JJWTConfig;
import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.util.SpringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
*@Author ping
*@Time  创建时间:2017年8月14日下午3:47:27
*
*/

public class ApiUserFilter extends OncePerRequestFilter {
	
	private static final String LOGIN_API_URL = "/api/login";
	private static final String REGISTER_API_URL = "/api/distributer/register.do";
	private static final String ERROR_API_URL = "/api/error";
	@SuppressWarnings("unused")
	private static final String ERROR_UNLOGIN_API_URL = "/api/error/unlogin";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String path = request.getServletPath();
		if(path.contains(LOGIN_API_URL) || path.contains(ERROR_API_URL) || path.contains(REGISTER_API_URL))
		{
			filterChain.doFilter(request, response);
			return;
		}
		
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		Enumeration<String> headerNames = request.getHeaderNames();
		System.out.println("start output request headers.........");
		while (headerNames.hasMoreElements()) {
			String name = (String) headerNames.nextElement();
			System.out.println(name + ": " + request.getHeader(name));
			
		}
		if(authHeader==null || !authHeader.startsWith(JJWTConfig.AUTHORIZATION_HEADER_PREFIX)){
			throw new ServletException("Missing or invaid Authorization header");
		}
		
		final String token = authHeader.substring(JJWTConfig.AUTHORIZATION_HEADER_PREFIX.length());
		Claims claims =  Jwts.parser().setSigningKey(JJWTConfig.secretKey)
			.parseClaimsJws(token)
			.getBody();
		//判断凭证是否过期 
		if(claims.getExpiration().before(new Date()))
			throw new ServletException("token 已过期");
		Integer distributerId = claims.get(Constants.CURRENT_DISTRIBUTER_ID, Integer.class);
		if(distributerId !=null){
			DistributerService disService = SpringUtils.getBean(DistributerService.class);
			Distributer distributer = disService.get(distributerId);
			UserContext.setDistributer(distributer);
			filterChain.doFilter(request, response);
		}else {
			throw new ServletException("未登录 或无效的token");
		}
		
	}

}
