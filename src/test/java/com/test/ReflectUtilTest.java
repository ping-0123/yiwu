package com.test;

import java.lang.reflect.Field;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.util.ReflectUtil;

@RunWith(BlockJUnit4ClassRunner.class)
public class ReflectUtilTest {

	@Test
	public void testDistributerClass(){
		long start = System.currentTimeMillis();
		Field[] fields = ReflectUtil.getAllFields(Distributer.class);
		long end = System.currentTimeMillis();
		System.out.println("所花时间: " + (end-start));
		System.out.println("类的所有属性:" + fields.length);
		for (Field field : fields) {
			System.out.println(field.getName());
		}
	}
}
