package com.yinzhiwu.yiwu.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public final class ReflectUtils {

	/**
	 * Returns an array of Field objects reflecting all the fields declared by
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
}
