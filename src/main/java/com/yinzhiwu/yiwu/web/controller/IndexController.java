package com.yinzhiwu.yiwu.web.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinzhiwu.yiwu.entity.Distributer;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
public class IndexController {

//    @Autowired
//    private ResourceService resourceService;
//    @Autowired
//    private UserService userService;

    @GetMapping("/")
    public String index(Distributer loginUser, Model model) {
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
