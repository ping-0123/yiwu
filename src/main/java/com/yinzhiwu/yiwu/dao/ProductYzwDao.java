package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw.CustomerAgeType;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.ProductCardType;
import com.yinzhiwu.yiwu.web.purchase.dto.ProductDto;

public interface ProductYzwDao extends IBaseDao<ProductYzw, Integer> {

	public ProductYzw get_audit_deposit_product();

	public ProductYzw get_children_deposit_product();

	public List<ProductDto> findByCardTypeByAgeTypeByCompany(int companyId, ProductCardType cardType,
			CustomerAgeType ageType);
}
