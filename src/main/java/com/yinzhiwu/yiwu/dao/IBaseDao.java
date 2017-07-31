package com.yinzhiwu.yiwu.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import com.yinzhiwu.yiwu.exception.DataNotFoundException;
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

	public T get(PK id);

	public PK save(T entity);

	public void saveOrUpdate(T entity);

	public List<T> findAll();

	public int findCount();

	public List<T> findByProperty(String propertyName, Object value);

	public int findCountByProperty(String propertyName, Object value);

	public List<T> findByProperties(String[] propertyNames, Object[] values);

	public int findCountByProperties(String[] propertyNames, Object[] values);

	/**
	 * 如果T中的某一成员变量的class为@Entity注解, 则查询时忽略该属性， 即查询语句没有表的关联
	 * 
	 * @param entity
	 * @return
	 * @throws DataNotFoundException
	 */
	public List<T> findByExample(T entity);

	void delete(T entit);

	void delete(PK id);

	void update(T entity);

	PageBean<T> findPageByHql(String hql, int pageNum, int pageSize);

	<R> PageBean<R> findPageByCriteria(CriteriaQuery<R> criteria, int pageNo, int pageSize, int totalSize);

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

	PageBean<T> findPageOfAll(int pageNo, int pageSize);

	PageBean<T> findPageByProperties(String[] propertyNames, Object[] values, int pageNo, int pageSize);

	PageBean<T> findPageByProperty(String propertyName, Object value, int pageNo, int pageSize);


}
