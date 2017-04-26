package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.ProductType;

public interface ProductTypeService {
	
	public List<ProductType> findAll();

	public ProductType findById(Integer id);
	
}
