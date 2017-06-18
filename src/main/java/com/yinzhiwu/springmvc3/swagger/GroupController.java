package com.yinzhiwu.springmvc3.swagger;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinzhiwu.springmvc3.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping(value="/group", produces={"application/json;charset=UTF-8"})
@Api(value="/group", description="群组的相关操作")
public class GroupController extends BaseController {

	@PutMapping(value="addGroup")
	@ApiOperation(notes="addGroup", httpMethod="POST" , value="Add a new uam group")
	@ApiResponses(value={@ApiResponse(code=405, message="invalid input")})
	public UamGroup addGroup(
			@ApiParam(required=true, value="group data") 
			@RequestBody
			UamGroup group){
		return group;
	}
	
	@GetMapping("getAccessibleGroups")
	@ApiOperation(notes="getAccessibleGroups", httpMethod="GET", value="获取我可以访问的群组列表")
	public java.util.List<UamGroup> getAccessibleGroups(){
		UamGroup group1 = new UamGroup("1", "test group 1");
		UamGroup group2 = new UamGroup("2", "test group 2");
		
		List<UamGroup> groups = new LinkedList<>();
		groups.add(group1);
		groups.add(group2);
		
		return groups;
	}
}
