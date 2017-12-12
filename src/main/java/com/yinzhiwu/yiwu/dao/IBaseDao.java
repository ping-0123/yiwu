package com.yinzhiwu.yiwu.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import com.yinzhiwu.yiwu.common.entity.search.Searchable;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
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
	public T get(PK id) throws DataNotFoundException;
	public List<T> findAll();
	public List<T> findAll(List<Order> orders);
	public List<T> findAll(Order order);
	public List<T> findAll(Order...orders);
	public Page<T> findAll(Pageable page);
	public Page<T> findAll(Pageable page, Order...orders);
 	public List<T> findAll(Specification<T> spec);
	public Page<T> findAll(Searchable<T> search);
	public T findOne(Specification<T> spec);
	public T findOne(Searchable<T> search);
	/**
	 * 如果T中的某一成员变量的class为@Entity注解, 则查询时忽略该属性， 即查询语句没有表的关联
	 * 
	 * @param entity
	 * @return
	 * @throws IllegalAccessException 
	 */
	public List<T>  findByExample(T entity) ;
	public List<T>  findByProperties(String[] properteis, Object[] values);
	public List<T>  findByPropertiesNullValueIgnore(String[] properties, Object[] values);
	public List<T>  findByProperty(String property, Object value);
	public T  findOneByProperties(String[] properties, Object[] values) throws DataNotFoundException;
	public T  findOneByProperty(String property, Object value) throws DataNotFoundException;
	
	public long count();
	public long count(Searchable<T> search);
	public long count(Specification<T> spec);
	public Long findCount();
	public Long findCountByProperties(String[] properties, Object[] values);
	public Long findCountByPropertiesNullValueIsAll(String[] properties, Object[] values);
	public Long findCountByProperty(String property, Object value);
	public Long findCountByPropertyNullValueIsAll(String property, Object value);
	
	
	public PageBean<T> findPageOfAll(int pageNo, int pageSize);
	public PageBean<T> findPageByExample(T example, Integer pageNo, Integer pageSize);
	public PageBean<T> findPageByProperties(String[] properties, Object[] values, Integer pageNo, Integer pageSize);
	public PageBean<T> findPageByPropertiesNullValueIsAll(String[] propertyNames, Object[] values, Integer pageNum, Integer pageSize);
	public PageBean<T> findPageByProperty(String property, Object value, Integer pageNo, Integer pageSize);
	
	//增
	public PK save(T entity);
	
	/**
	 * 如果 entity 是新创建的并且 是 BaseEntity 或 BaseYzwEntity的子类
	 * 调用此函数前先调用entity.init()
	 * @param entity
	 */
	public void saveOrUpdate(T entity);

	//删
	public void delete(T entity);
	public void delete(PK id);
	public void delete(Specification<T> spec);
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
	public DataTableBean<T> findDataTableByProperty(QueryParameter parameter, String propertyName, Object value);
	public DataTableBean<T> findDataTableByProperties(QueryParameter parameter, String[] properties, Object[] values);
	
}
