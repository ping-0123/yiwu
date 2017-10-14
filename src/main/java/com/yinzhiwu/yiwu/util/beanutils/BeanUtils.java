package com.yinzhiwu.yiwu.util.beanutils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ClassUtils;

import com.yinzhiwu.yiwu.util.ReflectUtils;
import com.yinzhiwu.yiwu.util.beanutils.annotation.BeanClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.BeanProperty;

public class BeanUtils {
	
	private static final Log logger = LogFactory.getLog(BeanUtils.class);
	
	public static void copyProperties(Object source, Object target) 
			throws ConvertException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		
		if(target ==null)
			throw new IllegalArgumentException("target object cannot be null");
		if(source == null) return;
		
		BeanClass sourceAnnotation = source.getClass().getDeclaredAnnotation(BeanClass.class);
		if(sourceAnnotation != null && sourceAnnotation.value().isInstance(target)){
			Field[] sourceFields = ReflectUtils.getAllFields(source.getClass());
			for (Field sourceField : sourceFields) {
				BeanProperty sourceFieldDeclaredBeanProperty = sourceField.getDeclaredAnnotation(BeanProperty.class);
				if(sourceFieldDeclaredBeanProperty !=null && (!sourceFieldDeclaredBeanProperty.inverse() || sourceFieldDeclaredBeanProperty.ignored()))
					continue; //忽略此属性
				
				String targetFieldName = sourceField.getName();
				if(sourceFieldDeclaredBeanProperty !=null && !"".equals(sourceFieldDeclaredBeanProperty.value())) 
					targetFieldName = sourceFieldDeclaredBeanProperty.value();
				sourceField.setAccessible(true);
				setBeanPropertyValue(target,targetFieldName,sourceField.get(source));
//					ReflectUtils.setFieldValue(target, targetFieldName,field.get(source) );
				
			}
			return;
		}
		
		BeanClass targetAnnotation = target.getClass().getDeclaredAnnotation(BeanClass.class);
		if(targetAnnotation !=null && targetAnnotation.value().isInstance(source)){
			Field[] targetFields = ReflectUtils.getAllFields(target.getClass());
			for (Field field : targetFields) {
				BeanProperty beanProperty = field.getDeclaredAnnotation(BeanProperty.class);
				if(beanProperty != null && beanProperty.ignored()) continue;  //忽略该属性
				
				String mapedFieldName = field.getName();
				if(beanProperty != null && !"".equals(beanProperty.value()))
					mapedFieldName = beanProperty.value();
				
				Object mapedFieldValue = ReflectUtils.getFieldValue(source,mapedFieldName);
				if(mapedFieldValue == null ) continue;  // 值为null 忽略
				
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
			}else{
				ReflectUtils.setFieldValue(bean, propertyName, value);
			}
		} catch (NoSuchFieldException | InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException | NoSuchMethodException | InvocationTargetException e) {
			logger.error(e.getMessage(),e);
			return;
		}
	}

	
	/**
	 * Check if the given type represents a "simple" property:
	 * a primitive, a String or other CharSequence, a Number, a Date,
	 * a URI, a URL, a Locale, a Class, or a corresponding array.
	 * <p>Used to determine properties to check for a "simple" dependency-check.
	 * @param clazz the type to check
	 * @return whether the given type represents a "simple" property
	 */
	public static boolean isSimpleProperty(Class<?> clazz) {
		if(clazz==null)
			throw new IllegalArgumentException("clazz must not be null");
		
		return isSimpleValueType(clazz) || (clazz.isArray() && isSimpleValueType(clazz.getComponentType()));
	}
	
	/**
	 * Check if the given type represents a "simple" value type:
	 * a primitive, a String or other CharSequence, a Number, a Date,
	 * a URI, a URL, a Locale or a Class.
	 * @param clazz the type to check
	 * @return whether the given type represents a "simple" value type
	 */
	public static boolean isSimpleValueType(Class<?> clazz) {
		return (ClassUtils.isPrimitiveOrWrapper(clazz) || clazz.isEnum() ||
				CharSequence.class.isAssignableFrom(clazz) ||
				Number.class.isAssignableFrom(clazz) ||
				Date.class.isAssignableFrom(clazz) ||
				URI.class == clazz || URL.class == clazz ||
				Locale.class == clazz || Class.class == clazz );
	}

	
	public static void copyProperty(Object bean, String propertyName, Object value){
		if(bean==null)
			throw new IllegalArgumentException("bean can not be null");
		if(propertyName==null|| "".equals(propertyName.trim()) || value==null) 
			return;
		
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperty(bean, propertyName, value);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
}
