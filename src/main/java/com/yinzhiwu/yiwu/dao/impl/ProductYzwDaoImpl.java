package com.yinzhiwu.yiwu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Where;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.ProductYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw.CustomerAgeType;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.ProductCardType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.web.purchase.dto.ProductDto;

@Repository
public class ProductYzwDaoImpl extends BaseDaoImpl<ProductYzw, Integer> implements ProductYzwDao {

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

	@Override
	public List<ProductDto> findByCardTypeByAgeTypeByCompany(int companyId, ProductCardType cardType, CustomerAgeType ageType) {
		StringBuilder hqlBuilder = new StringBuilder();
		hqlBuilder.append("SELECT new com.yinzhiwu.yiwu.web.purchase.dto.ProductDto(p)");
		hqlBuilder.append(" FROM ProductYzw p");
		hqlBuilder.append(" WHERE p.visableDepartmentIds like '%" + companyId + "%'");
		hqlBuilder.append(" AND p.cardType = :cardType");
		hqlBuilder.append(" AND p.customerType = :ageType");
		hqlBuilder.append(" AND p.endDate > :currentDate");
		
		List<ProductDto> dtos =  getSession().createQuery(hqlBuilder.toString(), ProductDto.class)
				.setParameter("cardType", cardType)
				.setParameter("ageType", ageType)
				.setParameter("currentDate", new Date())
				.getResultList();
		if(dtos == null){
			return new ArrayList<>();
		}
		return dtos;
	}

	@Override
	@Where(clause ="obsolete_flag=false")
	public DataTableBean<ProductYzw> findDataTable(QueryParameter parameter) {
		return super.findDataTable(parameter);
	}

	@Override
	@Where(clause ="obsolete_flag=false")
	public DataTableBean<ProductYzw> findDataTableByProperties(QueryParameter parameter, String[] properties, Object[] values) {
		return super.findDataTableByProperties(parameter, properties, values);
	}

	@Override
	@Where(clause ="obsolete_flag=false")
	public DataTableBean<ProductYzw> findDataTableByProperty(QueryParameter parameter, String propertyName, Object value) {
		return super.findDataTableByProperty(parameter, propertyName, value);
	}
	
	

}
