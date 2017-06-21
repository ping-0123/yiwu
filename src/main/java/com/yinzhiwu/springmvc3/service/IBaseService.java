package com.yinzhiwu.springmvc3.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.page.PageBean;

public interface IBaseService<T, PK extends Serializable> {
	

	
	public void update(T entity);
	
	public void saveOrUpdate(T entity);
	
	public List<T> findAll() throws DataNotFoundException;
	
	public List<T> findByProperty(String propertyName, Object value);
	public int findCountByProperty(String propertyName, Object value);
	
	public int findCountByProperties(String[] propertyNames, Object[] values) throws Exception;
	
	public List<T> findByProperties(Map<String, Object> propertyMap) throws DataNotFoundException;
	
	public List<T> findByProperties(String[] propertyNames, Object[] values) throws DataNotFoundException;
	
	public List<T> findByExample(T entity) throws DataNotFoundException;

	void delete(T entit);
	
	public PK save(T entity);
	
	void deleteById(PK id);

	/**
	 * 通过dao.get(id)从数据库中取出一个新实体newEntity, 将newEntity中的属性值
	 * 设置为entity对应的值(entity中某一属性为null, 则newEntity中相应属性值不变)
	 * 改方法用到了反射
	 * 该方法忽略@OneToMany注解的属性的修改
	 * 该方法不能修改从父类继承过来的成员变量的值
	 * 
	 * @param id 需要更改的数据的id
	 * @param entity 需要更改的属性封装在entity中
	 * @throws DataNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * 
	 */
	void modify(PK id, T entity) throws DataNotFoundException, IllegalArgumentException, IllegalAccessException;
	
	public T get(PK id);
	
	
	public PageBean<T> findPage(T entity, int pageNum, int pageSize);
}
