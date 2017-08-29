package com.yinzhiwu.yiwu.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yinzhiwu.yiwu.dao.IBaseDao;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.service.IBaseService;

public abstract class BaseServiceImpl<T, PK extends Serializable> implements IBaseService<T, PK> {

	protected Log logger = LogFactory.getLog(getClass());

	private IBaseDao<T, PK> baseDao;

	public final IBaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	protected final void setBaseDao(IBaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}

	public final IBaseDao<T, PK> getDao() {
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
	public List<T> findAll()  {
		return baseDao.findAll();
	}

	@Override
	public List<T> findByProperty(String propertyName, Object value) {
		return baseDao.findByProperty(propertyName, value);
	}

	@Override
	public Long findCountByProperty(String propertyName, Object value) {
		return baseDao.findCountByProperty(propertyName, value);
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
	public void delete(PK id){
		baseDao.delete(id);
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
	public void modify(T source, T target) throws IllegalArgumentException, IllegalAccessException {
		baseDao.modify(source, target);
	}

	@Override
	public void modify(PK id, T entity) throws DataNotFoundException, IllegalArgumentException, IllegalAccessException {
		baseDao.modify(id, entity);
	}

	// @Override
	// public void modify(PK id, T entity) throws DataNotFoundException,
	// IllegalArgumentException, IllegalAccessException{
	// long start = System.currentTimeMillis();
	// Assert.notNull(id);
	// if(entity== null)
	// return;
	// T newEntity = baseDao.get(id);
	//
	// Field[] fields = entity.getClass().getDeclaredFields();
	// logger.info(fields.length);
	// boolean update_flag =false;
	// long afterReflect = System.currentTimeMillis();
	// logger.debug("反射所花的时间: " + (afterReflect-start));
	// for (Field f : fields) {
	// f.setAccessible(true);
	// if(!Modifier.isStatic(f.getModifiers())
	// &&f.get(entity)!=null
	// && f.getDeclaredAnnotation(Id.class) == null
	// //排除OneToMany 映射
	// && f.getDeclaredAnnotation(OneToMany.class) == null
	// )
	// {
	// f.set(newEntity, f.get(entity));
	// logger.debug(f.get(newEntity));
	// update_flag = true;
	// }
	// }
	// long afterCompare=System.currentTimeMillis();
	// logger.debug("对比所化时间: " + (afterCompare-afterReflect));
	// if(update_flag){
	// logger.debug("开始更新");
	// baseDao.update(newEntity);
	// }
	//
	// long end = System.currentTimeMillis();
	// logger.debug("更新所花时间: " + (end-afterCompare));
	// logger.debug("所花总时间： " + (end-start));
	// }

	@Override
	public PageBean<T> findPage(T entity, int pageNum, int pageSize) {
		return null;
	}

	@Override
	public Long findCountByProperties(String[] propertyNames, Object[] values) throws Exception {
		return baseDao.findCountByProperties(propertyNames, values);
	}

}
