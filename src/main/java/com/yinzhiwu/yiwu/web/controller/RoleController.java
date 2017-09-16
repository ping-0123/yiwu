package com.yinzhiwu.yiwu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.RoleYzw;
import com.yinzhiwu.yiwu.service.ResourceService;
import com.yinzhiwu.yiwu.service.RoleYzwService;

/**
 * 
 * @author ping
 * @date 2017年9月15日下午3:57:30
 *
 */

@Controller
@RequestMapping("/system/roles")
public class RoleController extends BaseController {

    @Autowired
    private RoleYzwService roleService;

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("roleList", roleService.findAll());
        return "role/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        model.addAttribute("role", new RoleYzw());
        model.addAttribute("op", "新增");
        return "role/edit";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(RoleYzw role, RedirectAttributes redirectAttributes) {
    	logger.error(role.getResources());
        roleService.save(role);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/role";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        setCommonData(model);
        model.addAttribute("role", roleService.get(id));
        model.addAttribute("op", "修改");
        return "role/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(RoleYzw role, RedirectAttributes redirectAttributes) {
        roleService.update(role);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/role";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Integer id, Model model) {
        setCommonData(model);
        model.addAttribute("role", roleService.get(id));
        model.addAttribute("op", "删除");
        return "role/edit";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        roleService.delete(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/role";
    }

    private void setCommonData(Model model) {
        model.addAttribute("resourceList", resourceService.findAll());
    }

}
