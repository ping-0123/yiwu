package com.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;

import javax.persistence.OneToMany;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.yinzhiwu.springmvc3.entity.Distributer;

@RunWith(BlockJUnit4ClassRunner.class)
public class AnnotationTest {

	private  int i = 0;
	
	@Test
	public void testGetAnnotations(){
		Field[] fields = Distributer.class.getDeclaredFields();
		System.out.println(fields.length);
		int i=0;
		for (Field field : fields) {
			field.setAccessible(true);
			Annotation[] annotations = field.getAnnotations();
			for (Annotation annotation : annotations) {
				System.out.println(annotation.annotationType().getSimpleName());
			}
			System.out.println(i++);
		}
	}
	
	@Test
	public void test(){
		Field[] fields = Distributer.class.getDeclaredFields();
		for (Field field : fields) {
			AnnotatedType annotatedType = field.getAnnotatedType();
			System.out.println(field.getDeclaredAnnotation(OneToMany.class));
			System.out.println(field.getAnnotationsByType(OneToMany.class));
			System.out.println(i++);
		}
	}
}
