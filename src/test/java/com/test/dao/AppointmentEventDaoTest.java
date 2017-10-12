package com.test.dao;

import java.lang.reflect.Field;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.dao.AppointmentEventDao;
import com.yinzhiwu.yiwu.model.view.AppointSuccessApiView;
import com.yinzhiwu.yiwu.util.ReflectUtils;

/**
*@Author ping
*@Time  创建时间:2017年8月30日下午4:16:27
*
*/

public class AppointmentEventDaoTest  extends BaseSpringTest{

	@Autowired private AppointmentEventDao dao;
	
	@Test
	public void testFindApiView(){
		int eventId = 5006433;
		long start = System.currentTimeMillis();
		AppointSuccessApiView view = dao.findAppointSuccessApiViewById(eventId);
		long end = System.currentTimeMillis();
		System.err.println("查询花费时间是: " + (end-start));
		if(view != null){
			Field[] fields = ReflectUtils.getAllFields(AppointSuccessApiView.class);
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					System.err.println(field.getName() + " : " + field.get(view));
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
