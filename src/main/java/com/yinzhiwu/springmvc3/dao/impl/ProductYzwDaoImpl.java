package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.ProductYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.ProductYzw;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

@Repository 
public class ProductYzwDaoImpl extends BaseDaoImpl<ProductYzw, Integer> implements ProductYzwDao{

	@Override
	public ProductYzw get_audit_deposit_product() {
		try {
			return get(117);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

	@Override
	public ProductYzw get_children_deposit_product() {
		try {
			return get(118);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

}
