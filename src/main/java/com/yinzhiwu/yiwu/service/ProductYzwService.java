package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw.CustomerAgeType;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.ProductCardType;
import com.yinzhiwu.yiwu.web.purchase.dto.ProductDto;

/**
 * @Author ping
 * @Time 创建时间:2017年7月24日下午3:49:32
 *
 */

public interface ProductYzwService extends IBaseService<ProductYzw, Integer> {

	List<ProductDto> findVisableDtoBycardAndAgeType(int employeeId, ProductCardType cardType, CustomerAgeType ageType);


}
