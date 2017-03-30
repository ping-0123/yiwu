package com.yinzhiwu.springmvc3.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.yinzhiwu.springmvc3.entity.Customer;
import com.yinzhiwu.springmvc3.service.CustomerService;

public class StringToCustomerConvertor 
	implements Converter<String, Customer>
{

	@Autowired
	private CustomerService s;
	
	@Override
	public Customer convert(String arg0) {
		return s.findByWeChat(arg0);
	}

}
