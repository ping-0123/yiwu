package com.yinzhiwu.yiwu.web.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.web.bind.anotation.CurrentUser;

/**
 * 
 * @author ping
 * @date 2017年9月11日下午4:14:00
 *
 */

@Controller
public class IndexController {

//    @Autowired
//    private ResourceService resourceService;
//    @Autowired
//    private UserService userService;

    @RequestMapping(value="/", method={RequestMethod.GET,RequestMethod.POST})
    public String index(@CurrentUser EmployeeYzw user, Model model) {
//        Set<String> permissions = userService.findPermissions(loginUser.getName());
//        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", new ArrayList<>());
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}
