package com.yinzhiwu.yiwu.util.convert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yinzhiwu.yiwu.util.ReflectUtils;
import com.yinzhiwu.yiwu.util.convert.annotation.BeanClass;
import com.yinzhiwu.yiwu.util.convert.annotation.BeanProperty;

public class BeanUtils {
	
	private static final Log logger = LogFactory.getLog(BeanUtils.class);
	
	public static void copyProperties(Object source, Object target) 
			throws ConvertException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException, NoSuchMethodException, InvocationTargetException{
		
		if(target ==null)
			throw new IllegalArgumentException("target object cannot be null");
		if(source == null) return;
		
		BeanClass sourceAnnotation = source.getClass().getDeclaredAnnotation(BeanClass.class);
		if(sourceAnnotation != null && sourceAnnotation.value().isInstance(target)){
			Field[] sourceFields = ReflectUtils.getAllFields(source.getClass());
			for (Field field : sourceFields) {
				BeanProperty beanProperty = field.getDeclaredAnnotation(BeanProperty.class);
				if(beanProperty !=null && beanProperty.inverse()){
					String targetFieldName = 
							("".equals(beanProperty.value()))?field.getName():beanProperty.value();
					field.setAccessible(true);
					setBeanPropertyValue(target,targetFieldName,field.get(source));
//					ReflectUtils.setFieldValue(target, targetFieldName,field.get(source) );
				}
			}
			return;
		}
		
		BeanClass targetAnnotation = target.getClass().getDeclaredAnnotation(BeanClass.class);
		if(targetAnnotation !=null && targetAnnotation.value().isInstance(source)){
			Field[] targetFields = ReflectUtils.getAllFields(target.getClass());
			for (Field field : targetFields) {
				BeanProperty beanProperty = field.getDeclaredAnnotation(BeanProperty.class);
				if(beanProperty!=null){
					String mapedFieldName=("".equals(beanProperty.value()))?field.getName():beanProperty.value();
					Object mapedFieldValue = ReflectUtils.getFieldValue(source,mapedFieldName);
					if(mapedFieldValue == null ) 
						continue;
					Class<?> targetFieldClass =(Class<?>) field.getGenericType();
					BeanClass targetfieldClassAnnotation = targetFieldClass.getDeclaredAnnotation(BeanClass.class);
					if(targetfieldClassAnnotation !=null){
						/*深度复制*/
						field.setAccessible(true);
						Object targetFieldObject = field.get(target);
						if(targetFieldObject == null){
							targetFieldObject = targetFieldClass.newInstance();
							field.set(target, targetFieldObject);
						}
						copyProperties(mapedFieldValue, targetFieldObject);
					}else{
						field.setAccessible(true);
						field.set(target, mapedFieldValue);
					}
				}
			}
			return ;
		}
		
		throw new ConvertException(source.getClass(), target.getClass());
	}
	
	/**
	 * 
	 * @param bean
	 * @param propertyName
	 * @param value
	 * @throws ConvertException
	 */
	private static void setBeanPropertyValue(Object bean, String propertyName, Object value) throws ConvertException 
			{
		
		if(bean==null)
			throw new IllegalArgumentException();
		if(value==null || propertyName==null || "".equals(propertyName.trim()))
			return ;
		
		try {
			Class<?> valueClass = value.getClass();
			BeanClass valueClassAnnotation = valueClass.getDeclaredAnnotation(BeanClass.class);
			if(valueClassAnnotation !=null){
				Object propertyObject = ReflectUtils.getFieldValue(bean, propertyName);
				if(propertyObject == null){
					Class<?> propertyClass = (Class<?>) (ReflectUtils.getField(bean.getClass(), propertyName).getGenericType());
					propertyObject = propertyClass.newInstance();
					ReflectUtils.setFieldValue(bean, propertyName, propertyObject);
				}
				copyProperties(value, propertyObject);
			}else
//			Class<?> propertyClass = (Class<?>) (ReflectUtils.getField(bean.getClass(), propertyName).getGenericType());
//			BeanClass propertyClassAnnotation = propertyClass.getDeclaredAnnotation(BeanClass.class);
//			if(propertyClassAnnotation !=null){
//				Object propertyObject = ReflectUtils.getFieldValue(bean, propertyName);
//				if(propertyObject == null){
//					propertyObject = propertyClass.newInstance();
//					ReflectUtils.setFieldValue(bean, propertyName, propertyObject);
//				}
//				copyProperties(value, propertyObject);
//				return;
//			}
			
			ReflectUtils.setFieldValue(bean, propertyName, value);
			
		} catch (NoSuchFieldException | InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException | NoSuchMethodException | InvocationTargetException e) {
			logger.error(e.getMessage(),e);
			return;
		}
	}

}
