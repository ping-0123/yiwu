package com.yinzhiwu.springmvc3.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yinzhiwu.springmvc3.model.page.PageBean;

public interface IBaseDao<T ,PK extends Serializable> {

		public T get(PK id);
		
		public PK save(T entity);
		
		public void saveOrUpdate(T entity);
		
		
		public List<T> findAll();
		
		public int findCount();
		
		public List<T> findByProperty(String propertyName, Object value);
		
		public int findCountByProperty(String propertyName, Object value);
		
		
		public List<T> findByProperties(Map<String, Object> param);
		
		public List<T> findByProperties(String[] propertyNames, Object[] values);
		
		public int findCountByProperties(String[] propertyNames, Object[] values) throws Exception;
		
		public List<T> findByExample(T entity);

		void delete(T entit);
		
		void delete(PK id);

		void update(T entity);



		PageBean<T> findPageByHql(String hql, int pageNum, int pageSize);
		

}
