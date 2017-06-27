package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.AppointmentDao;
import com.yinzhiwu.springmvc3.dao.CheckInsDao;
import com.yinzhiwu.springmvc3.dao.ClassRoomDao;
import com.yinzhiwu.springmvc3.dao.CourseDao;
import com.yinzhiwu.springmvc3.dao.CustomerDao;
import com.yinzhiwu.springmvc3.dao.LessonDao;
import com.yinzhiwu.springmvc3.dao.OrderDao;
import com.yinzhiwu.springmvc3.dao.StoreManCallRollDao;
import com.yinzhiwu.springmvc3.dao.TeacherCallRollDao;
import com.yinzhiwu.springmvc3.entity.yzwOld.ClassRoom;
import com.yinzhiwu.springmvc3.entity.yzwOld.Course;
import com.yinzhiwu.springmvc3.entity.yzwOld.Customer;
import com.yinzhiwu.springmvc3.entity.yzwOld.Lesson;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.LessonList;
import com.yinzhiwu.springmvc3.model.LessonOldApiView;
import com.yinzhiwu.springmvc3.model.LessonOldApiView.CheckedInStatus;
import com.yinzhiwu.springmvc3.service.LessonService;
import com.yinzhiwu.springmvc3.util.CalendarUtil;


/**
 * 
 * @author ping
 *	星期一为第一天
 */
@Service
public class LessonServiceImplTwo extends BaseServiceImpl<Lesson, Integer>  implements LessonService {
	
	public static Log logger = LogFactory.getLog(LessonServiceImplTwo.class);
	
	@Autowired private LessonDao lessonDao;
	@Autowired private ClassRoomDao roomDao;
	@Autowired private AppointmentDao appointedDao;
	@Autowired private CourseDao courseDao;
	@Autowired private OrderDao orderDao;
	@Autowired private CheckInsDao checkInsDao;
	@Autowired private StoreManCallRollDao scrDao;
	@Autowired private TeacherCallRollDao tcrDao;
	
	@Autowired
	@Qualifier("customerDaoImpl")
	private CustomerDao customerDao;
	
	@Autowired
	public void setBaseDao(LessonDao lessonDao){
		super.setBaseDao(lessonDao);
	}
	
