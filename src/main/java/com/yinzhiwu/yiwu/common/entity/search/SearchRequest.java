package com.yinzhiwu.yiwu.common.entity.search;

import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月30日 下午11:59:20
*
*/

public class SearchRequest<T> implements Searchable<T> {
	
	private Specifications<T> specification;

	@Override
	public Specification<T> getSpecification() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable getPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return null;
	}

}
