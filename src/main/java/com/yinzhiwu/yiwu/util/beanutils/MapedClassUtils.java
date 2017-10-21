package com.yinzhiwu.yiwu.util.beanutils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;
import com.yinzhiwu.yiwu.util.reflect.FieldUtils;

public final class MapedClassUtils {
	
	private static final Log logger = LogFactory.getLog(MapedClassUtils.class);
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @throws MapedClassDismatchException
	 * @throws MapedPropertyDismatchException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void copyProperties(Object source, Object target)  throws MapedClassDismatchException, MapedPropertyDismatchException{
		
		if(target ==null)
			throw new IllegalArgumentException("target object cannot be null");
		if(source == null) return;
		
		//TODO 不支持 array , map, collection 转换
		
		
		Class<?> sourceClazz = source.getClass();
		//simple value type
		if( BeanUtils.isSimpleValueType(sourceClazz)){
			if(target.getClass().isInstance(source)){
				target= source;
				return;
			}else {
				throw new InputMismatchException("simple type mismatched");
			}
		}
		
		//array type
		if(sourceClazz.isArray()){
			if(target.getClass().isArray()){
				if(Array.getLength(source) == Array.getLength(target)){
					for(int i=0; i<Array.getLength(source); i++){
						Object nestedSource = Array.get(source, i);
						Object nestedTarget = Array.get(target, i);
						if(nestedTarget == null){
							try {
								nestedTarget = target.getClass().getComponentType().newInstance();
							} catch (InstantiationException | IllegalAccessException e) {
								logger.error(e.getMessage(),e);
								throw new MapedClassDismatchException( 
										target.getClass().getComponentType().getName() + "is can not instanced", e);
							}
						}
						copyProperties(nestedSource, nestedTarget);
					}
					return;
				}else {
					throw new InputMismatchException("两个数组长度不同");
				}
			}else {
				throw new InputMismatchException("array type mismatched");
			}
		}
		
		//collection type
		if(source instanceof Collection<?>){
			if(target instanceof Collection<?>){
				//获取目标对象的参数化类型
				ParameterizedType parameterizedType = (ParameterizedType)target.getClass().getGenericSuperclass();
				Class<?> targetParameterizedClass = (Class<?>)parameterizedType.getActualTypeArguments()[0];
				
				Collection sourceCollection = (Collection) source;
				Collection targetCollection = (Collection) target;
				targetCollection.clear();
				Iterator<?> iterator = sourceCollection.iterator();
				while (iterator.hasNext()) {
					Object nestedSource = (Object) iterator.next();
					Object nestedTarget;
					try {
						nestedTarget = targetParameterizedClass.newInstance();
						copyProperties(nestedSource, nestedTarget);
						targetCollection.add(nestedTarget);
					} catch (InstantiationException | IllegalAccessException e) {
						logger.error(e.getMessage());
						throw new MapedClassDismatchException(e);
					}
				}
				return;
			}else {
				throw new InputMismatchException("collection type mismatched");
			}
			
		}
//		
//		//map type
//		if(source instanceof Map<?, ?>){
//			//TODO
//			return;
//		}
//		
		// normal bean type
		MapedClass sourceAnnotation = source.getClass().getDeclaredAnnotation(MapedClass.class);
		if(sourceAnnotation != null && sourceAnnotation.value().isInstance(target)){
			Field[] sourceFields = com.yinzhiwu.yiwu.util.reflect.ClassUtils.getAllFields(source.getClass());
			for (Field sourceField : sourceFields) {
				
				//静态属性不变换
				if(Modifier.isStatic( sourceField.getModifiers()))
					continue;
				//判断是否需要赋值给 maped target filed
				MapedProperty sourceFieldDeclaredBeanProperty = sourceField.getDeclaredAnnotation(MapedProperty.class);
				if(sourceFieldDeclaredBeanProperty !=null && (!sourceFieldDeclaredBeanProperty.inverse() || sourceFieldDeclaredBeanProperty.ignored()))
					continue; //忽略此属性
				
				//获取付给 maped target field 的值
				sourceField.setAccessible(true);
				Object sourceFieldValue = null;
				try {
					sourceFieldValue = sourceField.get(source);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					logger.error(e.getMessage(),e);
					throw new MapedClassDismatchException(e);
				}
				if(sourceFieldValue == null) 
					continue; //sourceField value is null then ignored set maped target filed value
				
				//获取  maped target field name
				String mapedTargetFieldName = sourceField.getName();
				if(sourceFieldDeclaredBeanProperty !=null && !"".equals(sourceFieldDeclaredBeanProperty.value())) 
					mapedTargetFieldName = sourceFieldDeclaredBeanProperty.value();
				
				// 赋值给 target field
				Class<?> sourceFieldClass = sourceFieldValue.getClass();
				MapedClass sourceFieldClassAnnotation = sourceFieldClass.getDeclaredAnnotation(MapedClass.class);
				try{
					if(sourceFieldClassAnnotation !=null){
						Object mapedTargetFieldObject = FieldUtils.getFieldValue(target, mapedTargetFieldName);
						if(mapedTargetFieldObject == null){
							Class<?> mapedTargetFieldClass;
							try {
								mapedTargetFieldClass = FieldUtils.getFieldClass(target.getClass(), mapedTargetFieldName);
							} catch (NoSuchFieldException e) {
								logger.error(e.getMessage(),e);
								throw new MapedPropertyDismatchException(e);
							}
							mapedTargetFieldObject = mapedTargetFieldClass.newInstance();
							try {
								FieldUtils.setFieldValue(target, mapedTargetFieldName, mapedTargetFieldObject);
							} catch (IllegalArgumentException | NoSuchFieldException | SecurityException | NoSuchMethodException | InvocationTargetException e) {
								//忽略赋值失败
								logger.error(e.getMessage(),e);
								continue;
							}
						}
						
						copyProperties(sourceFieldValue, mapedTargetFieldObject);
					}else{
						try {
							FieldUtils.setFieldValue(target, mapedTargetFieldName, sourceFieldValue);
						} catch (IllegalArgumentException | NoSuchFieldException | SecurityException | NoSuchMethodException | InvocationTargetException e) {
							//忽略赋值失败
							logger.error(e.getMessage(),e);
							continue;
						}
					}
				}catch (Exception e) {
					// TODO: handle exception
					logger.error(e.getMessage(),e);
					throw new RuntimeException(e);
				}
			}
			return;
		}
		
		MapedClass targetAnnotation = target.getClass().getDeclaredAnnotation(MapedClass.class);
		if(targetAnnotation !=null && targetAnnotation.value().isInstance(source)){
			Field[] targetFields = com.yinzhiwu.yiwu.util.reflect.ClassUtils.getAllFields(target.getClass());
			for (Field field : targetFields) {
				//静态属性不变换
				if(Modifier.isStatic( field.getModifiers()))
					continue;
				
				//判断是否忽略该属性赋值
				MapedProperty beanProperty = field.getDeclaredAnnotation(MapedProperty.class);
				if(beanProperty != null && beanProperty.ignored()) continue;  //忽略该属性
				
				// 获取 maped source field name
				String mapedSourceFieldName = field.getName();
				if(beanProperty != null && !"".equals(beanProperty.value()))
					mapedSourceFieldName = beanProperty.value();
				
				// 或去 maped source field value
				Object mapedSourceFieldValue = FieldUtils.getFieldValue(source,mapedSourceFieldName);
				if(mapedSourceFieldValue == null ) continue;  // 值为null 忽略
				
				// 赋值给 target field
				Class<?> targetFieldClass =(Class<?>) field.getGenericType(); //TODO 当field.getGenericType返回值为ParameterizedType时报错
				MapedClass targetfieldClassAnnotation = targetFieldClass.getDeclaredAnnotation(MapedClass.class);
				try{
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
				}catch (Exception e) {
					// TODO: handle exception
					logger.error(e.getMessage(),e);
					throw new RuntimeException(e);
				}
			}
			return ;
		}
		
		
		throw new MapedClassDismatchException(source.getClass(), target.getClass());
	}
	
	

	
	

	
}
