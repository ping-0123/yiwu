package com.yinzhiwu.springmvc3.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.yinzhiwu.springmvc3.dao.IBaseDao;
import com.yinzhiwu.springmvc3.entity.BaseEntity;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.page.PageBean;




public abstract class BaseDaoImpl<T,PK extends Serializable> 
				extends HibernateDaoSupport 
				implements IBaseDao<T, PK> {
	private static Log LOG = LogFactory.getLog(BaseTypeDaoImpl.class);

	private Class<T> entityClass;  
    protected SessionFactory sessionFactory;  
      
    @SuppressWarnings("unchecked")
	public BaseDaoImpl() {  
        this.entityClass = null;  
        Class<?> c = getClass();  
        Type type = c.getGenericSuperclass();  
        if (type instanceof ParameterizedType) {  
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();  
            this.entityClass = (Class<T>) parameterizedType[0];  
        }  
    }  
      
    @Resource  
    public void setHibernateSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory; 
        super.setSessionFactory(sessionFactory);
    }  
  
    protected Session getSession() {  
    	try {
    		 return getHibernateTemplate().getSessionFactory().getCurrentSession();  
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
       
    }  
  
    public T get(PK id) throws DataNotFoundException {  
        Assert.notNull(id, "id is required"); 
    	T t = (T) getHibernateTemplate().get(entityClass, id);  
    	if(t==null)
    		throw  new DataNotFoundException(entityClass,"id", id);
    	return t;
    } 
  
	@SuppressWarnings("unchecked")
	public PK save(T entity)  {  
        Assert.notNull(entity, "entity is required");  
        try {
        	if(entity instanceof BaseEntity)
        		((BaseEntity) entity).init();
        	return (PK) getHibernateTemplate().save(entity);  
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByProperty(String propertyName, Object value) throws DataNotFoundException {
		String hql = "from " + entityClass.getSimpleName() + " where " + propertyName + " =:value";
		List<T> list =  (List<T>) getHibernateTemplate().findByNamedParam(hql, "value", value);
		if(list==null || list.size() ==0)
			throw new DataNotFoundException(entityClass, propertyName, value);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findCountByProperty(String propertyName, Object value) {
		String hql = "select count(*) from " + entityClass.getSimpleName() + " where " + propertyName + " =:value";
		try {
			List<Long> l = (List<Long>) getHibernateTemplate().findByNamedParam(hql, "value", value);
			return l.get(0).intValue();
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByProperties(Map<String, Object> map) throws DataNotFoundException {
		Map<String, Object> v_map = new HashMap<>();
		StringBuilder hql = new StringBuilder("from " + entityClass.getSimpleName() + " where 1=1");
		for (String string : map.keySet()) {
			if(null != string && !"".equals(string)){
				String valString = string.replace(".", "");
				hql.append(" and " + string + "=:" + valString);
				v_map.put(valString, map.get(string));
			}
		}
		
		List<T>  list = (List<T>) getHibernateTemplate().findByNamedParam(
				hql.toString(), 
				v_map.keySet().toArray(new String[] {}),
				v_map.values().toArray(new Object[] {}));
		if(list==null || list.size() ==0)
			throw new DataNotFoundException();
		return list;
}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() throws DataNotFoundException {
		String hql = "from "+  entityClass.getSimpleName();
		List<T> list =  (List<T>) getHibernateTemplate().find(hql);
		if(list==null || list.size() ==0)
			throw new DataNotFoundException();
		return list;
	}

	@Override
	public void saveOrUpdate(T entity) {
		Assert.notNull(entity, "entity is required");
		try {
			getHibernateTemplate().saveOrUpdate(entity);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	@Override
	public void delete(T entity) {
		Assert.notNull(entity, "entity is required");
		try{
			getHibernateTemplate().delete(entity);
		}catch (Exception e) {
			LOG.error(e.getMessage());
		}
		
	}

	@Override
	public void delete(PK id) {
		Assert.notNull(id, "id is required");
		try{
			T entity = get(id);
			if (entity !=null){
				delete(entity);
			}
		}catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}



	@Override
	public List<T> findByExample(T entity) throws DataNotFoundException {
		Assert.notNull(entity, "entity is required");
		List<T> list =   getHibernateTemplate().findByExample(entity);
		if(list==null || list.size() ==0)
			throw new DataNotFoundException();
		return list;
	}  
	
	@Override
	public void update(T entity){
		Assert.notNull(entity, "entity is required");
		try{
			if(entity instanceof BaseEntity){
				BaseEntity baseEntity = (BaseEntity) entity;
				baseEntity.setLastModifiedDate(new Date());
				getHibernateTemplate().update(baseEntity);
				return;
			}
			getHibernateTemplate().update(entity);
		}catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findCount() {
		String hql = "select count(*) from " + entityClass.getSimpleName();
		try{
			List<Long> sums =   (List<Long>) getHibernateTemplate().find(hql);
			return sums.get(0).intValue();
		}catch (Exception e) {
			LOG.error(e.getMessage());
			return 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByProperties(String[] propertyNames, Object[] values) throws DataNotFoundException {
		StringBuilder builder = new StringBuilder("FROM " + entityClass.getSimpleName() + " WHERE 1=1");
		Map<String,Object> map = new HashMap<>();
		for(int i = 0; i<propertyNames.length; i++){
			if(StringUtils.hasLength(propertyNames[i])){
				String valString = propertyNames[i].replace(".", "");
				builder.append(" and " + propertyNames[i] + "=:" + valString);
				map.put(valString, values[i]);
			}
		}
		
		List<T> list =  (List<T>) getHibernateTemplate().findByNamedParam(
				builder.toString(), 
				map.keySet().toArray(new String[] {}),
				map.values().toArray(new Object[] {}));
		if(list==null || list.size() ==0)
			throw new DataNotFoundException();
		return list;
	}

	@Override
	public int findCountByProperties(String[] propertyNames, Object[] values) throws Exception{
		if(propertyNames.length != values.length){
			throw new Exception("传入的属性名和属性值数量不一致");
		}
		String[] properties = new String[propertyNames.length];
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(*)");
		builder.append(" FROM " + entityClass.getSimpleName());
		builder.append(" WHERE 1=1");
		for(int i = 0; i<propertyNames.length; i++){
			if(StringUtils.hasLength(propertyNames[i])){
				properties[i] = propertyNames[i].replace(".", "");
				builder.append(" AND " + propertyNames[i] + "=:" +properties[i]);
			}else {
				throw new Exception("属性名不能为空为null");
			}
		}
		@SuppressWarnings("unchecked")
		List<Long> count =   (List<Long>) getHibernateTemplate().findByNamedParam(
				builder.toString(), properties, values);
		return count.get(0).intValue();
	}

	

	
	@Override
	public PageBean<T> findPageByHql(String hql, int pageNum, int pageSize){
		Assert.hasLength(hql);
		if(pageNum <=0)
			pageNum =1;
		if(pageSize<=0)
			pageSize = PageBean.DEFAULT_PAGE_SIZE;
		
		int totalRecords =  findCountByHql(_get_count_hql(hql));
		if(totalRecords ==0)
			return new PageBean<>(pageSize, pageNum, totalRecords, new ArrayList<>());
		
		Query<?> query = getSession().createQuery(hql);
		query.setFirstResult((pageNum-1) * pageSize);
		query.setMaxResults(pageSize);
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<T> list =(List<T>) query.list();
		
		return new PageBean<>(pageSize, pageNum, totalRecords,list);
	}
	
		
	@SuppressWarnings("unchecked")
	public int findCountByHql(String hql){
		try{
			List<Long> list=   (List<Long>) getHibernateTemplate().find(hql);
			return list.get(0).intValue();
		}catch (Exception e) {
			LOG.error(e.getMessage());
			return 0;
		}
	}
	
	private String _get_count_hql(String hql){
		int i = hql.toUpperCase().indexOf("FROM");
		return "select count(*) " + hql.substring(i);
	}
	
	@SuppressWarnings("unused")
	private Throwable _get_root_Exception(Throwable e)
	{
		Throwable next = e.getCause();
		if(next == null)
			return e;
		else
			return _get_root_Exception(next);
	}
	
}

