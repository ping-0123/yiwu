package com.yinzhiwu.yiwu.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public final class ReflectUtils {

	@SuppressWarnings("unused")
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
	
	public static Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException,SecurityException{
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			
			Field[] fields = getAllFields(clazz);
			for (Field field : fields) {
				if(fieldName.equals(field.getName()))
					return field;
			}
			
			throw e;
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
	 * @throws IllegalAccessException
	 */
	public static <E> E modifySourceEntityPropertiesToTarget(E source, E target) throws IllegalAccessException{
		Field[] fields = getAllFields(source.getClass());
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
