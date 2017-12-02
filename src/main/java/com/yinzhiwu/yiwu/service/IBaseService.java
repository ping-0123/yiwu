package com.yinzhiwu.yiwu.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.common.entity.search.Searchable;
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
	public void delete(Specification<T> spec);
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
	
	public List<T> findAll() ;
	public Page<T> findAll(Searchable<T> seach);
	public List<T> findAll(Specification<T> spec);
	public T findOne(Specification<T> spec);
	/**
	 * 通过排序，选择满足条件的记录中理想的一个
	 * @param search
	 * @return
	 */
	public T findOne(Searchable<T> search);
	public List<T> findByExample(T entity);
	public List<T>  findByProperties(String[] properteis, Object[] values);
	public List<T>  findByPropertiesNullValueIgnore(String[] properties, Object[] values);
	public List<T>  findByProperty(String property, Object value);
	public T  findOneByProperties(String[] properties, Object[] values) throws DataNotFoundException;
	public T  findOneByProperty(String property, Object value) throws DataNotFoundException;
	
	public long count();
	public long count(Searchable<T> search);
	@Deprecated
	public Long findCount();
	@Deprecated
	public Long findCountByProperties(String[] properties, Object[] values);
	@Deprecated
	public Long findCountByPropertiesNullValueIsAll(String[] properties, Object[] values);
	@Deprecated
	public Long findCountByProperty(String property, Object value);
	@Deprecated
	public Long findCountByPropertyNullValueIsAll(String property, Object value);
	
	
	@Transactional
	public PageBean<T> findPageOfAll(int pageNum, int pageSize);
	
	@Transactional
	public PageBean<T> findPageByExample(T example, Integer pageNum, Integer pageSize);
	
	public PageBean<T> findPageByProperties(String[] properties, Object[] values, Integer pageNo, Integer pageSize);
	public PageBean<T> findPageByPropertiesNullValueIsAll(String[] propertyNames, Object[] values, Integer pageNum, Integer pageSize);
	public PageBean<T> findPageByProperty(String property, Object value, Integer pageNo, Integer pageSize);
	
	@Transactional
	public DataTableBean<T> findDataTable(QueryParameter parameter);
	public DataTableBean<T> findDataTableByProperties(QueryParameter parameter ,String[] propertyNames, Object[] values);
	public DataTableBean<T> findDataTableByProperty(QueryParameter parameter, String property, Object value);
}
