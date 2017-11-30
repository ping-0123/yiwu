package com.yinzhiwu.yiwu.common.entity.search;

import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月30日 下午11:28:57
*
*/

public interface Searchable<T> {
	
	public Specification<T> getSpecification();
	
	public Pageable getPage();
	
	public List<Order> getOrders();
}
