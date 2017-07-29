package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.ProductYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw.CustomerAgeType;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.ProductCardType;
import com.yinzhiwu.yiwu.service.ProductYzwService;
import com.yinzhiwu.yiwu.web.purchase.dto.ProductDto;

/**
 * @Author ping
 * @Time 创建时间:2017年7月24日下午3:50:17
 *
 */

@Service
public class ProductYzwServiceImpl extends BaseServiceImpl<ProductYzw, Integer> implements ProductYzwService {
//	@Autowired private DepartmentYzwDao departmentDao;
	@Autowired private ProductYzwDao productDao;
	
	@Autowired
	public void setDao(ProductYzwDao productYzwDao) {
		super.setBaseDao(productYzwDao);
	}

	@Override
	public List<ProductDto> findVisableDtoBycardAndAgeType(int employeeId, ProductCardType cardType,
			CustomerAgeType ageType) {
		//TODO 获取员工所在的公司Id, 员工不能看到其他公司的产品
//		int companyId = departmentDao.findCompanyIdOfEmployee(employeeId);
		
		int companyId = 48;
		List<ProductDto>  productDtos = productDao.findByCardTypeByAgeTypeByCompany(companyId, cardType, ageType);
		return productDtos;
		
	}
}
