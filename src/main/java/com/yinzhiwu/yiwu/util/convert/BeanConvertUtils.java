package com.yinzhiwu.yiwu.util.convert;

import java.lang.reflect.Field;

import com.yinzhiwu.yiwu.dao.impl.BaseDaoImpl;
import com.yinzhiwu.yiwu.dao.impl.OrderYzwDaoImpl;
import com.yinzhiwu.yiwu.util.ReflectUtils;
import com.yinzhiwu.yiwu.util.convert.annotation.BeanClass;
import com.yinzhiwu.yiwu.util.convert.annotation.BeanProperty;

public class BeanConvertUtils {
	
	public static void copyProperties(Object source, Object target) 
			throws ConvertException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException{
		
		if(source==null || target ==null)
			throw new IllegalArgumentException();
		BeanClass sourceAnnotation = source.getClass().getDeclaredAnnotation(BeanClass.class);
		BeanClass targetAnnotation = target.getClass().getDeclaredAnnotation(BeanClass.class);
		if(sourceAnnotation != null && sourceAnnotation.value().isInstance(target)){
			Field[] sourceFields = ReflectUtils.getAllFields(source.getClass());
			for (Field field : sourceFields) {
				BeanProperty beanProperty = field.getDeclaredAnnotation(BeanProperty.class);
				if(beanProperty !=null){
					String targetFieldName = beanProperty.value();
					field.setAccessible(true);
					ReflectUtils.setFieldValue(target, targetFieldName,field.get(source) );
				}
			}
			return;
		}
		if(targetAnnotation !=null && targetAnnotation.value().isInstance(source)){
			Field[] targetFields = ReflectUtils.getAllFields(target.getClass());
			for (Field field : targetFields) {
				BeanProperty beanProperty = field.getDeclaredAnnotation(BeanProperty.class);
				if(beanProperty!=null){
					String mapedFieldName = beanProperty.value();
					field.setAccessible(true);
					field.set(target, ReflectUtils.getFieldValue(source,mapedFieldName));
				}
			}
			
			return ;
		}
		
		throw new ConvertException(source.getClass(), target.getClass());
	}
	
	

	public static void main(String[] args) {
		Class<?> a = BaseDaoImpl.class;
		Class<?> b = OrderYzwDaoImpl.class;
		if(b.isAssignableFrom(a))
			System.out.println("a 是  b 的父类");
		else
			System.out.println("a 不是  b 的父类");
		
	}
}
