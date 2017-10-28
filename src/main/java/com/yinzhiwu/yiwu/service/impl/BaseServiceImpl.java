package com.yinzhiwu.yiwu.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yinzhiwu.yiwu.dao.IBaseDao;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.service.IBaseService;

public abstract class BaseServiceImpl<T, PK extends Serializable> implements IBaseService<T, PK> {

	protected Log logger = LogFactory.getLog(getClass());

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
	public List<T> findAll()  {
		return baseDao.findAll();
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
	public void delete(PK id){
		baseDao.delete(id);
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
	public DataTableBean<T> findDataTable(QueryParameter parameter) {
		return baseDao.findDataTable(parameter);
	}

}
