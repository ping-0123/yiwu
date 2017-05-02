package com.yinzhiwu.springmvc3.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yinzhiwu.springmvc3.dao.IBaseDao;
import com.yinzhiwu.springmvc3.service.IBaseService;

public abstract class BaseServiceImpl<T, PK extends Serializable> implements IBaseService<T, PK> {

	private IBaseDao<T, PK> baseDao;
	
	
	
	public final IBaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	public final void setBaseDao(IBaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}
	
	public final  IBaseDao<T, PK> getDao(){
		return baseDao;
	}

	@Override
	public T get(PK id) {
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
	public List<T> findAll() {
		return baseDao.findAll();
	}

	@Override
	public List<T> findByProperty(String propertyName, Object value) {
		return baseDao.findByProperty(propertyName, value);
	}

	@Override
	public int findCountByProperty(String propertyName, Object value) {
		return baseDao.findCountByProperty(propertyName, value);
	}

	@Override
	public List<T> findByProperties(Map<String, Object> propertyMap) {
		return baseDao.findByProperties(propertyMap);
	}

	@Override
	public List<T> findByProperties(String[] propertyNames, Object[] values) {
		return baseDao.findByProperties(propertyNames, values);
	}

	@Override
	public List<T> findByExample(T entity) {
		return baseDao.findByExample(entity);
	}

	@Override
	public void delete(T entity) {
		baseDao.delete(entity);
	}

	@Override
	public void deleteById(PK id) {
		baseDao.delete(id);
	}

}
