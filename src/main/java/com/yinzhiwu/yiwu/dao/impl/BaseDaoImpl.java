package com.yinzhiwu.yiwu.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.yinzhiwu.yiwu.dao.IBaseDao;
import com.yinzhiwu.yiwu.entity.BaseEntity;
import com.yinzhiwu.yiwu.entity.yzw.BaseYzwEntity;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.util.ReflectUtil;



/**
 * 
 * @author ping
 * @Version 2017年7月5日下午4:07:15
 *
 * @param <T> the entity type
 * @param <PK> the entity's primary key type
 */
public abstract class BaseDaoImpl<T,PK extends Serializable> 
		extends HibernateDaoSupport implements IBaseDao<T, PK> {
	
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
			logger.error(e.getMessage());
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
        	if(entity instanceof BaseYzwEntity)
        		((BaseYzwEntity) entity).init();
        	return (PK) getHibernateTemplate().save(entity);  
		} catch (Exception e) {
			logger.error(e.getMessage());
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
			logger.error(e.getMessage());
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
	public PageBean<T> findPageOfAll(int pageNo, int pageSize){
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(entityClass);
		Root<T> root = criteria.from(entityClass);
		criteria.select(root);
		int totalSize = findCount();
		return findPageByCriteria(criteria, pageNo, pageSize, totalSize);
	}
	
	@Override
	public void saveOrUpdate(T entity) {
		Assert.notNull(entity, "entity is required");
		try {
			getHibernateTemplate().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void delete(T entity) {
		Assert.notNull(entity, "entity is required");
		try{
			getHibernateTemplate().delete(entity);
		}catch (Exception e) {
			logger.error(e.getMessage());
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
			logger.error(e.getMessage());
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
			if(entity instanceof BaseEntity)
				((BaseEntity) entity).beforeUpdate();;
			if(entity instanceof BaseYzwEntity)
				((BaseYzwEntity) entity).beforeUpdate();
			getHibernateTemplate().update(entity);
		}catch (Exception e) {
			logger.error(e.getMessage());
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
			logger.error(e.getMessage());
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
	public int findCountByProperties(String[] propertyNames, Object[] values){
		if(propertyNames.length != values.length){
			throw new IllegalArgumentException("传入的属性名和属性值数量不一致");
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
				throw new IllegalArgumentException("属性名不能为空为null");
			}
		}
		@SuppressWarnings("unchecked")
		List<Long> count =   (List<Long>) getHibernateTemplate().findByNamedParam(
				builder.toString(), properties, values);
		return count.get(0).intValue();
	}
	
	@Override
	public PageBean<T> findPageByProperties(String[] propertyNames, Object[] values, int pageNo, int pageSize){
		if(propertyNames.length != values.length){
			throw new IllegalArgumentException("传入的属性名和属性值数量不一致");
		}
		if(Arrays.asList(propertyNames).contains(null)) throw new IllegalArgumentException("属性名不可能为null");
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(entityClass);
		Root<T> root = criteria.from(entityClass);
		criteria.select(root);
		Predicate predicate = null;
		for(int i=0; i< propertyNames.length; i++){
			Predicate condition = builder.equal(_getPath(root, propertyNames[i]), values[i]);
			predicate =(predicate == null)?condition:builder.and(predicate,condition);
		}
		criteria.where(predicate);
		
		//查询数量
//		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
//		countCriteria.from(entityClass);
//		countCriteria.select(builder.count(root));
//		countCriteria.where(predicate);
//		Long totalSize = getSession().createQuery(countCriteria).getSingleResult();
		int totalSize = findCountByProperties(propertyNames, values);
		
		return  findPageByCriteria(criteria, pageNo, pageSize, totalSize);
	}
	
	@Override
	public PageBean<T> findPageByProperty(String propertyName, Object value, int pageNo, int pageSize){
		if(!StringUtils.hasLength(propertyName)) throw new IllegalArgumentException("propertyName 不能为空" );
		String[] properties = new String[]{propertyName};
		Object[] values = new Object[]{value};
		return findPageByProperties(properties, values, pageNo, pageSize);
	}
	
	private <X> Path<?> _getPath(Path<X> path, String propertyName){
		Path<?> result;
		if(propertyName.contains(".")){
			String[] properties = propertyName.split("\\.");
			result = path.get(properties[0]);
			for(int i=1;i<properties.length; i++){
				result=result.get(properties[i]);
			}
		}else {
			result = path.get(propertyName);
		}
		return result;
	}

	@Override
	public <R> PageBean<R> findPageByCriteria(CriteriaQuery<R> criteria, int pageNo, int pageSize, int totalSize){
		if(pageNo<=0) pageNo = 1;
		if(pageSize<=0) pageSize = PageBean.DEFAULT_PAGE_SIZE;
		Query<R> query = getSession().createQuery(criteria);
		query.setFirstResult(((pageNo-1) * pageSize));
		query.setMaxResults(pageSize);
		List<R> list = query.getResultList();
		
		return new PageBean<>(pageSize, pageNo,totalSize,list);
	}

	
	@Override
	public PageBean<T> findPageByHql(String hql, int pageNo, int pageSize){
		Assert.hasLength(hql);
		if(pageNo <=0)
			pageNo =1;
		if(pageSize<=0)
			pageSize = PageBean.DEFAULT_PAGE_SIZE;
		
		int totalRecords =  findCountByHql(_get_count_hql(hql));
		if(totalRecords ==0)
			return new PageBean<>(pageSize, pageNo, totalRecords, new ArrayList<>());
		
		Query<T> query = getSession().createQuery(hql,entityClass);
		query.setFirstResult((pageNo-1) * pageSize);
		query.setMaxResults(pageSize);
		List<T> list =(List<T>) query.getResultList();
		
		return new PageBean<>(pageSize, pageNo, totalRecords,list);
	}
	
	
	public PageBean<T> findPageByHql(String hql, int pageNo, int pageSize, String[] namedParams, Object[] values){
		Assert.hasLength(hql, "hql is not correct.");
		
		if(pageNo<=0) pageNo = 1;
		if(pageSize<=0) pageSize = PageBean.DEFAULT_PAGE_SIZE;
		int totalRecords =  findCountByHql(_get_count_hql(hql), namedParams, values);
		if(totalRecords ==0)
			return new PageBean<>(pageSize, pageNo, totalRecords, new ArrayList<>());
		
		Query<T> query =getSession().createQuery(hql, entityClass );
		query.setFirstResult((pageNo-1) * pageSize);
		query.setMaxResults(pageSize);
		if(namedParams != null && namedParams.length>0){
			if(namedParams.length != values.length)
				throw new IllegalArgumentException("the size of namedparams should equal to the size of values");
			for (int i=0; i<namedParams.length; i++) {
				query.setParameter(namedParams[i], values[i]);
			}
		}
		List<T> list = query.getResultList();
		return new PageBean<>(pageSize, pageNo, totalRecords, list);
	}
		

	private int findCountByHql(String hql, String[] namedParams, Object[] values) {
		Query<Integer>  query = getSession().createQuery(hql, Integer.class);
		if(namedParams != null && namedParams.length>0){
			if(namedParams.length != values.length)
				throw new IllegalArgumentException("the size of namedparams should equal to the size of values");
			for (int i=0; i<namedParams.length; i++) {
				query.setParameter(namedParams[i], values[i]);
			}
		}
		return query.getSingleResult();
	}
	
	
	@SuppressWarnings("unchecked")
	public int findCountByHql(String hql){
		try{
			List<Long> list=   (List<Long>) getHibernateTemplate().find(hql);
			return list.get(0).intValue();
		}catch (Exception e) {
			logger.error(e.getMessage());
			return 0;
		}
	}
	
	private String _get_count_hql(String hql){
		int i = hql.toUpperCase().indexOf("FROM");
		return "SELECT COUNT(*) " + hql.substring(i);
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
	
	@Override
	public void modify(T source, T target) throws IllegalArgumentException, IllegalAccessException{
		Assert.notNull(source);
		Assert.notNull(target);
		
		source = modifySourceEntityProperties(source, target);
		
		update(source);
	}

	private <E> E modifySourceEntityProperties(E source, E target) throws IllegalAccessException {
		Field[] fields = ReflectUtil.getAllFields(source.getClass());
		for (Field f : fields) {
			f.setAccessible(true);
			if(//静态属性不变
					!Modifier.isStatic(f.getModifiers()) 
					// target属性为null ,source 对应的属性不变
					&&f.get(target)!=null
					//Id 主键不改变
					&& f.getDeclaredAnnotation(Id.class) == null
					//属性值相同无须改变
					&& !f.get(target).equals(f.get(source))
					//排除OneToMany 映射
					&& f.getDeclaredAnnotation(OneToMany.class) == null)
				if(f.getDeclaredAnnotation(Embedded.class) != null){
					f.set(source, modifySourceEntityProperties(f.get(source), f.get(target)));
				}else
					f.set(source, f.get(target));
		}
		return source;
	}
	
	@Override
	public void modify(PK id, T target) throws DataNotFoundException, IllegalArgumentException, IllegalAccessException{
		Assert.notNull(id);
		Assert.notNull(target);
		
		T source = get(id);
		modify(source, target);
	}
}

