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

import com.yinzhiwu.yiwu.util.beanutils.annotation.BeanClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.BeanProperty;
import com.yinzhiwu.yiwu.util.reflect.FieldUtils;

public final class BeanClassUtils {
	
	private static final Log logger = LogFactory.getLog(BeanClassUtils.class);
	
	/**
	 *TODO 不支持 array , map, collection 转换
	 * @param source 
	 * @param target
	 * @throws ConvertException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws InvocationTargetException 
	 * @throws NoSuchMethodException 
	 */
	public static void copyProperties(Object source, Object target) 
			throws ConvertException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InstantiationException, SecurityException, NoSuchMethodException, InvocationTargetException{
		
		if(target ==null)
			throw new IllegalArgumentException("target object cannot be null");
		if(source == null) return;
		
		//TODO 不支持 array , map, collection 转换
		
		
//		Class<?> sourceClazz = source.getClass();
//		//simple value type
//		if(isSimpleValueType(sourceClazz)){
//			if(target.getClass().isInstance(source)){
//				target= source;
//				return;
//			}else {
//				throw new InputMismatchException("simple type mismatched");
//			}
//		}
//		
//		//array type
//		if(sourceClazz.isArray()){
//			if(target.getClass().isArray()){
//				if(Array.getLength(source) == Array.getLength(target)){
//					for(int i=0; i<Array.getLength(source); i++){
//						Object nestedSource = Array.get(source, i);
//						Object nestedTarget = Array.get(target, i);
//						if(nestedTarget == null){
//							try {
//								nestedTarget = target.getClass().getComponentType().newInstance();
//							} catch (InstantiationException e) {
//								logger.error(e.getMessage(),e);
//								throw e;
//							}
//						}
//						copyProperties(nestedSource, nestedTarget);
//					}
//					return;
//				}else {
//					throw new InputMismatchException("两个数组长度不同");
//				}
//			}else {
//				throw new InputMismatchException("array type mismatched");
//			}
//		}
//		
//		//collection type
//		if(source instanceof Collection<?>){
//			//TODO
////			if(target instanceof Collection<?>){
////				//获取目标对象的参数化类型
////				ParameterizedType parameterizedType = (ParameterizedType)target.getClass().getGenericSuperclass();
////				Class<?> targetParameterizedClass = (Class<?>)parameterizedType.getActualTypeArguments()[0];
////				
////				Collection<?> sourceCollection = ((Collection<?>) source);
////				Collection<?> targetCollection = (Collection<?>) target;
////				targetCollection.clear();
////				Iterator<?> iterator = sourceCollection.iterator();
////				while (iterator.hasNext()) {
////					Object nestedSource = (Object) iterator.next();
////					Object nestedTarget = targetParameterizedClass.newInstance();
////					copyProperties(nestedSource, nestedTarget);
////				}
////				return;
////			}else {
////				throw new InputMismatchException("collection type mismatched");
////			}
//			
//			return;
//		}
//		
//		//map type
//		if(source instanceof Map<?, ?>){
//			//TODO
//			return;
//		}
//		
		
		// normal bean type
		BeanClass sourceAnnotation = source.getClass().getDeclaredAnnotation(BeanClass.class);
		if(sourceAnnotation != null && sourceAnnotation.value().isInstance(target)){
			Field[] sourceFields = com.yinzhiwu.yiwu.util.reflect.ClassUtils.getAllFields(source.getClass());
			for (Field sourceField : sourceFields) {
				
				//判断是否需要赋值给 maped target filed
				BeanProperty sourceFieldDeclaredBeanProperty = sourceField.getDeclaredAnnotation(BeanProperty.class);
				if(sourceFieldDeclaredBeanProperty !=null && (!sourceFieldDeclaredBeanProperty.inverse() || sourceFieldDeclaredBeanProperty.ignored()))
					continue; //忽略此属性
				
				//获取付给 maped target field 的值
				sourceField.setAccessible(true);
				Object sourceFieldValue = sourceField.get(source);
				if(sourceFieldValue == null) 
					continue; //sourceField value is null then ignored set maped target filed value
				
				//获取  maped target field name
				String mapedTargetFieldName = sourceField.getName();
				if(sourceFieldDeclaredBeanProperty !=null && !"".equals(sourceFieldDeclaredBeanProperty.value())) 
					mapedTargetFieldName = sourceFieldDeclaredBeanProperty.value();
				
				// 赋值给 target field
				Class<?> sourceFieldClass = sourceFieldValue.getClass();
				BeanClass sourceFieldClassAnnotation = sourceFieldClass.getDeclaredAnnotation(BeanClass.class);
				if(sourceFieldClassAnnotation !=null){
					Object mapedTargetFieldObject = FieldUtils.getFieldValue(target, mapedTargetFieldName);
					if(mapedTargetFieldObject == null){
						Class<?> mapedTargetFieldClass = FieldUtils.getFieldClass(target.getClass(), mapedTargetFieldName);
						mapedTargetFieldObject = mapedTargetFieldClass.newInstance();
						FieldUtils.setFieldValue(target, mapedTargetFieldName, mapedTargetFieldObject);
					}
					
					copyProperties(sourceFieldValue, mapedTargetFieldObject);
				}else{
					FieldUtils.setFieldValue(target, mapedTargetFieldName, sourceFieldValue);
				}
				
			}
			return;
		}
		
		BeanClass targetAnnotation = target.getClass().getDeclaredAnnotation(BeanClass.class);
		if(targetAnnotation !=null && targetAnnotation.value().isInstance(source)){
			Field[] targetFields = com.yinzhiwu.yiwu.util.reflect.ClassUtils.getAllFields(target.getClass());
			for (Field field : targetFields) {
				
				//判断是否忽略该属性赋值
				BeanProperty beanProperty = field.getDeclaredAnnotation(BeanProperty.class);
				if(beanProperty != null && beanProperty.ignored()) continue;  //忽略该属性
				
				// 获取 maped source field name
				String mapedSourceFieldName = field.getName();
				if(beanProperty != null && !"".equals(beanProperty.value()))
					mapedSourceFieldName = beanProperty.value();
				
				// 或去 maped source field value
				Object mapedSourceFieldValue = FieldUtils.getFieldValue(source,mapedSourceFieldName);
				if(mapedSourceFieldValue == null ) continue;  // 值为null 忽略
				
				// 赋值给 target field
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
					copyProperties(mapedSourceFieldValue, targetFieldObject);
				}else{
					field.setAccessible(true);
					field.set(target, mapedSourceFieldValue);
				}
			}
			return ;
		}
		
		throw new ConvertException(source.getClass(), target.getClass());
	}
	
	@SuppressWarnings("unused")
	private static void setMapedTargetFieldValue(Object target, String mapedTargetFieldName, Object value){
		if(target==null)
			throw new IllegalArgumentException("target object can not be null");
		if(value==null || mapedTargetFieldName==null || "".equals(mapedTargetFieldName.trim()))
			return ;
		
		
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
