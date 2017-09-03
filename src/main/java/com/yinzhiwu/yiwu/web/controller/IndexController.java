package com.yinzhiwu.yiwu.web.controller;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinzhiwu.yiwu.entity.sys.Resource;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.web.bind.annotation.CurrentUser;


/**
 * 
 * @author ping
 * @Date 2017年9月3日 下午9:23:44
 *
 */
@Controller
public class IndexController {


    @RequestMapping("/")
    public String index(@CurrentUser EmployeeYzw loginUser, Model model) {
//        Set<String> permissions = loginUser.getStringPermissions();
        Set<Resource> menus = loginUser.getResources();
        model.addAttribute("menus", menus);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}
