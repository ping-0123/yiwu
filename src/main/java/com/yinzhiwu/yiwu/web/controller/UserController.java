package com.yinzhiwu.yiwu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;
import com.yinzhiwu.yiwu.service.RoleYzwService;
import com.yinzhiwu.yiwu.service.UserService;


/**
 * 
 * @author ping
 * @date 2017年9月15日下午4:02:32
 *
 */

@Controller
@RequestMapping("/users")
public class UserController {

	
    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentYzwService organizationService;
    @Autowired
    private RoleYzwService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "user/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        model.addAttribute("user", new EmployeeYzw());
        model.addAttribute("op", "新增");
        return "user/edit";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(EmployeeYzw user,  RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/user";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        setCommonData(model);
        model.addAttribute("user", userService.get(id));
        model.addAttribute("op", "修改");
        return "user/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(EmployeeYzw user, RedirectAttributes redirectAttributes) {
        userService.update(user);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/user";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Integer id, Model model) {
        setCommonData(model);
        model.addAttribute("user", userService.get(id));
        model.addAttribute("op", "删除");
        return "user/edit";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        userService.delete(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/user";
    }


    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.GET)
    public String showChangePasswordForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.get(id));
        model.addAttribute("op", "修改密码");
        return "user/changePassword";
    }

    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
    public String changePassword(@PathVariable("id") Integer id, String newPassword, RedirectAttributes redirectAttributes) {
        userService.modifyPassword(id, newPassword);
        redirectAttributes.addFlashAttribute("msg", "修改密码成功");
        return "redirect:/user";
    }

    private void setCommonData(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        model.addAttribute("roleList", roleService.findAll());
    }
}
