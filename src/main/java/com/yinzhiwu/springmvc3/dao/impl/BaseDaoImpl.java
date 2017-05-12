package com.yinzhiwu.springmvc3.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.yinzhiwu.springmvc3.dao.IBaseDao;
import com.yinzhiwu.springmvc3.entity.BaseEntity;




public class BaseDaoImpl<T,PK extends Serializable> 
				extends HibernateDaoSupport 
				implements IBaseDao<T, PK> {

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
        return getHibernateTemplate().getSessionFactory().getCurrentSession();  
    }  
  
    public T get(PK id) {  
        Assert.notNull(id, "id is required");  
        return (T) getHibernateTemplate().get(entityClass, id);  
    }  
  
	@SuppressWarnings("unchecked")
	public PK save(T entity)  {  
        Assert.notNull(entity, "entity is required");  
        return (PK) getHibernateTemplate().save(entity);  
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByProperty(String propertyName, Object value) {
		String hql = "from " + entityClass.getSimpleName() + " where " + propertyName + " =:value";
		return (List<T>) getHibernateTemplate().findByNamedParam(hql, "value", value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findCountByProperty(String propertyName, Object value) {
		String hql = "select count(*) from " + entityClass.getSimpleName() + " where " + propertyName + " =:value";
		List<Long> l = (List<Long>) getHibernateTemplate().findByNamedParam(hql, "value", value);
		return l.get(0).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByProperties(Map<String, Object> map) {
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

  
}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		String hql = "from "+  entityClass.getSimpleName();
		return (List<T>) getHibernateTemplate().find(hql);
	}

	@Override
	public void saveOrUpdate(T entity) {
		Assert.notNull(entity, "entity is required");
		 getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public void delete(T entity) {
		Assert.notNull(entity, "entity is required");
		getHibernateTemplate().delete(entity);
		
	}

	@Override
	public void delete(PK id) {
		Assert.notNull(id, "id is required");
		T entity = get(id);
		if (entity !=null){
			delete(entity);
		}
	}



	@Override
	public List<T> findByExample(T entity) {
		Assert.notNull(entity, "entity is required");
		return getHibernateTemplate().findByExample(entity);
	}  
	
	@Override
	public void update(T entity){
		Assert.notNull(entity, "entity is required");
		if(entity instanceof BaseEntity){
			BaseEntity baseEntity = (BaseEntity) entity;
			baseEntity.setLastModifiedDate(new Date());
			getHibernateTemplate().update(baseEntity);
			return;
		}
		getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findCount() {
		String hql = "select count(*) from " + entityClass.getSimpleName();
		List<Long> sums =   (List<Long>) getHibernateTemplate().find(hql);
		return sums.get(0).intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByProperties(String[] propertyNames, Object[] values) {
		StringBuilder hql = new StringBuilder("from " + entityClass.getSimpleName() + " where 1=1");
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

	}

	@Override
	public int findCountByProperties(String[] propertyNames, Object[] values){
		if(propertyNames.length != values.length)
			try {
				throw new Exception("传入的属性名和属性值数量不一致");
			} catch (Exception e) {
				e.printStackTrace();
			}
		return findByProperties(propertyNames, values).size();
	}
}

