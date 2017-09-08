package com.test.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.TestBase;
import com.yinzhiwu.yiwu.dao.CheckInsYzwDao;

/**
*@Author ping
*@Time  创建时间:2017年9月8日下午5:29:55
*
*/

public class CheckInsYzwDaoTest extends TestBase {

	@Autowired
	private CheckInsYzwDao checkInsDao;
	
	@Test
	public void testFindSumHoursOfCheckedLessons(){
		String contractNo = "YZW20170812064";
		float hours = checkInsDao.findSumHoursOfCheckedLessonsByContractNo(contractNo);
		System.out.println(hours);
	}
}
