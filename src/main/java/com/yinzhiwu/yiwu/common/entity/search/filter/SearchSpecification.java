package com.yinzhiwu.yiwu.common.entity.search.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.yinzhiwu.yiwu.common.entity.search.SearchOperator;
import com.yinzhiwu.yiwu.common.entity.search.utils.CriteriaPathUtils;


/**
 * 
 * @author ping
 * @date 2017年11月28日上午11:06:40
 * @since v2.1.5
 *	
 * @param <T> the entity type
 */
public class SearchSpecification<T> implements SearchFilter, Specification<T> {
	
	private String propertyName;
	
	private SearchOperator operator;
	
	private Object value;
	
	

	public SearchSpecification(String propertyName, SearchOperator operator, Object value) {
		this.propertyName = propertyName;
		this.operator = operator;
		this.value = value;
	}

	

	public SearchSpecification(String propertyName, SearchOperator operator) {
		this.propertyName = propertyName;
		this.operator = operator;
	}



	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Path<?> path = CriteriaPathUtils.getNestedPath(root, this.propertyName);
		
		switch (this.operator) {
		case eq:
			return builder.equal(path, this.value);
		case ne:
			return builder.notEqual(path, this.value);
		case gt:
			Path<Comparable> newPath = (Path<Comparable>) path;
			return builder.greaterThan(newPath,(Comparable)this.value);
		default:
			break;
		}
		return null;
	}

}
