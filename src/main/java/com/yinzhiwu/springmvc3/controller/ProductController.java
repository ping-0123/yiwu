package com.yinzhiwu.springmvc3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinzhiwu.springmvc3.model.Product;
import com.yinzhiwu.springmvc3.validator.ProductValidator;

@Controller
@RequestMapping("/product")
public class ProductController  extends BaseController{

	
	@RequestMapping(value = "/add")
	public String inputProduct(Model model) {
		model.addAttribute("product", new Product());
		return "product_form";
	} 
	
	@RequestMapping(value = "/save")
	public String saveProduct(@ModelAttribute Product product, BindingResult bindingResult, Model model) {
		
		ProductValidator productValidator = new ProductValidator();
		logger.info(bindingResult.hasErrors());
		productValidator.validate(product, bindingResult);
	if (bindingResult.hasErrors()) {
		FieldError fieldError = bindingResult.getFieldError();
		logger.info("Code:" + fieldError.getCode() + ", field:" + fieldError.getField());
		return "product_form";
	} 
	
	// save product here
	model.addAttribute("product", product);
	return "product_detail";
	}
}
