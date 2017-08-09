package com.yinzhiwu.yiwu.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.model.view.DepartmentApiView;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@Controller
@RequestMapping(value = "/api/district")
public class DistrictApiController extends BaseController {

	@Autowired
	private DepartmentYzwService departmentYzwService;

	@GetMapping(value = "/list")
	@ResponseBody
	public List<DepartmentApiView> getDistrictList() {
		return departmentYzwService.findAllOperationDistricts();
	}

	@GetMapping(value="/{id}")
	@ResponseBody
	@ApiOperation(value="返回区域的部门信息")
	public DepartmentYzw getById(@PathVariable Integer id){
		return departmentYzwService.get(id);
	}

}
