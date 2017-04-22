package com.yinzhiwu.springmvc3.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.yinzhiwu.springmvc3.dao.IBaseDao;




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
        return sessionFactory.getCurrentSession();  
    }  
  
    public T get(PK id) {  
        Assert.notNull(id, "id is required");  
        return (T) getSession().get(entityClass, id);  
    }  
  
    @SuppressWarnings("unchecked")
	public PK save(T entity) {  
        Assert.notNull(entity, "entity is required");  
        return (PK) getSession().save(entity);  
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

	@SuppressWarnings("unchecked")
	@Override
	public PK saveOrUpdate(T entity) {
		return (PK) getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
		
	}

	@Override
	public void delete(PK id) {
		T entity = get(id);
		if (entity !=null){
			delete(entity);
		}
	}  
}

