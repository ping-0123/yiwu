package com.yinzhiwu.yiwu.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;

/**
 * 
 * @author ping
 * @Date 2017年9月3日 下午9:25:07
 *
 */
@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentYzwService departmentService;

    @RequiresPermissions("department:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "organization/index";
    }

    @RequiresPermissions("department:view")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public String showTree(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return "organization/tree";
    }

    @RequiresPermissions("department:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.GET)
    public String showAppendChildForm(@PathVariable("parentId") Integer parentId, Model model) {
        DepartmentYzw parent = departmentService.get(parentId);
        model.addAttribute("parent", parent);
        DepartmentYzw child = new DepartmentYzw();
        child.setSuperior(parent);
        model.addAttribute("child", child);
        model.addAttribute("op", "新增");
        return "organization/appendChild";
    }

    @RequiresPermissions("department:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.POST)
    public String create(DepartmentYzw dept) {
        departmentService.save(dept);
        return "redirect:/organization/success";
    }

    @RequiresPermissions("department:update")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showMaintainForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("organization", departmentService.get(id));
        return "organization/maintain";
    }

    @RequiresPermissions("department:update")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String update(DepartmentYzw organization, RedirectAttributes redirectAttributes) {
        departmentService.update(organization);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/organization/success";
    }

    @RequiresPermissions("department:delete")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        departmentService.delete(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/organization/success";
    }


    @RequiresPermissions("department:update")
    @RequestMapping(value = "/{sourceId}/move", method = RequestMethod.GET)
    public String showMoveForm(@PathVariable("sourceId") Integer sourceId, Model model) {
        DepartmentYzw source = departmentService.get(sourceId);
        model.addAttribute("source", source);
        model.addAttribute("targetList", departmentService.findAllWithExclude(source));
        return "organization/move";
    }

    @RequiresPermissions("department:update")
    @RequestMapping(value = "/{sourceId}/move", method = RequestMethod.POST)
    public String move(
            @PathVariable("sourceId") Integer sourceId,
            @RequestParam("targetId") Integer targetId) {
        DepartmentYzw source = departmentService.get(sourceId);
        DepartmentYzw target = departmentService.get(targetId);
        departmentService.move(source, target);
        return "redirect:/organization/success";
    }

    @RequiresPermissions("department:view")
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success() {
        return "organization/success";
    }


}
