package com.yinzhiwu.yiwu.common.entity.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;


/**
* @author 作者 ping
* @Date 创建时间：2017年11月30日 下午11:59:20
*
*/

public class SearchRequest<T> implements Searchable<T> {
	
	private Specifications<T> specification;
	private Pageable pageable;
	private List<Order> orders = new ArrayList<>();
	
	

	public SearchRequest() {
	}

	public SearchRequest(Specifications<T> specification, Pageable pageable, List<Order> orders) {
		this.specification = specification;
		this.pageable = pageable;
		this.orders.addAll(orders);
	}

	@Override
	public Specification<T> getSpecification() {
		return specification;
	}

	@Override
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public Pageable getPageable() {
		return pageable;
	}

	@Override
	public Searchable<T> setPageable(Pageable pageable) {
		this.pageable = pageable;
		return this;
	}

	@Override
	public Searchable<T> setPageable(int offset, int size){
		this.pageable = new PageRequest(offset, size);
		return this;
	}
	
	
	@Override
	public Searchable<T> and(String propertyName, SearchOperator operator, Object value){
		Specifications<T> spec = Specifications.where(new PropertySpecification<>(propertyName, operator,value));
		this.specification = null ==this.specification?spec:this.specification.and(spec);
		return this;
	}
	
	@Override
	public Searchable<T> and(Specification<T> spec){
		this.specification = null ==this.specification?
				Specifications.where(spec):this.specification.and(spec);
		return this;
	}
	
	@Override
	public Searchable<T> or(String propertyName, SearchOperator operator, Object value){
		Specifications<T> spec = Specifications.where(new PropertySpecification<>(propertyName, operator,value));
		this.specification = null ==this.specification?spec:this.specification.or(spec);
		return this;
	}
	
	@Override
	public Searchable<T> or(Specification<T> spec){
		this.specification = null ==this.specification?
				Specifications.where(spec):this.specification.or(spec);
		return this;
	}
	
	@Override
	public Searchable<T> not(){
		this.specification = null ==this.specification?
				null:Specifications.not(this.specification);
		return this;
	}
	
	@Override
	public Searchable<T> addOrder(Direction direction ,String propertyName){
		this.orders.add(new Order(direction, propertyName));
		return this;
	}
	
	@Override
	public Searchable<T> addOrder(Order order){
		this.orders.add(order);
		return this;
	}
	
	@Override
	public Searchable<T> addOrder(String propertyName){
		this.orders.add(new Order(propertyName));
		return this;
	}
	
	@Override
	public Searchable<T> addOrders(List<Order> orders){
		this.orders.addAll(orders);
		return this;
	}
	
	@Override
	public Searchable<T> addOrders(Order[] orders){
		for (Order order : orders) {
			this.orders.add(order);
		}
		return this;
	}
	
	@Override
	public Searchable<T> addOrders(Direction direction, List<String> propertyNames){
		for (String propertyName : propertyNames) {
			this.orders.add(new Order(direction,propertyName));
		}
		return this;
	}
	
	@Override
	public Searchable<T> addOrders(Direction direction, String[] propertyNames){
		for (String propertyName : propertyNames) {
			this.orders.add(new Order(direction,propertyName));
		}
		return this;
	}
	
	@Override
	public Searchable<T> addOrders(String[] propertyNames){
		return addOrders(Direction.ASC,propertyNames);
	}
	
	
}
