package com.yinzhiwu.yiwu.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

import com.yinzhiwu.yiwu.context.Constants;
import com.yinzhiwu.yiwu.context.ThreadContext;
import com.yinzhiwu.yiwu.entity.Distributer;

/**
*@Author ping
*@Time  创建时间:2017年8月14日下午3:47:27
*
*/

public class ApiUserFilter extends OncePerRequestFilter {
	
	private static final String LOGIN_API_URL = "/api/distributer/login";
	private static final String REGISTER_API_URL = "/api/distributer/register.do";
	private static final String ERROR_API_URL = "/api/error";
	private static final String ERROR_UNLOGIN_API_URL = "/api/error/unlogin";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		/**
		response.setContentType("textml;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "0");
		response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("XDomainRequestAllowed","1");
		*/
		
		if(request.getServletPath().contains(LOGIN_API_URL)
				|| request.getServletPath().contains(ERROR_API_URL)
				|| request.getServletPath().contains(REGISTER_API_URL))
		{
			filterChain.doFilter(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		Distributer distributer = (Distributer) session.getAttribute(Constants.CURRENT_USER);
		//TODO 加强权限控制
//		if(distributer!= null && distributer instanceof Distributer){
//			UserContext.setUser((Distributer) distributer);
//			filterChain.doFilter(request, response);
//		}else {
//			request.getRequestDispatcher(ERROR_UNLOGIN_API_URL).forward(request, response);
//		}
		if(distributer !=null)
			ThreadContext.setUser((Distributer) distributer);
		filterChain.doFilter(request, response);
	}

}
