package com.yinzhiwu.yiwu.dao;

import java.io.Serializable;
import java.util.List;

import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.model.page.PageBean;

/**
 * 
 * @author ping
 *
 * @param <T>
 *            the entity type
 * @param <PK>
 *            the entity's primary key type
 */
public interface IBaseDao<T, PK extends Serializable> {

	//查
	public T get(PK id);
	public List<T> findAll();
	public Long findCount();
	public PageBean<T> findPageOfAll(int pageNo, int pageSize);
	public PageBean<T> findPageByExample(T example, Integer pageNo, Integer pageSize);
	
	/**
	 * 如果T中的某一成员变量的class为@Entity注解, 则查询时忽略该属性， 即查询语句没有表的关联
	 * 
	 * @param entity
	 * @return
	 * @throws IllegalAccessException 
	 */
	public List<T> findByExample(T entity) ;
	
	//增
	public PK save(T entity);
	public void saveOrUpdate(T entity);

	//删
	void delete(T entity);

	void delete(PK id);
	public void deleteLogic(T entity);
	public void deleteLogic(PK id);
	

	//改
	void update(T entity);

	/**
	 * 修改source的属性修改为target对应的属性值， 如果 target对应的属性值为null， 则source对应的属性 保持不变.
	 * 此函数要求 T 所有的属性类型为封装对象
	 * 
	 * @param source
	 *            被修改的对象
	 * @param target
	 *            封装了{@code source}对象需要修改的属性
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	void modify(T source, T target) throws IllegalArgumentException, IllegalAccessException;

	/**
	 * T source = get(id)
	 * 
	 * @see IBaseDao#modify(T, T)
	 * @param id
	 * @param target
	 * @throws DataNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	void modify(PK id, T target) throws DataNotFoundException, IllegalArgumentException, IllegalAccessException;


	public DataTableBean<T> findDataTable(QueryParameter parameter);
}
