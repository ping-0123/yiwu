package com.yinzhiwu.springmvc3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.yinzhiwu.springmvc3.config.UserDTO;

@RequestMapping(value="/rc/swagger")
@Controller
public class SwaggerTestController {

	/**
	 * 根据用户名获取用户对象
	 * @param name
	 * @return
	 */
	
	@RequestMapping(value="/name/{name}", method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据用户名获取", 
		httpMethod="GET", 
		response=UserDTO.class,
		notes="根据用户名获取用户对象")
	public UserDTO getUserByName(@ApiParam(required=true, name="name", value="用"
			+ "户名") @PathVariable String name){
		UserDTO user = new UserDTO();
		user.setName(name);
		return user;
	}
}
