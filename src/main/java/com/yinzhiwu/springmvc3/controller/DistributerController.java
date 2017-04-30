package com.yinzhiwu.springmvc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.entity.Customer;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.service.DistributerService;

@RestController
@RequestMapping("/distributer")
public class DistributerController {
	
	@Autowired
	private DistributerService  distributerService;

	
	@GetMapping("/test")
	public Object save(){
		Customer customer = new Customer();
		customer.setId(21);
		Distributer d = new Distributer();
		System.out.println(d.getCreateDate());
		int id = distributerService.save(d);
		
		return distributerService.get(id).getMemberId();
	}
	
	@GetMapping("/{id}")
	public Object findById(@PathVariable int id){
		return distributerService.get(id);
	}
	
	@GetMapping("/test2")
	public Object testTransfer(@ModelAttribute Distributer distributer){
		return distributer.getRegistedTime();
	}
}
