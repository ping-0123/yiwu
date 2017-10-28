package com.test.main;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.view.CourseConnotationVO;
import com.yinzhiwu.yiwu.model.view.CourseVO;
import com.yinzhiwu.yiwu.model.view.LessonVO;
import com.yinzhiwu.yiwu.model.view.LessonVO.LessonVOConverter;
import com.yinzhiwu.yiwu.service.CourseYzwService;

/**
*@Author ping
*@Time  创建时间:2017年10月14日下午2:47:13
*
*/

public class BeanUtilTest extends BaseSpringTest{
	
	@Autowired
	private CourseYzwService courseService;
	
	@Test
	public void testCopyProperty(){
		CourseVO courseVO = new CourseVO();
		CourseConnotationVO connotationVO  = new CourseConnotationVO();
		courseVO.setConnotation(connotationVO);
		
		System.err.println(courseVO.getConnotation().getHelpInfomation());
	}	
	
	@Test
	public void testMapedClassRatio(){
		String courseId = "20170826004";
		List<LessonYzw> lessons;
		try {
			lessons = courseService.get(courseId).getLessons();
		} catch (DataNotFoundException e1) {
			e1.printStackTrace();
			return;
		}
		List<LessonVO> mapedVos = new ArrayList<LessonVO>();
		List<LessonVO> apachiVos = new ArrayList<>();
		long start = System.currentTimeMillis();
		for(int i=0; i<10; i++){
			for (LessonYzw lesson : lessons) {
				LessonVO vo = LessonVOConverter.instance.fromPO(lesson);
				mapedVos.add(vo);
			}
		}
		
		long end = System.currentTimeMillis();
		System.err.println("Maped Class Utils used time is " + (end - start));
		System.err.println("Maped vos size is " + mapedVos.size());
		
		long apachiStart = System.currentTimeMillis();
		for(int i=0;i<10;i++){
			for (LessonYzw lesson : lessons) {
				LessonVO vo = new LessonVO();
				try {
					BeanUtils.copyProperties(vo, lesson);
				} catch (IllegalAccessException | InvocationTargetException e) {
					logger.error(e);
					e.printStackTrace();
				}
				apachiVos.add(vo);
			}
		}
		
		long apachiEnd = System.currentTimeMillis();
		System.err.println("Apachi BeanUtils used time is " + (apachiEnd-apachiStart));
		System.err.println("Apachi BeanUtils vos size is " + apachiVos.size());
	}
}
