package com.yinzhiwu.yiwu.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.service.CourseYzwService;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月9日 上午12:08:43
*
*/

@Service
public class CourseScheduleService {
	@Autowired private CourseYzwService courseService;
	
	@Scheduled(fixedRate=10, fixedDelay=10000)
	public void  setStudentCountAndSumLessonTimesAndLessonOrdialNo(){
		courseService.scheduledSetOne_StudentCount_SumLessonTimesAndLessonOrdialNo();
	}
}
