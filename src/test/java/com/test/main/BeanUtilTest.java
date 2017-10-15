package com.test.main;

import org.junit.Test;

import com.test.BaseBlockJUnitTest;
import com.yinzhiwu.yiwu.model.view.CourseConnotationVO;
import com.yinzhiwu.yiwu.model.view.CourseVO;

/**
*@Author ping
*@Time  创建时间:2017年10月14日下午2:47:13
*
*/

public class BeanUtilTest extends BaseBlockJUnitTest{
	
	@Test
	public void testCopyProperty(){
		CourseVO courseVO = new CourseVO();
		CourseConnotationVO connotationVO  = new CourseConnotationVO();
		courseVO.setConnotation(connotationVO);
		String uri = "aaaaaaaaaaa";
		
		System.err.println(courseVO.getConnotation().getHelpInfomation());
	}
}
