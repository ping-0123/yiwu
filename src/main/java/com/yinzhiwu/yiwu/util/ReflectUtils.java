package com.yinzhiwu.yiwu.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;


public final class ReflectUtils {

	private static Log logger = LogFactory.getLog(ReflectUtils.class);
	
	/**
	 * Returns an array contains Field objects reflecting all the fields declared  or inherited by
	 * the class or interface represented by this Class object. This includes
	 * public, protected, default (package) access, and private fields, also
	 * includes inherited fields.
	 * 
	 * @param clazz
	 * @return
	 * 
	 */
	public static Field[] getAllFields(Class<?> clazz) {
		if (clazz == null)
			throw new IllegalArgumentException("clazz 不能为null");

		if (clazz != Object.class) {
			Field[] fields = clazz.getDeclaredFields();
			Class<?> superClazz = clazz.getSuperclass();
			Field[] superFields = ReflectUtils.getAllFields(superClazz);
			if (fields == null || superFields == null) {
				return superFields==null? fields:superFields;
			} else {
				return ArrayUtil.concat( superFields, fields);
			}
		}
		return null;
	}
	
	public static Field getField(Class<?> clazz, String fieldName) 
			throws NoSuchFieldException,SecurityException{
		if(clazz==null || fieldName==null || "".equals(fieldName.trim())) 
			throw new IllegalArgumentException("clazz can not be null and fieldName can not be empty");
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			Class<?> supClazz = clazz.getSuperclass();
			if(supClazz!=null){
				return getField(supClazz,fieldName);
			}
//			Field[] fields = getAllFields(clazz);
//			for (Field field : fields) {
//				if(fieldName.equals(field.getName()))
//					return field;
//			}
			
			throw e;
		}
		
	}
	
	
	public static Field getNestedField(Class<?> clazz, String fieldName) throws NoSuchFieldException, SecurityException{
		if(clazz==null || fieldName==null || "".equals(fieldName.trim())) 
			throw new IllegalArgumentException("clazz can not be null and fieldName can not be empty");
		
		if(fieldName.contains(".")){
			int position = fieldName.indexOf(".");
			String preFieldName = fieldName.substring(0,position);
			String suffixFieldName= fieldName.substring(position + 1);
			Field preField = getNestedField(clazz, preFieldName);
			return getNestedField((Class<?>)preField.getGenericType(), suffixFieldName);
		}
		
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			Class<?> supClazz = clazz.getSuperclass();
			if(supClazz!=null){
				return getField(supClazz,fieldName);
			}
			throw e;
		}
	}
	
	
	/**
	 * 
	 * @param object
	 * @param fieldName
	 * @return 如果fieldName为空，返回null
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 */
	public static Object getFieldValue(Object object, String fieldName) 
	{
		if(object==null)
			throw new IllegalArgumentException();
		if(fieldName==null || "".equals(fieldName.trim()))
			return null;
		
		try{
			if(fieldName.contains(".")){
				int position = fieldName.indexOf(".");
				String preFieldName = fieldName.substring(0,position);
				String suffixFieldName= fieldName.substring(position + 1);
				Field preField = getField(object.getClass(), preFieldName);
				preField.setAccessible(true);
				/*满足 hibernate 延迟加载*/
				Object preObject = getFieldValue(object,preFieldName);
				if(preObject==null) return null;
				return getFieldValue(preObject, suffixFieldName);
			}else {
				/**
				 * 通过Method获取值, 已满足hibernate session延迟加载
				 */
				StringBuilder methodName=new StringBuilder().append("get");
				methodName.append(fieldName.substring(0,1).toUpperCase());
				methodName.append(fieldName.substring(1));
				Method method = object.getClass().getMethod(methodName.toString());
				return method.invoke(object);
				/**
				 * 通过Field获取值
				 *
				Field field = getField(object.getClass(), fieldName);
				field.setAccessible(true);
				return field.get(object);
				*/
			}
		}catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
			logger.error(e.getMessage(),e);
			return null;
		}
		
	}
	
	
	
	/**
	 * 当 fieldName 包含"."时 使用该方法设置field的值,如果object中包含申明类型为接口的属性， 先实例化该属性， 否则会抛出  InstantiationException
	 * @param bean
	 * @param fieldName
	 * @param value
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws InvocationTargetException 
	 * @throws NoSuchMethodException 
	 */
	public static void setFieldValue(Object bean , String fieldName, Object value) 
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException, NoSuchMethodException, InvocationTargetException{
		if(bean==null)
			throw new IllegalArgumentException();
		if(value==null || fieldName==null || "".equals(fieldName.trim()))
			return ;
		if(fieldName.contains(".")){
			int position = fieldName.indexOf(".");
			String preFieldName = fieldName.substring(0, position);
			String suffixFieldName = fieldName.substring(position + 1);
			Field preField = getField(bean.getClass(), preFieldName);
			Object preFieldValue = getFieldValue(bean, preFieldName);
			if (preFieldValue == null){
				preFieldValue = ((Class<?>)preField.getGenericType()).newInstance();
				preField.setAccessible(true);
				preField.set(bean, preFieldValue);
			}
			setFieldValue(preFieldValue, suffixFieldName, value);
		}else {
			Field field = getField(bean.getClass(), fieldName);
			field.setAccessible(true);
			field.set(bean, value);
		}
	}
	
	public static Class<?> getFieldClass(Class<?> clazz, String fieldName) throws NoSuchFieldException, SecurityException{
		if(clazz==null || !StringUtils.hasText(fieldName)) 
			throw new IllegalArgumentException("clazz and fieldName can not be null");
		
		if(fieldName.contains(".")){
			int index = fieldName.indexOf(".");
			String pre = fieldName.substring(0, index);
			String  suff = fieldName.substring(index+1);
			return getFieldClass(getFieldClass(clazz, pre), suff);
		}else {
			Field field = getField(clazz, fieldName);
			Class<?> cls = getParameterizedType(field);
			if(cls != null) 
				return cls;
			else
				return (Class<?>) field.getGenericType();
		}
	}
	
	public static void showObject(Object object){
		if(object == null )
			return;
		Class<?> clazz = object.getClass();
		if(clazz.isPrimitive()
				|| clazz.isEnum()
				|| clazz.isArray()
				|| object instanceof Iterable<?>
				|| clazz==Date.class
				|| clazz== Boolean.class
				|| clazz == Integer.class
				|| clazz == Long.class
				|| clazz == Short.class
				|| clazz == Float.class
				|| clazz == Float.class
				|| object instanceof Number
				|| clazz==String.class
				|| clazz ==Byte.class
				|| clazz == Character.class
		){
			System.out.println(object);
			return;
		}
		
		Field[] fields = getAllFields(object.getClass());
		for (Field field : fields) {
			field.setAccessible(true);
			System.out.println("value of property " + field.getName() + " is :" );
			try {
				showObject(field.get(object));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @see com.yinzhiwu.yiwu.dao.impl.BaseDaoImpl#modify
	 * 
	 * @param source
	 * @param target
	 * @return
	 * @throws IllegalAccessException if source or target is null
	 */
	public static <E> E modifySourceEntityPropertiesToTarget(E source, E target) throws IllegalAccessException{
		if(null==source || null ==target)
			throw new IllegalArgumentException("source and target can not be null");
			
		Field[] fields = getAllFields(source.getClass());
		for (Field f : fields) {
			f.setAccessible(true);
			if (// 静态属性不变
					!Modifier.isStatic(f.getModifiers())
					// target属性为null ,source 对应的属性不变
					&& f.get(target) != null
					// 如果target属性为Collection, size不能为0;
					&& (!(f.get(target) instanceof Collection) || ((Collection<?>) f.get(target)).size()>0)
					// Id 主键不改变
					&& f.getDeclaredAnnotation(Id.class) == null
					// 属性值相同无须改变
					&& !f.get(target).equals(f.get(source))
					// 排除OneToMany 映射
					&& f.getDeclaredAnnotation(OneToMany.class) == null)
					// 不支持修改关联表对象 && f.getDeclaredAnnotation(ManyToOne.class) == null
				if (f.getDeclaredAnnotation(Embedded.class) != null) {
					Object embededSource = f.get(source);
					if(null == embededSource)
						f.set(source, f.get(target));
					else
						f.set(source, modifySourceEntityPropertiesToTarget(f.get(source), f.get(target)));
				} else
					f.set(source, f.get(target));
		}
		return source;
	}
	
	public static Class<?> getParameterizedType(Field field){
		Type fieldClass = field.getGenericType();
		if(fieldClass != null && fieldClass  instanceof ParameterizedType){
			ParameterizedType pt = (ParameterizedType) fieldClass;
			Type[] types = pt.getActualTypeArguments();
			if(types != null && types.length > 0){
				return (Class<?>) types[0];
			}
		}
	
		return null;
	}
	
}
