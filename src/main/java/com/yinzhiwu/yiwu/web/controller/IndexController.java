package com.yinzhiwu.yiwu.web.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.sys.Resource;
import com.yinzhiwu.yiwu.entity.sys.User;
import com.yinzhiwu.yiwu.service.ResourceService;
import com.yinzhiwu.yiwu.service.UserService;

/**
 * 
 * @author ping
 * @date 2017年9月11日下午4:14:00
 *
 */

@Controller
public class IndexController extends BaseController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;
    

    @RequestMapping(value="/system/index", method={RequestMethod.GET})
    public String index(Model model) {
    	User user = UserContext.getUser();
        Set<String> permissions = userService.findPermissions(user);
        logger.info("permissions is " + permissions);
//        List<Resource> menus = resourceService.findMenus(permissions);
       List<Resource> menus = resourceService.findRootMenus();
        logger.info("menus size is " + menus.size() );
        model.addAttribute("menus", menus);
        model.addAttribute("employee", user.getEmployee());
        return "index";
    }
    
       
    @GetMapping(value="/")
    public String index2(Model model){
    	return "redirect:/system/index";
    }
    
    @GetMapping(value="/system")
    public String index3(Model model){
    	return "redirect:/system/index";
    }
    	
    @GetMapping("/system/welcome")
    public String welcome() {
        return "welcome";
    }


}
