package com.yinzhiwu.yiwu.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.AppointmentYzwDao;
import com.yinzhiwu.yiwu.dao.CheckInsYzwDao;
import com.yinzhiwu.yiwu.dao.CustomerYzwDao;
import com.yinzhiwu.yiwu.dao.LessonYzwDao;
import com.yinzhiwu.yiwu.dao.StoreManCallRollYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.Connotation;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.model.DailyLessonsDto;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.model.view.LessonForWeeklyDto;
import com.yinzhiwu.yiwu.service.LessonYzwService;

@Service
public class LessonYzwServiceImpl extends BaseServiceImpl<LessonYzw, Integer> implements LessonYzwService {

	@Autowired
	private LessonYzwDao lessonDao;
	@Autowired private AppointmentYzwDao appointmentYzwDao;
	@Autowired private CustomerYzwDao customerYzwDao;
	@Autowired private StoreManCallRollYzwDao storeManCallRollYzwDao;
	@Autowired private CheckInsYzwDao checkInsYzwDao;
	
	@Autowired
	public void setBaseDao(LessonYzwDao lessonDao) {
		super.setBaseDao(lessonDao);
	}

	@Override
	public YiwuJson<List<LessonApiView>> findByCourseId(String courseId) {
		List<LessonYzw> lessons = lessonDao.findByCourseId(courseId);
		if (lessons == null || lessons.size() == 0)
			return new YiwuJson<>("no lessons found by courseId: " + courseId);
		List<LessonApiView> views = new ArrayList<>();
		for (LessonYzw l : lessons) {
			views.add(new LessonApiView(l));
		}
		return new YiwuJson<>(views);
	}

	@Override
	public List<LessonApiView> findApiViewByCourseId(String courseId) {
		return lessonDao.findApiViewsByCourseId(courseId);
	}

	@Override
	public LessonYzw getLastNLesson(LessonYzw thisLesson, int lastN) {
		return lessonDao.findLastNLesson(thisLesson, lastN);
	}

	@Override
	public Connotation getLastNLessonConnotation(int thisLessonId, int lastN) throws Exception {
		LessonYzw thislesson = lessonDao.get(thisLessonId);
		LessonYzw lesson = lessonDao.findLastNLesson(thislesson, lastN);
		if (lesson != null)
			return lesson.getConnotation();
		return null;
	}

	@Override
	public List<DailyLessonsDto> findWeeklyLessons(int storeId, CourseType courseType, String teacherName, String danceCatagory,
			Date date, String weChat) {
		CustomerYzw customer = null;
		if(weChat != null){
			customer = customerYzwDao.findByWeChat(weChat);
		}
		//获取一周的开始时间和结束时间   星期一为一周开始
		Date start, end;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekday= calendar.get(Calendar.DAY_OF_WEEK);
		if(weekday >= Calendar.MONDAY)
			calendar.add(Calendar.DAY_OF_WEEK, Calendar.MONDAY-weekday);
		else {
			calendar.add(Calendar.DAY_OF_WEEK, Calendar.MONDAY-weekday -7);
		}
		start = calendar.getTime();
		calendar.add(Calendar.DAY_OF_WEEK, 6);
		end = calendar.getTime();
		
		List<LessonForWeeklyDto> dtos = new ArrayList<>();
//		dtos = lessonDao.findWeeklyLessons(storeId, courseType, teacherName, danceCatagory, start, end);
		List<LessonYzw> lessons = lessonDao.findWeeklyLessons(storeId, courseType, teacherName,
				danceCatagory,start, end);
		for (LessonYzw lesson : lessons) {
			dtos.add(_wrapToLessonForWeeklyDto(lesson, customer));
		}
		
		return _wrapToWeeklyLessons(dtos,start);
	}

	private List<DailyLessonsDto> _wrapToWeeklyLessons(List<LessonForWeeklyDto> dtos, Date start){
		List<DailyLessonsDto> list = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		for(int i=2; i<=8; i++){
			list.add(new DailyLessonsDto(
					calendar.getTime(),
					i<=7?i:i%7, 
					new ArrayList<>()));
			calendar.add(Calendar.DAY_OF_WEEK, 1);
		}
		
		for(LessonForWeeklyDto dto: dtos){
			for(int j =0; j<dtos.size(); j++){
				if(dto.getWeek() == list.get(j).getWeekday()){
					list.get(j).getList().add(dto);
					break;
				}
			}
		}
		
		return list;
	}
	private LessonForWeeklyDto _wrapToLessonForWeeklyDto(LessonYzw lesson, CustomerYzw customer) {
		Assert.notNull(lesson, "_wrapToLessonForWeeklyTableDto 中 lesson 不能为null");
		
		LessonForWeeklyDto dto = new LessonForWeeklyDto(lesson);
//		if(customer != null){
//			//TOD this.appointStatus
//			if(CourseType.OPENED == lesson.getCourseType()){
//				Long count = appointmentYzwDao.findCountByProperties(
//						new String[]{"lesson.id", "customer.id", "status"},
//						new Object[]{lesson.getId(), customer.getId(),AppointStatus.APPONTED });
//				if(count > 0)
//					dto.setAppointStatus(AppointStatus.APPONTED);
//				else
//					dto.setAppointStatus(AppointStatus.UN_APOINTED);
//			}
//			//TOD  老师的签到状态 this.checkedInStatus
//			//1.如果上课时间大于或等于当天， 则未知其刷卡状态
//			if(lesson.getActualTeacher() == null)
//			{
//				if(LessonStatus.UN_CHECKED == lesson.getLessonStatus())
//					dto.setCheckedInStatus(CheckedInStatus.NON_CHECKABLE);
//				else
//					dto.setCheckedInStatus(CheckedInStatus.UN_CHECKED);
//			}else {
//				//2.如果刷卡时间大于课程开始时间则是补刷卡
//				Date checkedInDate = checkInsYzwDao.findCheckInTimeByProperties(lesson.getId(), lesson.getActualTeacher().getId());
//				Date lessonStart = lesson.getStartDateTime();
//				if(checkedInDate.compareTo(lessonStart) >0)
//					dto.setCheckedInStatus(CheckedInStatus.PATCHED);
//				else
//					dto.setCheckedInStatus(CheckedInStatus.CHECKED);
//			}
//			
//				
//		}
//		//TOD this.appointedStudentCount = lesson.getAppointedStudentCount();
//		if(CourseType.OPENED == lesson.getCourseType())
//			dto.setAppointedStudentCount(appointmentYzwDao.findCountByProperties(
//					new String[]{"lesson.id", "status"}, 
//					new Object[]{lesson.getId(), AppointStatus.APPONTED})
//					.intValue());
//		//TOD storeManCallRollCount
//		dto.setStoreManCallRollCount(storeManCallRollYzwDao.findCountByProperties(
//				new String[]{"lessonId", "callRolled"}, 
//				new Object[]{lesson.getId().toString(),true})
//				.intValue());
//		//TOD  sumTimesOfCourse
//		dto.setSumTimesOfCourse(lessonDao.findCountByProperty(
//				"course.id", lesson.getCourse().getId())
//				.intValue());
//		//TOD  orderInCourse
//		dto.setOrderInCourse(lessonDao.findOrderInCourse(lesson));
//		
		return dto;
	}
}