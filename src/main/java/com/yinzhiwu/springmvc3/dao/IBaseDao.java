package com.yinzhiwu.springmvc3.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.page.PageBean;

public interface IBaseDao<T ,PK extends Serializable> {

		public T get(PK id) throws DataNotFoundException;
		
		public PK save(T entity);
		
		public void saveOrUpdate(T entity);
		
		
		public List<T> findAll() throws DataNotFoundException;
		
		public int findCount();
		
		public List<T> findByProperty(String propertyName, Object value) throws DataNotFoundException;
		
		public int findCountByProperty(String propertyName, Object value);
		
		
		public List<T> findByProperties(Map<String, Object> param) throws DataNotFoundException;
		
		public List<T> findByProperties(String[] propertyNames, Object[] values) throws DataNotFoundException;
		
		public int findCountByProperties(String[] propertyNames, Object[] values) throws Exception;
		
		public List<T> findByExample(T entity) throws DataNotFoundException;

		void delete(T entit);
		
		void delete(PK id);

		void update(T entity);



		PageBean<T> findPageByHql(String hql, int pageNum, int pageSize);
		
		

}
