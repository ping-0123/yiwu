package com.yinzhiwu.yiwu.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.model.page.PageBean;

public interface IBaseService<T, PK extends Serializable> {

	@Transactional
	public PK save(T entity);

	@Transactional
	public void delete(T entit);
	@Transactional
	public void delete(PK id);
	@Transactional
	public void deleteLogic(T entity);
	@Transactional
	public void deleteLogic(PK id);
	
	
	@Transactional
	public void update(T entity);
	@Transactional
	public void saveOrUpdate(T entity);
	
	/**
	 * 
	 * 通过dao.get(id)从数据库中取出一个新实体newEntity, 将newEntity中的属性值
	 * 设置为entity对应的值(entity中某一属性为null, 则newEntity中相应属性值不变) 改方法用到了反射
	 * 该方法忽略@OneToMany注解的属性的修改 该方法不能修改从父类继承过来的成员变量的值
	 * 
	 * @param id
	 *            需要更改的数据的id
	 * @param entity
	 *            需要更改的属性封装在entity中
	 * @throws DataNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * 
	 */
	@Transactional
	public void modify(PK id, T entity) throws DataNotFoundException, IllegalArgumentException, IllegalAccessException;
	
	@Transactional
	public void modify(T source, T target) throws IllegalArgumentException, IllegalAccessException;
	
	public T get(PK id) throws DataNotFoundException;
	@Transactional
	public List<T> findAll() ;
	@Transactional
	public List<T> findByExample(T entity);

	@Transactional
	public PageBean<T> findPageOfAll(int pageNum, int pageSize);
	@Transactional
	public PageBean<T> findPageByExample(T example, Integer pageNum, Integer pageSize);
	
	@Transactional
	public DataTableBean<T> findDataTable(QueryParameter parameter);
}
