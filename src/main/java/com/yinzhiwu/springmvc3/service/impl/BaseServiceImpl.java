package com.yinzhiwu.springmvc3.service.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import com.yinzhiwu.springmvc3.dao.IBaseDao;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.page.PageBean;
import com.yinzhiwu.springmvc3.service.IBaseService;

public abstract class BaseServiceImpl<T, PK extends Serializable> implements IBaseService<T, PK> {
	
	protected  Log logger = LogFactory.getLog(getClass());

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
		try {
			return baseDao.get(id);
		} catch (DataNotFoundException e) {
			logger.error(e.getMessage());
			return null;
		}
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
	public List<T> findAll() throws DataNotFoundException {
		return baseDao.findAll();
	}

	@Override
	public List<T> findByProperty(String propertyName, Object value) throws DataNotFoundException {
		return baseDao.findByProperty(propertyName, value);
	}

	@Override
	public int findCountByProperty(String propertyName, Object value) {
		return baseDao.findCountByProperty(propertyName, value);
	}

	@Override
	public List<T> findByProperties(Map<String, Object> propertyMap) throws DataNotFoundException {
		return baseDao.findByProperties(propertyMap);
	}

	@Override
	public List<T> findByProperties(String[] propertyNames, Object[] values) throws DataNotFoundException {
		return baseDao.findByProperties(propertyNames, values);
	}

	@Override
	public List<T> findByExample(T entity) throws DataNotFoundException {
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

	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public void modify(PK id, T entity) throws DataNotFoundException, IllegalArgumentException, IllegalAccessException{
		long start = System.currentTimeMillis();
		Assert.notNull(id);
		if(entity== null)
			return;
		T newEntity = baseDao.get(id);
		
		entity.getClass().getSuperclass();
		Field[] fields = entity.getClass().getDeclaredFields();
		logger.info(fields.length);
		boolean update_flag =false;
		long afterReflect = System.currentTimeMillis();
		logger.info("反射所花时间: " + (afterReflect-start));
		for (Field f : fields) {
			f.setAccessible(true);
			if(!Modifier.isStatic(f.getModifiers()) 
					&&f.get(entity)!=null
					&& f.getDeclaredAnnotation(Id.class) == null
					&& f.getDeclaredAnnotation(OneToMany.class) == null
					&& !f.get(entity).equals(f.get(newEntity)))
			{
				f.set(newEntity, f.get(entity));
				logger.info(f.get(newEntity));
				update_flag = true;
			}
		}
		long afterCompare=System.currentTimeMillis();
		logger.info("对比所化时间: " + (afterCompare-afterReflect));
		if(update_flag){
			logger.info("开始更新");
			baseDao.update(newEntity);
		}
		
		long end = System.currentTimeMillis();
		logger.info("更新所花时间: " + (end-afterCompare));
		logger.info("所花总时间： " + (end-start));
	}
	
	@Override
	public PageBean<T> findPage(T entity, int pageNum, int pageSize){
		return null;
	}

	@Override
	public int findCountByProperties(String[] propertyNames, Object[] values) throws Exception {
		return baseDao.findCountByProperties(propertyNames, values);
	}
	
	
}
