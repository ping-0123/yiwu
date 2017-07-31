package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.ProductTypeDao;
import com.yinzhiwu.yiwu.entity.type.ProductType;
import com.yinzhiwu.yiwu.service.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
	private ProductTypeDao productTypeDao;

	@Override
	public ProductType findById(Integer id) {
		return productTypeDao.get(id);
	}

	@Override
	public List<ProductType> findAll() {
		return productTypeDao.findAll();
	}

}
