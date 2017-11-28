package com.yinzhiwu.yiwu.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
import com.yinzhiwu.yiwu.model.datatable.Column;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.Order;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.util.ReflectUtils;
import com.yinzhiwu.yiwu.util.reflect.FieldUtils;

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

	private static final char NESTED        = '.';
	
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

	public T get(PK id) throws DataNotFoundException {
		Assert.notNull(id, "id is required");
		
		T entity =  getSession().get(entityClass, id);
		if(null == entity)
			throw new DataNotFoundException(entityClass, "id", id);
		return entity;
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

	@Override
	public List<T> findByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName, "属性名不能为空");
		
		return createQuery(new String[]{propertyName}, new Object[]{value})
				.getResultList();
		
	}
	
	
	@Override
	public T findOneByProperty(String propertyName, Object value) throws DataNotFoundException {
		Assert.hasText(propertyName, "属性名不能为空");
		
		Query<T> query = createQuery(new String[]{propertyName}, new Object[]{value});
		List<T> list = query.setMaxResults(1).getResultList();
		
		if(list.size()==0){
			throw new  DataNotFoundException(query.getQueryString(), new Object[]{value});
		}
		return list.get(0);
	}
	
	
	@Override
	public T findOneByProperties(String[] propertyNames, Object[] values) throws DataNotFoundException{
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
		
		 Query<T> query = getSession().createQuery(builder.toString(), entityClass)
				 .setMaxResults(1);
		 for(int j=0; j<properties.length;j++){
			 query.setParameter(properties[j], values[j]);
		 }
		 
		 List<T> list = query.getResultList();
		if(list == null || list.size()==0) 
			//TODO 
			throw new DataNotFoundException(builder.toString(),values);
		else {
			return list.get(0);
		}
		
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
		
		if(entity instanceof BaseEntity)
			((BaseEntity) entity).beforeUpdate();
		else if (entity instanceof BaseYzwEntity) {
			((BaseYzwEntity) entity).beforeUpdate();
		}
		
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
			
		T entity;
		try {
			entity = get(id);
		} catch (DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			return;
		}
		delete(entity);
	}

	
	
	@Override
	public void deleteLogic(T entity) {
		Assert.notNull(entity, "id is required");
		//TODO 处理一对多， 多对多， 一对一，多对一关联关系的级联逻辑删除
		
		if(entity instanceof BaseEntity){
			((BaseEntity) entity).setDataStatus(DataStatus.DELETE);
			update(entity);
		}
	}

	@Override
	public void deleteLogic(PK id) {
		Assert.notNull(id, "id is required");
		
		T entity;
		try {
			entity = get(id);
		} catch (DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			return;
		}
		
		deleteLogic(entity);
	}

	@Override
	public List<T> findByExample(T entity){
		Assert.notNull(entity, "entity is required");
		
		List<String> properties = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		
		addPairs(properties, values, entityClass, entity, "");
		String[] pros = new String[properties.size()];
		return findByProperties(properties.toArray(pros), values.toArray());
		
	}
	
	/**
	 * 添加(属性名，值)对， 将对象中的一个成员属性加入到属性值对里
	 * 
	 * @param properties
	 * @param values
	 * @param field
	 * @param entity
	 * @param prefixFiledName prefixFiledName 为空或者已.结束
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private void addPair(List<String> properties, List<Object> values, Field field, Object fieldValue, String prefixFiledName){
		Assert.notNull(properties);
		Assert.notNull(values);
		Assert.notNull(field);
		
		if(fieldValue == null)
			return;
		
		field.setAccessible(true);
		if( Modifier.isStatic( field.getModifiers())
				|| field.getDeclaredAnnotation(javax.persistence.OneToMany.class) !=null
				|| field.getDeclaredAnnotation(ManyToMany.class) != null){
			return;
		}else {
			if(field.getDeclaredAnnotation(ManyToOne.class) != null
					|| field.getDeclaredAnnotation(OneToOne.class) !=null
					|| field.getDeclaredAnnotation(Embedded.class) != null)
				addPairs(properties, values,fieldValue.getClass(), fieldValue, field.getName()+ NESTED);
			else {
				properties.add(prefixFiledName  + field.getName());
				values.add(fieldValue);
			}
		}
		
	}

	/**
	 * 添加(属性名，值)对， 将对象中所有的成员属性加入到属性值对中
	 * 
	 * @param properties
	 * @param values
	 * @param clazz
	 * @param value
	 * @param prefixFiledName prefixFiledName 为空或者已.结束
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private void addPairs(List<String> properties, List<Object> values,Class<?> clazz, Object value, String prefixFiledName){
		Assert.notNull(properties);
		Assert.notNull(values);
		Assert.notNull(clazz);
		
		if(value==null)
			return;
		
		Field[] fields = ReflectUtils.getAllFields(clazz);
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				Object fieldValue = field.get(value);
				addPair(properties, values, field, fieldValue, prefixFiledName);
				if(field.getDeclaredAnnotation(Id.class) != null && fieldValue !=null )
					break;
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error(e);
				throw new RuntimeException(e);
			}
		}
		
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
		String hql = "SELECT COUNT(1) FROM " + entityClass.getSimpleName();
		return getSession().createQuery(hql, Long.class)
				.getSingleResult();
		
	}

	@Override
	public List<T> findByProperties(String[] propertyNames, Object[] values) {
		if(propertyNames.length != values.length){
			throw new IllegalArgumentException("传入的属性名和属性值数量不一致");
		}
		Query<T> query = createQuery(propertyNames, values);
		 
		 return query.getResultList();
	
	}
	
	private Query<T> createQuery(String[] propertyNames, Object[] values) {
		Map<String, Object> map = new HashMap<>();
		
		StringBuilder builder = new StringBuilder();
		builder.append("FROM " + entityClass.getSimpleName());
		builder.append(" WHERE 1=1");
		for(int i = 0; i<propertyNames.length; i++){
			if(StringUtils.hasLength(propertyNames[i])){
				if(null == values[i])
//				properties.add( propertyNames[i].replace(".", ""));
					builder.append(" AND " + propertyNames[i] + " is null");
				else{
					String property = propertyNames[i].replace(".", "");
					map.put(property, values[i]);
					builder.append(" AND " + propertyNames[i] + " =:" + property);
				}
			}else {
				throw new IllegalArgumentException("属性名不能为空为null");
			}
		}
		
		 Query<T> query = getSession().createQuery(builder.toString(), entityClass);
		 if(map.size()>0){
			 Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
			 while(iterator.hasNext()){
				 Entry<String, Object> entry = iterator.next();
				 query.setParameter(entry.getKey(), entry.getValue());
			 }
		 }
		return query;
	}

	
	
	@Override
	public List<T> findByPropertiesNullValueIgnore(String[] properties, Object[] values) {
		Map<String,Object> map = filterNullValueProperties(properties, values);
		String[] filterdProperties = new String[map.size()];
		return findByProperties(map.keySet().toArray(filterdProperties),map.values().toArray());
	}

	@Override
	public  Long findCountByProperties(String[] propertyNames, Object[] values) {
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
	
	
	
	@Override
	public Long findCountByPropertiesNullValueIsAll(String[] properties, Object[] values) {
		Map<String, Object> map = filterNullValueProperties(properties, values);
		String[] filteredProperties = new String[map.size()];
		return findCountByProperties(map.keySet().toArray(filteredProperties),map.values().toArray());
	}
	
	private Map<String, Object>  filterNullValueProperties(String[] properties, Object[] values){
		Map<String, Object> map = new HashMap<>();
		for(int i=0; i< properties.length; i ++ ){
			if(StringUtils.hasLength(properties[i]) && null != values[i]){
				map.put(properties[i],values[i]);
			}
		}
		
		return map;
	}

	@Override
	public Long findCountByPropertyNullValueIsAll(String property, Object value) {
		return findCount();
	}

	@Override
	public Long findCountByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName, "属性名不能为空");
		
		String hql = "SELECT COUNT(1) FROM " + entityClass.getSimpleName() + " WHERE " + propertyName + " =:property";
		return getSession().createQuery(hql, Long.class)
				.setParameter("property", value)
				.getSingleResult();
	}

	
	protected PageBean<T> findPageByPropertiesThroughCriteria(String[] propertyNames, Object[] values, int pageNo, int pageSize) {
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
		
		int totalSize = findCountByProperties(propertyNames, values).intValue();

		return findPageByCriteria(criteria, pageNo, pageSize, totalSize);
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

		source = ReflectUtils.modifySourceEntityPropertiesToTarget(source, target);

		update(source);
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
	 * @param propertyNames 形参T 的属性，支持嵌套
	 * @param values
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageBean<T> findPageByProperties(String[] propertyNames, Object[] values, Integer pageNum, Integer pageSize){
		if(propertyNames == null || values == null)
			throw new IllegalArgumentException("propertyNames and values 不能为 null");
		if(propertyNames.length != values.length)
			throw new IllegalArgumentException("传入的属性名和属性值数量不一致");

		String[] properties = new String[propertyNames.length];
		StringBuilder hql = new StringBuilder();
		hql.append("FROM " + entityClass.getSimpleName());
		hql.append(" WHERE 1=1");
		
		for(int i = 0; i<propertyNames.length; i++){
			if(StringUtils.hasLength(propertyNames[i])){
				properties[i] = propertyNames[i].replace(".", "");
				hql.append(" AND " + propertyNames[i] + "=:" +properties[i]);
			}else {
				throw new IllegalArgumentException("属性名不能为空为null");
			}
		}
		
		return findPage(hql.toString(), entityClass, properties, values, pageNum, pageSize);
	}
	
	@Override
	public PageBean<T> findPageByPropertiesNullValueIsAll(String[] propertyNames, Object[] values, Integer pageNum, Integer pageSize)
	{
		Map<String, Object> map = new HashMap<>();
		for(int i=0; i< propertyNames.length; i++){
			if(null != values[i]){
				map.put(propertyNames[i], values[i]);
			}
		}
		
		String[] properties = new String[map.size()];
		return findPageByProperties(map.keySet().toArray(properties), map.values().toArray(), pageNum, pageSize);
	}
	
	public PageBean<T> findPageByProperty(String propertyName, Object value, Integer pageNo, Integer pageSize) {
		Assert.hasText(propertyName);
		
		String[] properties = new String[] { propertyName };
		Object[] values = new Object[] { value };
		return findPageByProperties(properties, values, pageNo, pageSize);
	}
	
	
	@Override
	public PageBean<T> findPageByExample(T example, Integer pageNo, Integer pageSize){
		Assert.notNull(example);
		
		List<String> properties = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		addPairs(properties, values, entityClass, example, "");
		String[] propertyNames = new String[properties.size()];
		
		return findPageByProperties(properties.toArray(propertyNames), values.toArray(), pageNo, pageSize);
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
	
	@Override
	public DataTableBean<T> findDataTable(QueryParameter parameter){
		//TODO 不支持正则表达式搜索
		//TODO 只能实现String列 Integer列搜索
		List<String> propertyNames = new ArrayList<>();
		StringBuilder hql = new StringBuilder();
		StringBuilder countHql = new StringBuilder();
		hql.append(" FROM " + entityClass.getSimpleName());
		hql.append(" WHERE 1=1");
		//Add 搜索条件
		String searchValue = parameter.getSearch()==null? null: parameter.getSearch().getValue();
		if(StringUtils.hasText(searchValue)){
			hql.append(" AND (1=0");
			int i = 0;
			Column column = parameter.getColumns()[i];
			while (column !=null){
				if(column.isSearchable() && StringUtils.hasLength(column.getData()) ){
					String fieldName = column.getData();
					//只支持  String 列搜索
					if(String.class == FieldUtils.getFieldClass(entityClass, fieldName)){
						propertyNames.add("search" + String.valueOf(i));
						hql.append(" OR " + fieldName  + " LIKE :search" + String.valueOf(i));
					}
					
				}
				column = parameter.getColumns()[++i];
			}
			//搜索条件结尾
			hql.append(" )");
		}
		//countHql
		countHql.append("SELECT COUNT(1) ");
		countHql.append(hql);
		// Add Order
		int j=0;
		Order order = parameter.getOrder()[j];
		while(order !=null){
			if(j==0){
				hql.append(" ORDER BY " );
			}else {
				hql.append(", ");
			}
			//如果是字符串 使用convert(? using gbk)排序
			String orderedColumnName = parameter.getColumns()[order.getColumn()].getData();
			if(String.class ==FieldUtils.getFieldClass(entityClass, orderedColumnName))
				hql.append( "convert_gbk(" + orderedColumnName+ ") " + order.getDir().name());
			else
				hql.append( orderedColumnName + " " +  order.getDir().name());
			
			order = parameter.getOrder()[++j];
		}
		
		//获取查询结果
		Query<T> query = getSession().createQuery(hql.toString(), entityClass);
		if(StringUtils.hasText(searchValue)){
			String value= "%" + searchValue.trim() + "%";
			for (String property : propertyNames) {
				query.setParameter(property, value);
			}
		}
		List<T> list = query.setFirstResult(parameter.getStart())
			.setMaxResults(parameter.getLength())
			.getResultList();
		
		// 获取查询数量
		Query<Long> countQuery = getSession().createQuery(_generateFindCountHql(countHql.toString()), Long.class);
		if(StringUtils.hasText(searchValue)){
			String value= "%" + searchValue.trim() + "%";
			for (String property : propertyNames) {
				countQuery.setParameter(property, value);
			}
		}
		Long filterdCount = countQuery.getSingleResult();
		
		
		return new DataTableBean<>(parameter.getDraw(), findCount().intValue(),  filterdCount.intValue(), list, "");
	}
	
	@Override
	public DataTableBean<T> findDataTableByProperties(QueryParameter parameter, String[] properties, Object[] values){
		//TODO 不支持正则表达式搜索
		//TODO 只能实现String列 Integer列搜索
		if(properties.length != values.length){
			IllegalArgumentException exception = new IllegalArgumentException("传入的属性名和属性值数量不一致");
			logger.error(exception.getMessage(), exception);
			throw exception;
		}
		
		List<String> searchPropertyNames = new ArrayList<>();
		String[] namedParameters = new String[properties.length];
		StringBuilder hql = new StringBuilder();
		StringBuilder filteredCountHql = new StringBuilder();
		StringBuilder totalCountHql = new StringBuilder();
		
		hql.append(" FROM " + entityClass.getSimpleName());
		hql.append(" WHERE 1=1");
		//添加查询条件
		if(properties !=null && properties.length>0){
			for(int i=0; i<properties.length; i++){
				hql.append(" AND " + properties[i] + " = :property" + i );
				namedParameters[i]= "property" + i; 
			}
		}
		totalCountHql.append(_generateFindCountHql(hql.toString()));
		
		//Add 搜索条件
		String searchValue = parameter.getSearch()==null? null: parameter.getSearch().getValue();
		if(StringUtils.hasText(searchValue)){
			hql.append(" AND (1=0");
			int i = 0;
			Column column = parameter.getColumns()[i];
			while (column !=null){
				if(column.isSearchable() && StringUtils.hasLength(column.getData()) ){
					String fieldName = column.getData();
					//只支持  String 列搜索
					if(String.class == FieldUtils.getFieldClass(entityClass, fieldName)){
						searchPropertyNames.add("search" + String.valueOf(i));
						hql.append(" OR " + fieldName  + " LIKE :search" + String.valueOf(i));
					}
					
				}
				column = parameter.getColumns()[++i];
			}
			//搜索条件结尾
			hql.append(" )");
		}
		//countHql
		filteredCountHql.append("SELECT COUNT(1) ");
		filteredCountHql.append(hql);
		// Add Order
		int j=0;
		Order order = parameter.getOrder()[j];
		while(order !=null){
			if(j==0){
				hql.append(" ORDER BY " );
			}else {
				hql.append(", ");
			}
			//如果是字符串 使用convert(? using gbk)排序
			String orderedColumnName = parameter.getColumns()[order.getColumn()].getData();
			if(String.class ==FieldUtils.getFieldClass(entityClass, orderedColumnName))
				hql.append( "convert_gbk(" + orderedColumnName+ ") " + order.getDir().name());
			else
				hql.append( orderedColumnName + " " +  order.getDir().name());
			
			order = parameter.getOrder()[++j];
		}
		
		//获取查询结果
		Query<T> query = getSession().createQuery(hql.toString(), entityClass);
		if(namedParameters.length>0)
			for(int i=0;i<namedParameters.length;i++)
				query.setParameter(namedParameters[i], values[i]);
		if(StringUtils.hasText(searchValue)){
			String value= "%" + searchValue.trim() + "%";
			for (String property : searchPropertyNames) {
				query.setParameter(property, value);
			}
		}
		List<T> list = query.setFirstResult(parameter.getStart())
			.setMaxResults(parameter.getLength())
			.getResultList();
		
		// 获取搜索数量
		Query<Long> filteredCountQuery = getSession().createQuery(_generateFindCountHql(filteredCountHql.toString()), Long.class);
		if(namedParameters.length>0)
			for(int i=0;i<namedParameters.length;i++)
				filteredCountQuery.setParameter(namedParameters[i], values[i]);
		if(StringUtils.hasText(searchValue)){
			String value= "%" + searchValue.trim() + "%";
			for (String property : searchPropertyNames) {
				filteredCountQuery.setParameter(property, value);
			}
		}
		Long filterdCount = filteredCountQuery.getSingleResult();
		
		//获取总数量
		Query<Long> totalCountQuery = getSession().createQuery(totalCountHql.toString(), Long.class);
		if(namedParameters.length>0)
			for(int i=0;i<namedParameters.length;i++)
				totalCountQuery.setParameter(namedParameters[i], values[i]);
		Long totalCount= totalCountQuery.getSingleResult();
		
		return new DataTableBean<>(parameter.getDraw(), totalCount.intValue(),  filterdCount.intValue(), list, "");
	}
	
	
	@Override
	public DataTableBean<T> findDataTableByProperty(QueryParameter parameter, String propertyName, Object value)
	{
		return findDataTableByProperties(parameter, new String[]{propertyName}, new Object[]{value});
	}
}
