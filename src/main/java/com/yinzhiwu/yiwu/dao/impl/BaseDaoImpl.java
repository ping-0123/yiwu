package com.yinzhiwu.yiwu.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.yinzhiwu.yiwu.enums.DataStatus;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.util.ReflectUtils;

/**
 * 
 * @author ping
 * @Version 2017年7月5日下午4:07:15
 *
 * @param <T>
 *            the entity type
 * @param <PK>
 *            the entity's primary key type
 */
public abstract class BaseDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements IBaseDao<T, PK> {

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

	@Resource(name="sessionFactory")
	public void setHibernateSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		super.setSessionFactory(sessionFactory);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T get(PK id) {
		Assert.notNull(id, "id is required");
		
		return getSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public PK save(T entity) {
		Assert.notNull(entity, "entity is required");
		
		if (entity instanceof BaseEntity)
			((BaseEntity) entity).init();
		if (entity instanceof BaseYzwEntity)
			((BaseYzwEntity) entity).init();
		
		return (PK) getSession().save(entity);
	}

	protected List<T> findByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName, "属性名不能为空");
		
		String hql = "FROM " + entityClass.getSimpleName() + " t1 WHERE  t1." + propertyName + " =:property";
		return getSession().createQuery(hql, entityClass)
				.setParameter("property", value)
				.getResultList();
		
	}
	
	protected T findOneByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName, "属性名不能为空");
		
		String hql = "FROM " + entityClass.getSimpleName() + " t1 WHERE  t1." + propertyName + " =:property";
		List<T> list = getSession().createQuery(hql, entityClass)
				.setParameter("property", value)
				.setMaxResults(1)
				.getResultList();
		
		return list.size()>0?list.get(0):null;
	}

	protected Long findCountByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName, "属性名不能为空");
		
		String hql = "SELECT COUNT(1) FROM " + entityClass.getSimpleName() + " WHERE " + propertyName + " =:property";
		return getSession().createQuery(hql, Long.class)
				.setParameter("property", value)
				.getSingleResult();
	}


	@Override
	public List<T> findAll(){
		String hql = "FROM " + entityClass.getSimpleName();
		return getSession().createQuery(hql, entityClass) 
				.getResultList();
		
	}

	@Override
	public PageBean<T> findPageOfAll(int pageNo, int pageSize) {
			
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(entityClass);
		Root<T> root = criteria.from(entityClass);
		criteria.select(root);
		int totalSize = findCount().intValue();
		return findPageByCriteria(criteria, pageNo, pageSize, totalSize);
	}

	@Override
	public void saveOrUpdate(T entity) {
		Assert.notNull(entity, "entity is required");
			
		getSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(T entity) {
		Assert.notNull(entity, "entity is required");
		
		getSession().delete(entity);

	}

	@Override
	public void delete(PK id) {
		Assert.notNull(id, "id is required");
			
		T entity = get(id);
		delete(entity);
	}

	
	
	@Override
	public void deleteLogic(T entity) {
		Assert.notNull(entity, "id is required");
		if(entity instanceof BaseEntity){
			((BaseEntity) entity).setDataStatus(DataStatus.DELETE);
			update(entity);
		}else if (entity instanceof BaseYzwEntity) {
			((BaseYzwEntity) entity).setDataStatus(DataStatus.DELETE);
			update(entity);
		}
	}

	@Override
	public void deleteLogic(PK id) {
		Assert.notNull(id, "id is required");
		
		T entity = get(id);
		deleteLogic(entity);
	}

	@Override
	public List<T> findByExample(T entity){
		Assert.notNull(entity, "entity is required");
		
		return getHibernateTemplate().findByExample(entity);
	}

	@Override
	public void update(T entity) {
		Assert.notNull(entity, "entity is required");
		try {
			if (entity instanceof BaseEntity)
				((BaseEntity) entity).beforeUpdate();
			;
			if (entity instanceof BaseYzwEntity)
				((BaseYzwEntity) entity).beforeUpdate();
			getHibernateTemplate().update(entity);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public Long findCount() {
		String hql = "SELECT COUNT(*) FROM " + entityClass.getSimpleName();
		return getSession().createQuery(hql, Long.class)
				.getSingleResult();
		
	}

	protected List<T> findByProperties(String[] propertyNames, Object[] values) {
		if(propertyNames.length != values.length){
			throw new IllegalArgumentException("传入的属性名和属性值数量不一致");
		}
		String[] properties = new String[propertyNames.length];
		
		StringBuilder builder = new StringBuilder();
		builder.append("FROM " + entityClass.getSimpleName());
		builder.append(" WHERE 1=1");
		for(int i = 0; i<propertyNames.length; i++){
			if(StringUtils.hasLength(propertyNames[i])){
				properties[i] = propertyNames[i].replace(".", "");
				builder.append(" AND " + propertyNames[i] + "=:" +properties[i]);
			}else {
				throw new IllegalArgumentException("属性名不能为空为null");
			}
		}
		
		 Query<T> query = getSession().createQuery(builder.toString(), entityClass);
		 for(int j=0; j<properties.length;j++){
			 query.setParameter(properties[j], values[j]);
		 }
		 
		 List<T> list = query.getResultList();
//		List<T> list =   (List<T>) getHibernateTemplate().findByNamedParam(
//				builder.toString(), properties, values);
		if(list == null) return new ArrayList<>();
		return list;
	}

	protected Long findCountByProperties(String[] propertyNames, Object[] values) {
		if (propertyNames.length != values.length) {
			throw new IllegalArgumentException("传入的属性名和属性值数量不一致");
		}
		String[] properties = new String[propertyNames.length];
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(1)");
		builder.append(" FROM " + entityClass.getSimpleName());
		builder.append(" WHERE 1=1");
		for (int i = 0; i < propertyNames.length; i++) {
			if (StringUtils.hasLength(propertyNames[i])) {
				properties[i] = propertyNames[i].replace(".", "");
				builder.append(" AND " + propertyNames[i] + "=:" + properties[i]);
			} else {
				throw new IllegalArgumentException("属性名不能为空为null");
			}
		}
		
		 Query<Long> query = getSession().createQuery(builder.toString(), Long.class);
		 for(int j=0; j<properties.length;j++){
			 query.setParameter(properties[j], values[j]);
		 }
		 
		 return query.getSingleResult();
	}

	protected PageBean<T> findPageByProperties(String[] propertyNames, Object[] values, int pageNo, int pageSize) {
		if (propertyNames.length != values.length) {
			throw new IllegalArgumentException("传入的属性名和属性值数量不一致");
		}
		if (Arrays.asList(propertyNames).contains(null))
			throw new IllegalArgumentException("属性名不可能为null");

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(entityClass);
		Root<T> root = criteria.from(entityClass);
		criteria.select(root);
		Predicate predicate = null;
		for (int i = 0; i < propertyNames.length; i++) {
			Predicate condition = builder.equal(_getPath(root, propertyNames[i]), values[i]);
			predicate = (predicate == null) ? condition : builder.and(predicate, condition);
		}
		criteria.where(predicate);

		// 查询数量
		// CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		// countCriteria.from(entityClass);
		// countCriteria.select(builder.count(root));
		// countCriteria.where(predicate);
		// Long totalSize =
		// getSession().createQuery(countCriteria).getSingleResult();
		int totalSize = findCountByProperties(propertyNames, values).intValue();

		return findPageByCriteria(criteria, pageNo, pageSize, totalSize);
	}

	protected PageBean<T> findPageByProperty(String propertyName, Object value, int pageNo, int pageSize) {
		if (!StringUtils.hasLength(propertyName))
			throw new IllegalArgumentException("propertyName 不能为空");
		String[] properties = new String[] { propertyName };
		Object[] values = new Object[] { value };
		return findPageByProperties(properties, values, pageNo, pageSize);
	}

	private <X> Path<?> _getPath(Path<X> path, String propertyName) {
		Path<?> result;
		if (propertyName.contains(".")) {
			String[] properties = propertyName.split("\\.");
			result = path.get(properties[0]);
			for (int i = 1; i < properties.length; i++) {
				result = result.get(properties[i]);
			}
		} else {
			result = path.get(propertyName);
		}
		return result;
	}

	protected <R> PageBean<R> findPageByCriteria(CriteriaQuery<R> criteria, int pageNo, int pageSize, int totalSize) {
		if(totalSize <=0)
			return null;
		if (pageNo <= 0)
			pageNo = 1;
		if (pageSize <= 0)
			pageSize = PageBean.DEFAULT_PAGE_SIZE;
		Query<R> query = getSession().createQuery(criteria);
		query.setFirstResult(((pageNo - 1) * pageSize));
		query.setMaxResults(pageSize);
		List<R> list = query.getResultList();

		return new PageBean<>(pageSize, pageNo, totalSize, list);
	}

	protected <R> PageBean<R> findPageByHqlWithParams(
			String hql,  String[] namedParams, Object[] values, int pageNo, int pageSize) {
		Assert.hasLength(hql, "hql is not correct.");

		if (pageNo <= 0) pageNo = 1;
		if (pageSize <= 0) pageSize = PageBean.DEFAULT_PAGE_SIZE;
		int totalRecords = findCountByHqlWithParameters(_generateFindCountHql(hql), namedParams, values);
		if (totalRecords == 0)
			return new PageBean<>(pageSize, pageNo, totalRecords, new ArrayList<>());

		Query<?> query = getSession().createQuery(hql);
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		if (namedParams != null && namedParams.length > 0) {
			if (namedParams.length != values.length)
				throw new IllegalArgumentException("the size of namedparams should equal to the size of values");
			for (int i = 0; i < namedParams.length; i++) {
				query.setParameter(namedParams[i], values[i]);
			}
		}
		@SuppressWarnings("unchecked")
		List<R> list =  (List<R>) query.getResultList();
		return new PageBean<>(pageSize, pageNo, totalRecords, list);
	}
	

	private int findCountByHqlWithParameters(String hql, String[] namedParams, Object[] values) {
		Query<Long> query = getSession().createQuery(hql, Long.class);
		if (namedParams != null && namedParams.length > 0) {
			if (namedParams.length != values.length)
				throw new IllegalArgumentException("the size of namedparams should equal to the size of values");
			for (int i = 0; i < namedParams.length; i++) {
				query.setParameter(namedParams[i], values[i]);
			}
		}
		return query.getSingleResult().intValue();
	}


	private String _generateFindCountHql(String hql) {
		int i = hql.toUpperCase().indexOf("FROM");
		return "SELECT COUNT(1) " + hql.substring(i);
	}


	@Override
	public void modify(T source, T target) throws IllegalArgumentException, IllegalAccessException {
		Assert.notNull(source);
		Assert.notNull(target);

		source = modifySourceEntityProperties(source, target);

		update(source);
	}

	private <E> E modifySourceEntityProperties(E source, E target) throws IllegalAccessException {
		Field[] fields = ReflectUtils.getAllFields(source.getClass());
		for (Field f : fields) {
			f.setAccessible(true);
			if (// 静态属性不变
			!Modifier.isStatic(f.getModifiers())
					// target属性为null ,source 对应的属性不变
					&& f.get(target) != null
					// Id 主键不改变
					&& f.getDeclaredAnnotation(Id.class) == null
					// 属性值相同无须改变
					&& !f.get(target).equals(f.get(source))
					// 排除OneToMany 映射
					&& f.getDeclaredAnnotation(OneToMany.class) == null)
				if (f.getDeclaredAnnotation(Embedded.class) != null) {
					f.set(source, modifySourceEntityProperties(f.get(source), f.get(target)));
				} else
					f.set(source, f.get(target));
		}
		return source;
	}

	@Override
	public void modify(PK id, T target) throws DataNotFoundException, IllegalArgumentException, IllegalAccessException {
		Assert.notNull(id);
		Assert.notNull(target);

		T source = get(id);
		if(source == null )
			throw new DataNotFoundException(entityClass, "id", id);
		modify(source, target);
	}
	
	
	protected <R> PageBean<R> findPage(String hql, Class<R> resultClass,  String[] namedParameters, Object[] values, Integer pageNum, Integer pageSize ){
		Assert.hasLength(hql);
		if(namedParameters.length != values.length){
			IllegalArgumentException exception = new IllegalArgumentException("传入的属性名和属性值数量不一致");
			logger.error(exception.getMessage(), exception);
			throw exception;
			}
		if(Arrays.asList(namedParameters).contains(null)) {
			IllegalArgumentException exception=  new IllegalArgumentException("hql的命名参数不能为null");
			logger.error(exception.getMessage(), exception);
			throw exception;
		}
		
		int totalRecords = findCount(_generateFindCountHql(hql), namedParameters, values).intValue();
		if(totalRecords == 0)
			return new PageBean<>(pageSize, pageNum, totalRecords, new ArrayList<>());
		if(pageNum == null || pageNum <=0) pageNum =PageBean.DEFAULT_PAGE_NO;
		if(pageSize == null || pageSize<=0) pageSize = PageBean.DEFAULT_PAGE_SIZE;
		int offset = (pageNum-1) * pageSize ;
		Query<R> query = getSession().createQuery(hql, resultClass)
				.setFirstResult(offset)
				.setMaxResults(pageSize);
		for(int i=0; i<namedParameters.length; i++){
			query.setParameter(namedParameters[i], values[i]);
		}
		List<R> list = query.getResultList();
		
		return new PageBean<>(pageSize, pageNum, totalRecords,list);
	}
	
	protected <R> PageBean<R> findPage(String hql, Class<R> resultClass, String namedParameter, Object value, Integer pageNum, Integer PageSize){
		if(namedParameter == null || "".equals(namedParameter.trim()))
			throw new IllegalArgumentException("hql的命名参数不能为null");
		String[] namedParameters = {namedParameter};
		Object[] values = {value};
		return findPage(hql, resultClass, namedParameters, values, pageNum, PageSize);
	}
		
	/**
	 * 
	 * @param hql 必须是"SELECT COUNT(1) FROM"开头
	 * @param namedParameters
	 * @param values
	 * @return
	 */
	protected Long findCount(String hql, String[] namedParameters, Object[] values) {
		Query<Long> query = getSession().createQuery(hql, Long.class);
		for(int i=0; i<namedParameters.length; i++){
			query.setParameter(namedParameters[i], values[i]);
		}
		return query.getSingleResult();
	}
	
	/**
	 * 
	 * @param hql 必须是"SELECT COUNT(1) FROM"开头
	 * @param namedParameter
	 * @param value
	 * @return
	 */
	protected Long findCount(String hql, String namedParameter, Object value) {
		return getSession().createQuery(hql, Long.class)
				.setParameter(namedParameter, value)
				.getSingleResult();
	}
}
