package com.yinzhiwu.springmvc3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.ProductTypeDao;
import com.yinzhiwu.springmvc3.entity.ProductType;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.service.ProductTypeService;



@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	
	@Autowired
	private ProductTypeDao productTypeDao;

	@Override
	public List<ProductType> findAll() {
		return productTypeDao.findAll();
	}

	@Override
	public ProductType findById(Integer id) {
		try {
			return productTypeDao.get(id);
		} catch (DataNotFoundException e) {
			return null;
		}
	}


}
