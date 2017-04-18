package com.yinzhiwu.springmvc3.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseDao<T ,PK extends Serializable> {

		public T get(PK id);
		
		public PK save(T entity);
		
		public PK saveOrUpdate(T entity);
		
		
		public List<T> findAll();
		
		public List<T> findByProperty(String propertyName, Object value);
		
		public int findCountByProperty(String propertyName, Object value);
		
		public List<T> findByProperties(Map<String, Object> param);

		void delete(T entit);
		

}
