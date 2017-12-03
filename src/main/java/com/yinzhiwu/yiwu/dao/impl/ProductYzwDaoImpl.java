package com.yinzhiwu.yiwu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.common.entity.search.PropertySpecification;
import com.yinzhiwu.yiwu.common.entity.search.SearchOperator;
import com.yinzhiwu.yiwu.common.entity.search.SearchRequest;
import com.yinzhiwu.yiwu.common.entity.search.Searchable;
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
	public List<ProductYzw> findAll() {
		return super.findAll( Specifications
				.where(new PropertySpecification<ProductYzw>("isObsolete", SearchOperator.eq, false)));
	}
	
	

	@Override
	public Page<ProductYzw> findAll(Searchable<ProductYzw> search) {
		Searchable<ProductYzw> newSearch = null==search?new SearchRequest<>():search;
		newSearch.and("isObsolete", SearchOperator.eq, false);
		return super.findAll(newSearch);
	}

	@Override
	public List<ProductYzw> findAll(Specification<ProductYzw> spec) {
		Specifications<ProductYzw> newSpec = Specifications
				.where(new PropertySpecification<ProductYzw>("isObsolete", SearchOperator.eq, false))
				.and(spec);
		return super.findAll(newSpec);
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
	public DataTableBean<ProductYzw> findDataTable(QueryParameter parameter) {
		
		return super.findDataTableByProperty(parameter, "isObsolete",false);
	}

	@Override
//	@Where(clause ="obsolete_flag=false")
	public DataTableBean<ProductYzw> findDataTableByProperty(QueryParameter parameter, String propertyName, Object value) {
		return findDataTableByProperties(parameter, new String[]{propertyName}, new Object[]{value});
	}
	
	@Override
	public DataTableBean<ProductYzw> findDataTableByProperties(QueryParameter parameter, String[] properties, Object[] values) {
		String[] newProperties = new String[properties.length + 1];
		Object[] newValues = new Object[newProperties.length];
		System.arraycopy(properties, 0, newProperties, 0, properties.length);
		newProperties[newProperties.length-1] = "isObsolete";
		System.arraycopy(values, 0, newValues, 0, values.length);
		newValues[properties.length] = false;
		return super.findDataTableByProperties(parameter, newProperties, newValues);
	}

	
	
	

}
