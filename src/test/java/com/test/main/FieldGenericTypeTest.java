package com.test.main;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.yinzhiwu.yiwu.model.view.CourseVO;

public class FieldGenericTypeTest {
	
	
	@Test
	public void testListField(){
		Field field;
		try {
			field = CourseVO.class.getDeclaredField("lessons");
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		Type genericType = field.getGenericType();
		System.err.println(genericType);
		if(genericType instanceof ParameterizedType){
			ParameterizedType parameterizedType = (ParameterizedType) genericType;
			System.err.println("type arguments is  " + parameterizedType.getActualTypeArguments()[0]);
			System.err.println("parameterized type name is " + parameterizedType.getTypeName());
			System.err.println(" owner type is " + parameterizedType.getOwnerType());
			System.err.print("raw type is " + parameterizedType.getRawType());
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testDeclareList(){
		List<CourseVO>  vos = new ArrayList<CourseVO>();
		System.err.println("vos class is " + vos.getClass());
		Type genericSuperclass = vos.getClass().getGenericSuperclass();
		System.out.println("vos generic super class is " + genericSuperclass);
		if(genericSuperclass instanceof ParameterizedType){
			ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
			System.out.println("vos parameterized type argument is " + parameterizedType.getActualTypeArguments()[0]);
			System.out.println("owner type is "  + parameterizedType.getOwnerType());
			System.out.println("raw type is " + parameterizedType.getRawType());
			Class rawtype = (Class) parameterizedType.getRawType();
			System.out.println("raw type class is " + rawtype.getName());
			System.out.println("type name is " + parameterizedType.getTypeName());
		}
		
		System.out.println();
		System.out.println("start print out vos interfaces....");
		
		Type[] genericInterfaces = vos.getClass().getGenericInterfaces();
		for (int i=0; i< genericInterfaces.length; i++) {
			System.out.println("the " + i + "th interface");
			Type type = genericInterfaces[i];
			if(type instanceof ParameterizedType){
				ParameterizedType parameterizedType = (ParameterizedType) type;
				System.out.println("vos " + i + "th  implemented interface parameter argment is " + parameterizedType.getActualTypeArguments()[0]);
				System.out.println("owner type is "  + parameterizedType.getOwnerType());
				System.out.println("raw type is " + parameterizedType.getRawType());
				Class rawtype = (Class) parameterizedType.getRawType();
				System.out.println("raw type class is " + rawtype.getName());
				System.out.println("type name is " + parameterizedType.getTypeName());
			}
		}
	}
}
