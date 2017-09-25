package com.test.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.yinzhiwu.yiwu.entity.yzw.PostYzw;
import com.yinzhiwu.yiwu.util.ReflectUtils;

/**
*@Author ping
*@Time  创建时间:2017年9月25日下午4:57:58
*
*/

@RunWith(BlockJUnit4ClassRunner.class)
public class IncreaseTest {
	
	@Test
	public void test(){
		int j=0;
		for(int i=0; i<10; i++)
			System.out.println(j++);
	}
	
	
	@Test
	public void testGetField(){
		try {
			System.out.println(ReflectUtils.getField(PostYzw.class, "id"));
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
