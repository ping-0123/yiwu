package com.yinzhiwu.yiwu.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.yinzhiwu.yiwu.common.entity.search.Searchable;
import com.yinzhiwu.yiwu.dao.IBaseDao;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.service.IBaseService;

public abstract class BaseServiceImpl<T, PK extends Serializable> implements IBaseService<T, PK> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private IBaseDao<T, PK> baseDao;

	public final IBaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	public final void setBaseDao(IBaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}

	public final IBaseDao<T, PK> getDao() {
		return baseDao;
	}

	@Override
	public T get(PK id) throws DataNotFoundException {
		return baseDao.get(id);
	}

	@Override
	public PK save(T entity) {
		return baseDao.save(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		baseDao.saveOrUpdate(entity);
	}

	
	
	@Override
	public Page<T> findAll(Searchable<T> seach) {
		return baseDao.findAll(seach);
	}

	@Override
	public List<T> findAll(Specification<T> spec) {
		return baseDao.findAll(spec);
	}

	@Override
	public List<T> findAll()  {
		return baseDao.findAll();
	}



	@Override
	public List<T> findByExample(T entity) {
		return baseDao.findByExample(entity);
	}
	
	
	@Override
	public List<T> findByProperties(String[] properteis, Object[] values) {
		return baseDao.findByProperties(properteis, values);
	}
	
	
	@Override
	public List<T> findByPropertiesNullValueIgnore(String[] properties, Object[] values) {
		return baseDao.findByPropertiesNullValueIgnore(properties, values);
	}

	@Override
	public List<T> findByProperty(String property, Object value) {
		return baseDao.findByProperty(property, value);
	}

	@Override
	public T findOneByProperties(String[] properties, Object[] values) throws DataNotFoundException {
		return baseDao.findOneByProperties(properties, values);
	}

	@Override
	public T findOneByProperty(String property, Object value) throws DataNotFoundException {
		return baseDao.findOneByProperty(property, value);
	}

	@Override
	public T findOne(Specification<T> spec) {
		return baseDao.findOne(spec);
	}

	@Override
	public T findOne(Searchable<T> search) {
		return baseDao.findOne(search);
	}

	@Override
	public long count() {
		return baseDao.count();
	}

	@Override
	public long count(Searchable<T> search) {
		return baseDao.count(search);
	}

	@Override
	public Long findCount() {
		return baseDao.findCount();
	}

	@Override
	public Long findCountByProperties(String[] properties, Object[] values) {
		return baseDao.findCountByProperties(properties, values);
	}

	@Override
	public Long findCountByPropertiesNullValueIsAll(String[] properties, Object[] values) {
		return baseDao.findCountByPropertiesNullValueIsAll(properties, values);
	}

	@Override
	public Long findCountByProperty(String property, Object value) {
		return baseDao.findCountByProperty(property, value);
	}

	@Override
	public Long findCountByPropertyNullValueIsAll(String property, Object value) {
		return baseDao.findCountByProperty(property, value);
	}

	@Override
	public void delete(T entity) {
		baseDao.delete(entity);
	}
	
	@Override
	public void delete(PK id){
		baseDao.delete(id);
	}

	
	@Override
	public void delete(Specification<T> spec) {
		baseDao.delete(spec);
	}

	@Override
	public void deleteLogic(T entity) {
		baseDao.deleteLogic(entity);
	}

	@Override
	public void deleteLogic(PK id) {
		baseDao.deleteLogic(id);
	}

	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public void modify(T source, T target) throws IllegalArgumentException, IllegalAccessException {
		baseDao.modify(source, target);
	}

	@Override
	public void modify(PK id, T entity) throws DataNotFoundException, IllegalArgumentException, IllegalAccessException {
		baseDao.modify(id, entity);
	}


	@Override
	public PageBean<T> findPageOfAll(int pageNo, int pageSize) {
		return baseDao.findPageOfAll(pageNo, pageSize);
	}
	
	
	@Override
	public PageBean<T> findPageByExample(T example, Integer pageNum, Integer pageSize) {
		return baseDao.findPageByExample(example, pageNum, pageSize);
	}

	@Override
	public PageBean<T> findPageByProperties(String[] properties, Object[] values, Integer pageNo, Integer pageSize) {
		return baseDao.findPageByProperties(properties, values, pageNo, pageSize);
	}
	
	
	
	@Override
	public PageBean<T> findPageByPropertiesNullValueIsAll(String[] propertyNames, Object[] values, Integer pageNum,
			Integer pageSize) {
		return baseDao.findPageByPropertiesNullValueIsAll(propertyNames, values, pageNum, pageSize);
	}

	@Override
	public PageBean<T> findPageByProperty(String property, Object value, Integer pageNo, Integer pageSize) {
		return baseDao.findPageByProperty(property, value, pageNo, pageSize);
	}
	
	@Override
	public DataTableBean<T> findDataTable(QueryParameter parameter) {
		return baseDao.findDataTable(parameter);
	}

	@Override
	public DataTableBean<T> findDataTableByProperties(QueryParameter parameter, String[] propertyNames, Object[] values) {
		return baseDao.findDataTableByProperties(parameter, propertyNames, values);
	}

	@Override
	public DataTableBean<T> findDataTableByProperty(QueryParameter parameter, String property, Object value) {
		return baseDao.findDataTableByProperty(parameter, property, value);
	}
	
	

}
