package com.yinzhiwu.yiwu.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.EmployeePostYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.entity.yzw.PostYzw;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.EmployeeApiView;
import com.yinzhiwu.yiwu.service.EmployeePostYzwService;
import com.yinzhiwu.yiwu.service.EmployeeYzwService;
import com.yinzhiwu.yiwu.service.UserService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeApiController extends BaseController {

	@Autowired private EmployeeYzwService empYzwService;
	@Autowired private UserService userService;
	@Autowired private EmployeePostYzwService empPostService;

	@GetMapping(value="/getAllCoaches")
	public List<EmployeeApiView> getAllCoaches() {
		List<EmployeePostYzw> empPosts = empPostService.findByProperties(
				new String[]{"post.id","removed"}, 
				new Object[]{PostYzw.COACH_ID,false});
		
		List<EmployeeApiView> views = new ArrayList<>();
		for(EmployeePostYzw empPost: empPosts){
			views.add(new EmployeeApiView(empPost.getEmployee()));
		}
		
		return views;
	}

	@GetMapping(value = "/list")
	public YiwuJson<List<EmployeeApiView>> list(EmployeeYzw e) {
		try {
			List<EmployeeYzw> employees = empYzwService.findByExample(e);
			List<EmployeeApiView> views = new ArrayList<>();
			for (EmployeeYzw emp : employees) {
				views.add(new EmployeeApiView(emp));
			}
			return new YiwuJson<>(views);
		} catch ( Exception e1) {
			return YiwuJson.createByErrorMessage(e1.getMessage());
		}
	}
	
	@PostMapping(value="{id}/password")
	public YiwuJson<Object> modifyPassword(@PathVariable(name="id") int id,  String newPassword){
		userService.modifyPassword(id, newPassword);
		return YiwuJson.createBySuccess();
	}
}
