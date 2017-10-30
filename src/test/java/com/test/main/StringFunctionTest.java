package com.test.main;

import org.junit.Test;

import com.test.BaseBlockJUnitTest;

/**
*@Author ping
*@Time  创建时间:2017年10月27日下午3:52:27
*
*/

public class StringFunctionTest extends BaseBlockJUnitTest{

	@Test
	public void testSplit(){
		String source = ";66";
		String[] strings = source.split(";");
		System.err.println(strings.length);
		for (String string : strings) {
			System.err.println(string);
		}
	}
}
