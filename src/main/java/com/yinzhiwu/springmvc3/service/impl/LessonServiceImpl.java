package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.AppointmentDao;
import com.yinzhiwu.springmvc3.dao.ClassRoomDao;
import com.yinzhiwu.springmvc3.dao.CourseDao;
import com.yinzhiwu.springmvc3.dao.LessonDao;
import com.yinzhiwu.springmvc3.dao.OrderDao;
import com.yinzhiwu.springmvc3.entity.ClassRoom;
import com.yinzhiwu.springmvc3.entity.Course;
import com.yinzhiwu.springmvc3.entity.Customer;
import com.yinzhiwu.springmvc3.entity.Lesson;
import com.yinzhiwu.springmvc3.model.LessonList;
import com.yinzhiwu.springmvc3.model.MiniLesson;
import com.yinzhiwu.springmvc3.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {
	
	@Autowired
	private LessonDao lessonDao;
	
	@Autowired
	private ClassRoomDao roomDao;
	
	@Autowired
	private AppointmentDao appointedDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public Lesson findById(int lessonId) {
		return lessonDao.findById(lessonId);
	}
	
	
	private List<LessonList> wrapLessonWeekList(List<MiniLesson> l, Date start){
		
		List<LessonList> list = new ArrayList<>();
		Calendar ca = Calendar.getInstance();
		ca.setTime(start);
		for(int i = 1; i<=7; i++){
//			list.add(new LessonList(ca.getTime(), i, new ArrayList<>()));
			list.add( new LessonList(
						new java.sql.Date(ca.getTimeInMillis()),
						i, new ArrayList<>()));
			ca.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		for (MiniLesson miniLesson : l) {
			for(int j= 0; j<list.size(); j++)
			{
				if (miniLesson.getWeek()==list.get(j).getWeekday()){
//					list.get(j).setDate(miniLesson.getLessonDate());
					list.get(j).getList().add(miniLesson);
					break;
				}
			}
			
		}
		
		 return list;
		
	}

	@Override
	public List<LessonList> findLessonWeekList(int storeId, String courseType, String teacherName, String danceCatagory,
			Date date , Customer c) {
		
		//获取周日到周六所对应的日期
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int weekday = ca.get(Calendar.DAY_OF_WEEK);
		ca.add(Calendar.DAY_OF_WEEK, Calendar.SUNDAY-weekday);
		Date startDate = ca.getTime();
		ca.add(Calendar.DAY_OF_WEEK, Calendar.SATURDAY-Calendar.SUNDAY);
		Date endDate = ca.getTime();
		
		
		List<Lesson> list = lessonDao.findLessonWeekList(
				storeId, courseType, teacherName, danceCatagory, startDate, endDate);
		List<MiniLesson> lm = new ArrayList<>();
		for (Lesson l : list) {
			MiniLesson ml = new MiniLesson(l);
			//添加最大预约人数
			if(null != l.getClassRoomId() && "" != l.getClassRoomId()){
				ClassRoom room = roomDao.findById(l.getClassRoomId());
				if (room != null)
					ml.setMaxStudentCount(room.getMaxStudentCount());
			}
			
			//添加舞种，舞种等级
			Course course = courseDao.findById(l.getCourseid());
			ml.setDanceName(course.getDanceDesc());
			ml.setDanceGrade(course.getDanceGrade());
			
			//添加封闭式课程的上课人数
			if("封闭式".equals(l.getCourseType()) && l.getCourseid() != null){
				ml.setAttendedStudentCount(orderDao.findAttendedStudentCount(l.getCourseid()));
			}
			//添加当前预约人数
			if(l.getCourseType().equals("开放式"))
				ml.setAppointedStudentCount(appointedDao.getAppointedStudentCount(l.getLessonId()));
			
			//添加预约状态
			if(c != null){
				if("开放式".equals(l.getCourseType()))
					ml.setAttendedStatus(appointedDao.findStatus(l.getLessonId(), c.getId()));
			}
			lm.add(ml);
			
		}
		
		return wrapLessonWeekList(lm, startDate);
	}


	@Override
	public void save(Lesson lesson) {
		lessonDao.save(lesson);
	}


	
}