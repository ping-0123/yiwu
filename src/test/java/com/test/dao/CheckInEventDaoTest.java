package com.test.dao;

import java.lang.reflect.Field;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.dao.CheckInEventDao;
import com.yinzhiwu.yiwu.model.view.CheckInSuccessApiView;
import com.yinzhiwu.yiwu.util.ReflectUtils;

/**
*@Author ping
*@Time  创建时间:2017年8月30日下午2:39:18
*
*/

public class CheckInEventDaoTest extends BaseSpringTest{
	
	@Autowired private CheckInEventDao checkInEventDao;
	
	@Test
	public void testfindCheckInSuccessApiViewById(){
		int eventId = 5004600;
		long start = System.currentTimeMillis();
		CheckInSuccessApiView view = checkInEventDao.findCheckInSuccessApiViewById(eventId);
		long end = System.currentTimeMillis();
		System.err.println("查询执行时间是: " + (end-start));
		if(view !=null){
			Field[] fields = ReflectUtils.getAllFields(CheckInSuccessApiView.class);
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					System.err.println(field.getName() + ": " +  field.get(view));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
