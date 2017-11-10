package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.ProductType;

public interface ProductTypeService {

	public List<ProductType> findAll();

	public ProductType findById(Integer id);

}
