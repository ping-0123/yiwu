package com.yinzhiwu.yiwu.util.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class FieldUtils {

	private static final Log log = LogFactory.getLog(FieldUtils.class);
	
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

	public static Object getFieldValue(Object object, String fieldName) {
		if(object==null)
			throw new IllegalArgumentException();
		if(fieldName==null || "".equals(fieldName.trim()))
			return null;
	
		if(fieldName.contains(".")){
			int position = fieldName.indexOf(".");
			String preFieldName = fieldName.substring(0,position);
			String suffixFieldName= fieldName.substring(position + 1);
			Field preField;
			try {
				preField = getField(object.getClass(), preFieldName);
			} catch (NoSuchFieldException e) {
				log.error("no field " + preFieldName + " in class " + object.getClass().getName(), e);
				return null;
			}
			preField.setAccessible(true);
			/*满足 hibernate 延迟加载*/
			Object preObject = getFieldValue(object,preFieldName);
			if(preObject==null) return null;
			return getFieldValue(preObject, suffixFieldName);
		}else {
			 //  通过Method获取值, 已满足hibernate session延迟加载
			StringBuilder methodName=new StringBuilder().append("get");
			methodName.append(fieldName.substring(0,1).toUpperCase());
			methodName.append(fieldName.substring(1));
			Method method;
			try {
				method = object.getClass().getMethod(methodName.toString());
			} catch (NoSuchMethodException | SecurityException e) {
				log.error("no method " + methodName + " in class " + object.getClass().getName() , e);
				return null;
			}
			
			try {
				return method.invoke(object);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				log.error("can not invoke method " + method.getName() + "of class "  + object.getClass().getName() + "for object " + object , e);
				return null;
			}
		}
	}

	public static Field getField(Class<?> clazz, String fieldName)  throws NoSuchFieldException{
		if(clazz==null || fieldName==null || "".equals(fieldName.trim())) 
			throw new IllegalArgumentException("clazz can not be null and fieldName can not be empty");
		
		if(fieldName.contains(".")){
			int position = fieldName.indexOf(".");
			String preFieldName = fieldName.substring(0,position);
			String suffixFieldName= fieldName.substring(position + 1);
			Field preField = getField(clazz, preFieldName);
			return getField((Class<?>)preField.getGenericType(), suffixFieldName);
		}
		
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException  e) {
			Class<?> supClazz = clazz.getSuperclass();
			if(supClazz!=null){
				return getField(supClazz,fieldName);
			}else{
				log.error("no field " + fieldName + " in class " + clazz.getName(), e);
				throw e;
			}
		}
	}

	public static Class<?> getFieldClass(Class<? extends Object> clazz, String fieldName) throws NoSuchFieldException {
		if(clazz==null || fieldName==null || fieldName.trim().length()==0) 
			throw new IllegalArgumentException("clazz and fieldName can not be null");
		
		if(fieldName.contains(".")){
			int index = fieldName.indexOf(".");
			String pre = fieldName.substring(0, index);
			String  suff = fieldName.substring(index+1);
			return getFieldClass(getFieldClass(clazz, pre), suff);
		}else {
			Field field = getField(clazz, fieldName);
			return (Class<?>) field.getGenericType();
		}
	}
	
}
