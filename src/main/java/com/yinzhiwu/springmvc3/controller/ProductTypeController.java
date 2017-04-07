package com.yinzhiwu.springmvc3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.model.ReturnedJson;
import com.yinzhiwu.springmvc3.service.ProductTypeService;

@Controller
@RequestMapping("/api/productType")
public class ProductTypeController {

	@Autowired
	private ProductTypeService ptServie;
	
	@RequestMapping("/getAll")
	@ResponseBody
	public ReturnedJson getAll(){
		List<?> l = ptServie.findAll();
		return new ReturnedJson(l);
	}
}
