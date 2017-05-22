package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.ProductYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.ProductYzw;

@Repository 
public class ProductYzwDaoImpl extends BaseDaoImpl<ProductYzw, Integer> implements ProductYzwDao{

	@Override
	public ProductYzw get_audit_deposit_product() {
		return get(117);
	}

	@Override
	public ProductYzw get_children_deposit_product() {
		return get(118);
	}

}
