package com.yinzhiwu.yiwu.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.ProductYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

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
