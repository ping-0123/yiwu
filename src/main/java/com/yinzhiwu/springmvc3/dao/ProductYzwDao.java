package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.yzw.ProductYzw;

public interface ProductYzwDao extends IBaseDao<ProductYzw, Integer> {

	
	public ProductYzw get_audit_deposit_product();
	
	public ProductYzw get_children_deposit_product();
}
