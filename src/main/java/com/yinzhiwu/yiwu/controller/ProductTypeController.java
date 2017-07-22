package com.yinzhiwu.yiwu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.model.ReturnedJson;
import com.yinzhiwu.yiwu.service.ProductTypeService;

@Controller
@RequestMapping("/api/productType")
public class ProductTypeController {

	@Autowired
	private ProductTypeService ptServie;
	
	@GetMapping("/getAll")
	@ResponseBody
	public ReturnedJson getAll(){
		List<?> l = ptServie.findAll();
		return new ReturnedJson(l);
	}
}
