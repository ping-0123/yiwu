package com.yinzhiwu.yiwu.util.reflect;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yinzhiwu.yiwu.util.ArrayUtil;
import com.yinzhiwu.yiwu.util.ReflectUtils;

public final class ClassUtils {
	
	@SuppressWarnings("unused")
	private final Log log = LogFactory.getLog(ClassUtils.class);
	
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
}
