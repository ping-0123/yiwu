package com.yinzhiwu.yiwu.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yinzhiwu.yiwu.context.JJWTConfig;
import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.service.JJWTService;
import com.yinzhiwu.yiwu.util.SpringUtils;

/**
*@Author ping
*@Time  创建时间:2017年8月14日下午3:47:27
*
*/

public class ApiUserFilter extends OncePerRequestFilter {
	
	private final List<String> throughUrls = new ArrayList<>();
	private final JJWTService jjwtService;
	
	public ApiUserFilter() {
		throughUrls.add("/api/login");
		throughUrls.add("/api/jsms");
		throughUrls.add("/api/error");
		throughUrls.add("/api/register");
		throughUrls.add("/api/store");
		throughUrls.add("/api/employee");
		throughUrls.add("/api/temp");
		throughUrls.add("/api/district");
		jjwtService = SpringUtils.getBean(JJWTService.class);
	}
	
	private boolean isThrough(String path){
		for (String url : throughUrls) {
			if(path.contains(url))
				return true;
		}
		return false;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// OPTIONS请求不鉴权
		if(request.getMethod().equals(HttpMethod.OPTIONS.toString()) 
				// 指定的 url 不鉴权
				||isThrough(request.getServletPath())){
			;
		}else {
			final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			if(authHeader==null || !authHeader.startsWith(JJWTConfig.AUTHORIZATION_HEADER_PREFIX)){
				throw new ServletException("Missing or invaid Authorization header");
			}
			
			final String token = authHeader.substring(JJWTConfig.AUTHORIZATION_HEADER_PREFIX.length());
			UserContext.setDistributer(jjwtService.parseDistributerToken(token));
		}
		
		filterChain.doFilter(request, response);
		
	}

}
