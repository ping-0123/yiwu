package com.yinzhiwu.yiwu.web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinzhiwu.yiwu.context.Constants;
import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.entity.sys.User;
import com.yinzhiwu.yiwu.service.UserService;


/**
 * 
 * @author ping
 * @date 2017年9月14日下午9:29:24
 *
 */

@Component
public class SysUserFilter extends PathMatchingFilter {

	private static final Log logger = LogFactory.getLog(SysUserFilter.class);
	
    @Autowired
    private UserService userService;
//    @Autowired private SpringCacheManagerWrapper cacheManager;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
    	User user = (User) SecurityUtils.getSubject().getSession().getAttribute(Constants.CURRENT_USER);
    	
    	if(user ==null){
	        String username = (String)SecurityUtils.getSubject().getPrincipal();
	        if(! StringUtils.hasText(username)){
	        	logger.error("用户未登录系统");
	        	return false;
	        }
	        user =userService.findByUsername(username);
	        if(user == null){
	        	logger.error("系统异常， 用户被删除");
	        	return false;
	        }
	        SecurityUtils.getSubject().getSession().setAttribute(Constants.CURRENT_USER, user);
    	}
        
        UserContext.setUser(user);
        return true;
    }
}
