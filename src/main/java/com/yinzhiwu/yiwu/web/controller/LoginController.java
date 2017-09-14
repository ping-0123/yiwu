package com.yinzhiwu.yiwu.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yinzhiwu.yiwu.controller.BaseController;


/**
 * 
 * @author ping
 * @date 2017年9月13日下午1:58:14
 *
 */
@Controller
public class LoginController extends BaseController {

    @RequestMapping(value = "/login", method={RequestMethod.GET, RequestMethod.POST})
    public String showLoginForm(HttpServletRequest req, Model model) {
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "login";
    }
    
//    @PostMapping(value="/login")
//    public String loginSuccess(HttpServletRequest req){
//  
//    	return "index";
//    }

}
