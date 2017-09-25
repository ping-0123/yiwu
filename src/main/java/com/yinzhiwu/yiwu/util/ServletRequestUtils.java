package com.yinzhiwu.yiwu.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yinzhiwu.yiwu.model.datatable.Order.Direction;

/**
*@Author ping
*@Time  创建时间:2017年9月25日上午9:28:28
*
*/

public class ServletRequestUtils {

	
	public static Object parseParameter(HttpServletRequest request, Class<?> parameterType) 
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		
		Object para = parameterType.newInstance();
		
		Map<String, String[]> map = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			
			/**
			 * 1. match primary or enum type  eg: start "[_a-zA-Z]+[_\\w]*"
			 * 2. match non primary type eg: search[value] "[_a-zA-Z]+[_\\w]*\\[[_a-zA-Z]+[_\\w]*\\]$"
			 * 3. match array			eg: columns[1]  "[_a-zA-Z]+[_\\w]*\\[[0-9]+\\].*"
			 */
			if(entry.getValue() !=null 
					&& entry.getValue().length>0 
					&& !"".equals(entry.getValue()[0].trim()))
				setComplexFieldValue(entry.getKey(),entry.getValue()[0],para);
			
		}
		return para;
	}
	
	private static void setComplexFieldValue(String paraName, String value, Object entity) 
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		final String regPrimary = "[_a-zA-Z]+[_\\w]*";
		final String regObject = "[_a-zA-Z]+[_\\w]*\\[[_a-zA-Z]+[_\\w]*\\].*";
		final String regArray = "[_a-zA-Z]+[_\\w]*\\[[0-9]+\\].*";
		 
		 if(paraName.matches(regPrimary)){
			 setPrimaryTypeFieoldValue(paraName, value, entity);
			 
		 }else if(paraName.matches(regObject)){
			 SetClazzedTypeFiledValue(paraName, value, entity);
			 
		 }else if(paraName.matches(regArray)){
			 setArrayTypeFiledValue(paraName, value, entity);
		 }
	}

	private static <T> void setArrayTypeFiledValue(String paraName, String value, Object entity)
			throws NoSuchFieldException, IllegalAccessException,  java.lang.InstantiationException {
		 String fieldname = paraName.substring(0, paraName.indexOf("["));
		 Field field = entity.getClass().getDeclaredField(fieldname);
		 field.setAccessible(true);
		 Object fieldValue = field.get(entity);
		 if(fieldValue == null){
			 throw new java.lang.InstantiationException("can not call newInstance of an array class or an interface(collection) class");
		 }
		 
		 int index = Integer.valueOf(paraName.substring(paraName.indexOf("[") + 1, paraName.indexOf("]")));
		 //TODO 如果 field.getClass is Collection type not an array
		 Class<?> fieldClass = (Class<?>) field.getGenericType();
		 if(fieldClass.isArray()){
			 // field is an array
			 Object[] subs= (Object[]) field.get(entity);
			 if(subs[index] == null)
				 subs[index] = subs.getClass().getComponentType().newInstance();
			 Object sub = subs[index];
			 String subFieldname = paraName.substring( paraName.indexOf("]") +2);
			 subFieldname = subFieldname.replaceFirst("]", "");
			 
			 setComplexFieldValue(subFieldname, value, sub);
		 }else if(fieldValue instanceof Collection ){
//			 Collection<T> relFieldValue = (Collection<T>) fieldValue;
//			 Class<T> parameterizedType = (Class<T>) ReflectUtils.getParameterizedType(field);
//			 if(relFieldValue.size() < index + 1){
//				 relFieldValue.add((T) parameterizedType.newInstance());
//			 }
			 
		 }else{
			 //TODO 抛异常 不能转为非集合，非array实体
		 }
	}

	private static void SetClazzedTypeFiledValue(String paraName, String value, Object entity)
			throws NoSuchFieldException, IllegalAccessException, InstantiationException {
	
		String fieldname = paraName.substring(0, paraName.indexOf("["));
		 Field field = entity.getClass().getDeclaredField(fieldname);
		 field.setAccessible(true);
		//迭代
		 Object subEntity = field.get(entity);
		 if(subEntity == null){
			 Class<?> clazz= (Class<?>) field.getGenericType();
			 subEntity = clazz.newInstance();
			 field.set(entity, subEntity);
		 }
		 String subFieldname = paraName.substring( paraName.indexOf("[") + 1);
		 subFieldname  = subFieldname.replaceFirst("]", "");
		 
		 setComplexFieldValue(subFieldname, value, subEntity);
	}

	private static void setPrimaryTypeFieoldValue(String paraName, String value, Object entity)
			throws NoSuchFieldException, IllegalAccessException {
		String  fieldname = paraName;
		 Field field = entity.getClass().getDeclaredField(fieldname);
		 field.setAccessible(true);
		 Class<?> fieldClass = (Class<?>) field.getGenericType();
		if ("boolean".equals(fieldClass.getSimpleName()) || fieldClass.equals(Boolean.class))
			field.set(entity, Boolean.valueOf(value));
		else if ("int".equals(fieldClass.getName()) || fieldClass.equals(Integer.class))
			field.set(entity, Integer.valueOf(value));
		else if (fieldClass.equals(Long.TYPE.getClass()) || fieldClass.equals(Long.class))
			field.set(entity, Long.valueOf(value));
		else if (fieldClass.equals(Short.TYPE.getClass()) || fieldClass.equals(Short.class))
			field.set(entity, Short.valueOf(value));
		else if (fieldClass.equals(Float.TYPE.getClass()) || fieldClass.equals(Float.class))
			field.set(entity, Float.valueOf(value));
		else if (fieldClass.equals(Double.TYPE.getClass()) || fieldClass.equals(Double.class))
			field.set(entity, Double.valueOf(value));
		else if (fieldClass.equals(Character.TYPE.getClass()) || fieldClass.equals(Character.class))
			field.set(entity, value.getBytes());
		else if (fieldClass.equals(Byte.TYPE.getClass()) || fieldClass.equals(Byte.class))
			field.set(entity, Byte.valueOf(value));
		else if (fieldClass.isEnum()) {
			//TODO 	 思考其他枚举类型怎么转换
			if (fieldClass.equals(Direction.class))
				field.set(entity, Direction.valueOf(value));
		 }else if(fieldClass.equals(String.class)){
			field.set(entity, value);
		}else {
			throw new UnsupportedOperationException("can not convert string \"" + value + "\" to target type " + fieldClass.getName());
		}
	}
	
	

}
