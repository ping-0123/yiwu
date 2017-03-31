package com.yinzhiwu.springmvc3.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.yinzhiwu.springmvc3.dao.IBaseDao;




//@Repository
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
  
}  