	@Override
	public Lesson findById(int lessonId) {
		try {
			return lessonDao.findById(lessonId);
		} catch (DataNotFoundException e) {
			logger.warn(e.getMessage());
			return null;
		}
	}
	
	
	private List<LessonList> wrapLessonWeekList(List<LessonOldApiView> l, Date start){
		
		List<LessonList> list = new ArrayList<>();
		Calendar ca = Calendar.getInstance();
		ca.setTime(start);
		for(int i = 2; i<=8; i++){
			list.add( new LessonList(
						new java.sql.Date(ca.getTimeInMillis()),
						i<=7?i:i/7, 
						new ArrayList<>()));
			ca.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		for (LessonOldApiView miniLesson : l) {
			for(int j= 0; j<list.size(); j++)
			{
				if (miniLesson.getWeek()==list.get(j).getWeekday()){
					list.get(j).getList().add(miniLesson);
					break;
				}
			}
			
		}
		
		 return list;
		
	}

	@Override
	public List<LessonList> findLessonWeekList(int storeId, String courseType, String teacherName, String danceCatagory,
			Date date , String wechat) {
		
		Customer c = null;
		try {
			c = customerDao.findByWeChat(wechat);
		} catch (DataNotFoundException e) {
			logger.debug(e.getStackTrace());
		}
		
		//获取周日到周六所对应的日期
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int weekday = ca.get(Calendar.DAY_OF_WEEK);
		if(weekday>=Calendar.MONDAY)
			ca.add(Calendar.DAY_OF_WEEK, Calendar.MONDAY-weekday);
		else
			ca.add(Calendar.DAY_OF_WEEK, Calendar.MONDAY-weekday-7);
		Date startDate = ca.getTime();
		ca.add(Calendar.DAY_OF_WEEK, 6);
		Date endDate = ca.getTime();
		List<LessonOldApiView> views = new ArrayList<>();
		
		List<Lesson> lessons = new ArrayList<>();
		try {
			lessons = lessonDao.findLessonWeekList(
					storeId, courseType, teacherName, danceCatagory, startDate, endDate);
		} catch (DataNotFoundException e) {
			logger.warn(e.getMessage());
		}
		
		if(lessons.size() != 0){
			for (Lesson l : lessons) {
				LessonOldApiView view = _wrap_to_api_view(c, l);
				views.add(view);
			}
		}
			
		return wrapLessonWeekList(views, startDate);
	}

	private LessonOldApiView _wrap_to_api_view(Customer c, Lesson l) {
        logger.debug("start wrap lesson + " + l.getLessonDesc());
		
		
		LessonOldApiView view = new LessonOldApiView(l);
		//添加最大预约人数
		if(null != l.getClassRoomId() && "" != l.getClassRoomId()){
			ClassRoom room = roomDao.findById(l.getClassRoomId());
			if (room != null)
				view.setMaxStudentCount(room.getMaxStudentCount());
		}
		
		//添加舞种，舞种等级
		Course course;
		try {
			course = courseDao.findById(l.getCourseid());
			view.setDanceName(course.getDanceDesc());
			view.setDanceGrade(course.getDanceGrade());
		} catch (DataNotFoundException e) {
			logger.warn(e.getMessage());
		}
		
		//添加封闭式课程的上课人数
		if("封闭式".equals(l.getCourseType()) && l.getCourseid() != null){
			view.setAttendedStudentCount(orderDao.findAttendedStudentCount(l.getCourseid()));
		}
		//添加当前预约人数
		if(l.getCourseType().equals("开放式"))
			view.setAppointedStudentCount(appointedDao.getAppointedStudentCount(l.getLessonId()));
		
		//添加预约状态
		if(c != null){
			if("开放式".equals(l.getCourseType()))
				view.setAttendedStatus(appointedDao.findStatus(l.getLessonId(), c.getId()));
		}
		
		// 添加签到人数
		view.setCheckedInsStudentCount(
				checkInsDao.findCheckedInStudentCountByLessonId(l.getLessonId().toString()));
				// checkInsDao.findCountByProperty("lessonId", l.getLessonId().toString()));
		
		
		if ("封闭式".equals(l.getCourseType())){
		//添加店员点名人数
			view.setStoreManCallRollCount(
					scrDao.findCountByProperty("lessonId", l.getLessonId().toString()) );
			
		//添加老师点名人数
			view.setTeacherCallRollCount(
					tcrDao.findCountByProperty("lessonId", l.getLessonId()));
		}
		
		//添加总课次和当前上课进度
		view.setSumTimesOfCourse(lessonDao.findCountByProperty("courseid", l.getCourseid()));
		view.setOrderInCourse(lessonDao.findOrderInCourse(l));
		
		//添加刷卡状态
		if  ((l.getLessonDate().compareTo(CalendarUtil.getTodayBegin().getTime()) >=0 )){
			if ("未审核".equals(l.getLessonStatus())|| l.getLessonDate()==null || "".equals(l.getLessonStatus())) {
				view.setCheckedInStatus(CheckedInStatus.UN_CHECKED);
			}else
				view.setCheckedInStatus(CheckedInStatus.UN_KNOWN);
		}

		try {
			Date checkedInTime = checkInsDao.findByProperties(
					new String[]{"lessonId","teacherId"}, 
					new Object[]{l.getLessonId().toString(), l.getActualTeacherId()})
				.get(0).getCreateTime();
			logger.debug("start test checkin time of lesson" + l.getLessonId() + " " + l.getLessonDesc());
			logger.debug("the time of coach check in :" + checkedInTime);
			Calendar end = Calendar.getInstance();
			end.setTime(l.getLessonDate());
			Calendar endTime = Calendar.getInstance();
			endTime.setTime(l.getEndTime());
			end.set(end.get(Calendar.YEAR), 
					end.get(Calendar.MONTH),
					end.get(Calendar.DAY_OF_MONTH), 
					endTime.get(Calendar.HOUR_OF_DAY), 
					endTime.get(Calendar.MINUTE),
					endTime.get(Calendar.SECOND));
//			end.setTimeInMillis(l.getLessonDate().getTime()  + l.getEndTime().getTime());
			logger.debug("lesson date is: " + l.getLessonDate());
			logger.debug("lesson end time is " + l.getEndTime());
			logger.debug("The end Time of lesson:" + end.getTime());
			//如果刷卡时间比课程结束时间大 则是补刷
			if(checkedInTime.compareTo(end.getTime()) >=0){
				view.setCheckedInStatus(CheckedInStatus.PATCHED);
			}else
				view.setCheckedInStatus(CheckedInStatus.CHECKED);
		} catch (DataNotFoundException e) {
			view.setCheckedInStatus(CheckedInStatus.UN_CHECKED);
		}
	
		return view;
	}


	@Override
	public Integer save(Lesson lesson) {
		lesson.setCreateTime(new Date());
		lesson.setCreateUserId(lesson.getDueTeacherId());
		return lessonDao.save(lesson);
	}


	
}