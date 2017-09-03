package com.yinzhiwu.yiwu.web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinzhiwu.yiwu.context.Constants;
import com.yinzhiwu.yiwu.service.EmployeeYzwService;

/**
 * 
 * @author ping
 * @Date 2017年9月3日 下午10:59:10
 *
 */

public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private EmployeeYzwService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
        return true;
    }
}
