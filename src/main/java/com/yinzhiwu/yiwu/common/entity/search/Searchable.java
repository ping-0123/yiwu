package com.yinzhiwu.yiwu.common.entity.search;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月30日 下午11:28:57
*
*/

public interface Searchable<T> {
	
	public Specification<T> getSpecification();
	
	public Pageable getPageable();		
	
	public List<Order> getOrders();

	Searchable<T> not();

	Searchable<T> setPageable(Pageable pageable);

	Searchable<T> setPageable(int offset, int size);

	Searchable<T> and(String propertyName, SearchOperator operator, Object value);


	Searchable<T> and(Specification<T> spec);

	Searchable<T> or(String propertyName, SearchOperator operator, Object value);

	Searchable<T> or(Specification<T> spec);

	Searchable<T> addOrder(Direction direction, String propertyName);
	
	Searchable<T> addOrder(Order order);

	Searchable<T> addOrder(String propertyName);

	Searchable<T> addOrders(List<Order> orders);

	Searchable<T> addOrders(Order[] orders);

	Searchable<T> addOrders(Direction direction, List<String> propertyNames);

	Searchable<T> addOrders(Direction direction, String[] propertyNames);

	Searchable<T> addOrders(String[] propertyNames);
	
}
