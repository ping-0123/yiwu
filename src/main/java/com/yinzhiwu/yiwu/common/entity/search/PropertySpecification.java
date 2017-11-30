package com.yinzhiwu.yiwu.common.entity.search;

import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;


/**
 * 
 * @author ping
 * @date 2017年11月28日上午11:06:40
 * @since v2.1.5
 *	
 * @param <T> the entity type
 */
public class PropertySpecification<T> implements Specification<T> {
	
	private String propertyName;
	
	private SearchOperator operator;
	
	private Object value;
	
	

	public PropertySpecification(String propertyName, SearchOperator operator, Object value) {
		this.propertyName = propertyName;
		this.operator = operator;
		this.value = value;
	}

	

	public PropertySpecification(String propertyName, SearchOperator operator) {
		this.propertyName = propertyName;
		this.operator = operator;
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Path<?> path = CriteriaPathUtils.getNestedPath(root, this.propertyName);
		
		switch (this.operator) {
		case eq:
			return builder.equal(path, this.value);
		case ne:
			return builder.notEqual(path, this.value);
		case gt:
			return builder.greaterThan((Path<Comparable>) path,(Comparable)this.value);
		case gte:
			return builder.greaterThanOrEqualTo((Path<Comparable>) path,(Comparable)this.value);
		case lt:
			return builder.lessThan((Path<Comparable>) path,(Comparable)this.value);
		case lte:
			return builder.lessThanOrEqualTo((Path<Comparable>) path,(Comparable)this.value);
		case between:
			return builder.between((Path<Comparable>) path,((MinMaxPair)value).min(), ((MinMaxPair)value).max());
		case prefixLike:
			return builder.like((Path<String>) path,"%" + value);
		case prefixNotLike:
			return builder.notLike((Path<String>) path,"%" + value);
		case suffixLike:
			return builder.like((Path<String>) path, value + "%");
		case suffixNotLike:
			return builder.notLike((Path<String>) path,value + "%");
		case like:
			return builder.like((Path<String>) path,"%" + value + "%");
		case notLike:
			return builder.notLike((Path<String>) path,"%" + value + "%");
		case isNull:
			return builder.isNull(path);
		case isNotNull:
			return builder.isNotNull(path);
		case in:
			return builderIn(builder,(Path<Object>)path,value);
		case notIn:
			return builderIn(builder,(Path<Object>)path,value).not();	
		default:
			break;
		}
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <E> Predicate builderIn(CriteriaBuilder builder , Path<E> path, Object values ){
		In<E> in = builder.in(path);
		if(values instanceof Collection){
			for (Object obj : (Collection) values) {
				in.value((E)obj);
			}
		}else if (values.getClass().isArray()) {
			for (E val : (E[]) values) {
				in.value(val);
			} 
			
		}else {
			in.value((E) values);
		}
		return in;
	}
}
