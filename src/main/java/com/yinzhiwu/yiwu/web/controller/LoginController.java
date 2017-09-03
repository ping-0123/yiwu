package com.yinzhiwu.yiwu.web.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author ping
 * @Date 2017年9月3日 下午9:24:26
 *
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login"    )
    public String showLoginForm(HttpServletRequest req, Model model) {
    	//System.out.println("---------------------------------------------");
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        //System.out.println("++++++++++++++++++++++++++++");
        model.addAttribute("error", error);
        return "login";
    }
    
   

}
