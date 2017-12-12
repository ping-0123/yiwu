package com.yinzhiwu.yiwu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;

/**
 * 
 * @author ping
 * @date 2017年9月15日下午2:51:12
 *
 */

@Controller
@RequestMapping(value= "/system/organizations")
public class OrganizationController {

    @Autowired
    private DepartmentYzwService organizationService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "organization/index";
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public String showTree(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        return "organization/tree";
    }

    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.GET)
    public String showAppendChildForm(@PathVariable("parentId") Integer parentId, Model model) throws DataNotFoundException {
        DepartmentYzw parent = organizationService.get(parentId);
        model.addAttribute("parent", parent);
        DepartmentYzw child = new DepartmentYzw();
        child.setParent(parent);
        model.addAttribute("child", child);
        model.addAttribute("op", "新增");
        return "organization/appendChild";
    }

    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.POST)
    public String create(DepartmentYzw organization) {
        organizationService.save(organization);
        return "redirect:/organizations/success";
    }

    @RequestMapping(value = "/{id}/maintain", method = RequestMethod.GET)
    public String showMaintainForm(@PathVariable("id") Integer id, Model model) throws DataNotFoundException {
        model.addAttribute("organization", organizationService.get(id));
        return "organization/maintain";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(DepartmentYzw organization, RedirectAttributes redirectAttributes) {
//        organizationService.update(organization);
    	try{
    		organizationService.modify(organization.getId(), organization);
    	}catch (Exception e) {
    		e.printStackTrace();
		}
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/organizations/success";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        organizationService.delete(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/organizations/success";
    }


    @RequestMapping(value = "/{sourceId}/move", method = RequestMethod.GET)
    public String showMoveForm(@PathVariable("sourceId") Integer sourceId, Model model) throws DataNotFoundException {
        DepartmentYzw source = organizationService.get(sourceId);
        model.addAttribute("source", source);
        model.addAttribute("targetList", organizationService.findAllWithExclude(source));
        return "organization/move";
    }

    @RequestMapping(value = "/{sourceId}/move", method = RequestMethod.POST)
    public String move(
            @PathVariable("sourceId") Integer sourceId,
            @RequestParam("targetId") Integer targetId) throws DataNotFoundException {
    	DepartmentYzw source = organizationService.get(sourceId);
    	DepartmentYzw target = organizationService.get(targetId);
        organizationService.move(source, target);
        return "redirect:/organizations/success";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success() {
        return "organization/success";
    }


}
