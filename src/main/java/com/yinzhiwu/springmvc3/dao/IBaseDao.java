package com.yinzhiwu.springmvc3.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T ,PK extends Serializable> {

		public T get(PK id);
		
		public PK save(T entity);
		
		public List<T> findByProperty(String propertyName, Object value);
}
