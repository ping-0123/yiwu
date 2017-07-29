package com.yinzhiwu.yiwu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.ProductTypeDao;
import com.yinzhiwu.yiwu.entity.type.ProductType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
	private ProductTypeDao productTypeDao;

	@Override
	public List<ProductType> findAll() {
		try {
			return productTypeDao.findAll();
		} catch (DataNotFoundException e) {
			return new ArrayList<>();
		}
	}

	@Override
	public ProductType findById(Integer id) {
		return productTypeDao.get(id);
	}

}
