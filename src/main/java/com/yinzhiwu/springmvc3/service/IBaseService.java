package com.yinzhiwu.springmvc3.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseService<T, PK extends Serializable> {
	
	public T get(PK id);
	
	public PK save(T entity);
	
	public void update(T entity);
	
	public void saveOrUpdate(T entity);
	
	public List<T> findAll();
	
	public List<T> findByProperty(String propertyName, Object value);
	
	public int findCountByProperty(String propertyName, Object value);
	
	public List<T> findByProperties(Map<String, Object> propertyMap);
	
	public List<T> findByProperties(String[] propertyNames, Object[] values);
	
	public List<T> findByExample(T entity);

	void delete(T entit);
	
	void deleteById(PK id);
}
