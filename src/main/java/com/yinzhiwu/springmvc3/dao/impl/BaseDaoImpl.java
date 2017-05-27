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
  
    public T get(PK id) {  
        Assert.notNull(id, "id is required"); 
        try {
        	return (T) getHibernateTemplate().get(entityClass, id);  
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
    } 
  
	@SuppressWarnings("unchecked")
	public PK save(T entity)  {  
        Assert.notNull(entity, "entity is required");  
        try {
        	return (PK) getHibernateTemplate().save(entity);  
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByProperty(String propertyName, Object value) {
		String hql = "from " + entityClass.getSimpleName() + " where " + propertyName + " =:value";
		try {
			return (List<T>) getHibernateTemplate().findByNamedParam(hql, "value", value);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return new ArrayList<>();
		}
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
	public List<T> findByProperties(Map<String, Object> map) {
		try{
			Map<String, Object> v_map = new HashMap<>();
			StringBuilder hql = new StringBuilder("from " + entityClass.getSimpleName() + " where 1=1");
			for (String string : map.keySet()) {
				if(null != string && !"".equals(string)){
					String valString = string.replace(".", "");
					hql.append(" and " + string + "=:" + valString);
					v_map.put(valString, map.get(string));
				}
			}
			
			return (List<T>) getHibernateTemplate().findByNamedParam(
					hql.toString(), 
					v_map.keySet().toArray(new String[] {}),
					v_map.values().toArray(new Object[] {}));
		}catch (Exception e) {
			LOG.error(e.getMessage());
			return new ArrayList<>();
		}

  
}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		String hql = "from "+  entityClass.getSimpleName();
		try{
			return (List<T>) getHibernateTemplate().find(hql);
		}catch (Exception e) {
			LOG.error(e.getMessage());
			return new ArrayList<>();
		}
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
	public List<T> findByExample(T entity) {
		Assert.notNull(entity, "entity is required");
		try{
			return getHibernateTemplate().findByExample(entity);
		}catch (Exception e) {
			LOG.error(e.getMessage());
			return new ArrayList<>();
		}
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
	public List<T> findByProperties(String[] propertyNames, Object[] values) {
		StringBuilder hql = new StringBuilder("from " + entityClass.getSimpleName() + " where 1=1");
		try{
			Map<String,Object> map = new HashMap<>();
			for(int i = 0; i<propertyNames.length; i++){
				if(StringUtils.hasLength(propertyNames[i])){
					String valString = propertyNames[i].replace(".", "");
					hql.append(" and " + propertyNames[i] + "=:" + valString);
					map.put(valString, values[i]);
				}
			}
			
			return  (List<T>) getHibernateTemplate().findByNamedParam(
					hql.toString(), 
					map.keySet().toArray(new String[] {}),
					map.values().toArray(new Object[] {}));
		}catch (Exception e) {
			LOG.error(e.getMessage());
			return new ArrayList<>();
		}

	}

	@Override
	public int findCountByProperties(String[] propertyNames, Object[] values){
		if(propertyNames.length != values.length)
			try {
				throw new Exception("传入的属性名和属性值数量不一致");
			} catch (Exception e) {
				e.printStackTrace();
			}
		try{
			return findByProperties(propertyNames, values).size();
		}catch (Exception e) {
			LOG.error(e.getMessage());
			return 0;
		}
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
		@SuppressWarnings("unchecked")
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
}

