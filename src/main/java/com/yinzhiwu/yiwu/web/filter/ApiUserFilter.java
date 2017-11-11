package com.yinzhiwu.yiwu.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yinzhiwu.yiwu.context.Constants;
import com.yinzhiwu.yiwu.context.JJWTConfig;
import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
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
	
	private final List<String> throughUrls = new ArrayList<>();
	
	public ApiUserFilter() {
		throughUrls.add("/api/login");
		throughUrls.add("/api/jsms");
		throughUrls.add("/api/error");
		throughUrls.add("/api/register");
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
		
		// 通过 http preflight
		if(request.getMethod().equals(HttpMethod.OPTIONS.toString())){
			filterChain.doFilter(request, response);
			return;
		}
		
		//通过  login error url
		final String path = request.getServletPath();
		if(isThrough(path)){
			filterChain.doFilter(request, response);
			return;
		}
		/*if(path.contains(LOGIN_API_URL) || path.contains(ERROR_API_URL) || path.contains(REGISTER_API_URL))
		{
			filterChain.doFilter(request, response);
			return;
		}*/
		
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(authHeader==null || !authHeader.startsWith(JJWTConfig.AUTHORIZATION_HEADER_PREFIX)){
			throw new ServletException("Missing or invaid Authorization header");
		}
		
		final String token = authHeader.substring(JJWTConfig.AUTHORIZATION_HEADER_PREFIX.length());
		Claims claims =  Jwts.parser().setSigningKey(JJWTConfig.SECRET_KEY)
			.parseClaimsJws(token)
			.getBody();
		//判断凭证是否过期 
		if(claims.getExpiration().before(new Date()))
			throw new ServletException("token 已过期");
		Integer distributerId = claims.get(Constants.CURRENT_DISTRIBUTER_ID, Integer.class);
		if(distributerId !=null){
			DistributerService disService = SpringUtils.getBean(DistributerService.class);
			Distributer distributer;
			try {
				distributer = disService.get(distributerId);
			} catch (DataNotFoundException e) {
				throw new RuntimeException(e);
			}
			UserContext.setDistributer(distributer);
			filterChain.doFilter(request, response);
		}else {
			throw new ServletException("未登录 或无效的token");
		}
		
	}

}
