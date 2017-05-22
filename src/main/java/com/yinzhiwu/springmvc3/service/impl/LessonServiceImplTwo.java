package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.springmvc3.dao.AppointmentDao;
import com.yinzhiwu.springmvc3.dao.CheckInsDao;
import com.yinzhiwu.springmvc3.dao.ClassRoomDao;
import com.yinzhiwu.springmvc3.dao.ClassRoomDaoImpl;
import com.yinzhiwu.springmvc3.dao.CourseDao;
import com.yinzhiwu.springmvc3.dao.CustomerDao;
import com.yinzhiwu.springmvc3.dao.LessonDao;
import com.yinzhiwu.springmvc3.dao.OrderDao;
import com.yinzhiwu.springmvc3.dao.StoreManCallRollDao;
import com.yinzhiwu.springmvc3.dao.TeacherCallRollDao;
import com.yinzhiwu.springmvc3.dao.impl.CustomerDaoImpl;
import com.yinzhiwu.springmvc3.dao.impl.LessonDaoImpl;
import com.yinzhiwu.springmvc3.entity.ClassRoom;
import com.yinzhiwu.springmvc3.entity.Course;
import com.yinzhiwu.springmvc3.entity.Customer;
import com.yinzhiwu.springmvc3.entity.Lesson;
import com.yinzhiwu.springmvc3.model.LessonList;
import com.yinzhiwu.springmvc3.model.MiniLesson;
import com.yinzhiwu.springmvc3.service.LessonService;


/**
 * 
 * @author ping
 *	星期一为第一天
 */
@Service
public class LessonServiceImplTwo extends BaseServiceImpl<Lesson, Integer>  implements LessonService {
	
	public static Log logger = LogFactory.getLog(LessonServiceImplTwo.class);
	
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
	
	@Autowired
	private CheckInsDao checkInsDao;
	
	@Autowired
	private StoreManCallRollDao scrDao;
	
	@Autowired
	private TeacherCallRollDao tcrDao;
	
	@Autowired
	@Qualifier("customerDaoImpl")
	private CustomerDao customerDao;
	
	@Autowired
	public void setBaseDao(LessonDao lessonDao){
		super.setBaseDao(lessonDao);
	}
	
	@Override
	public Lesson findById(int lessonId) {
		return lessonDao.findById(lessonId);
	}
	
	
	private List<LessonList> wrapLessonWeekList(List<MiniLesson> l, Date start){
		
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
			Date date , String wechat) {
		
		Customer c = null;
		try {
			c = customerDao.findByWeChat(wechat);
			logger.info(c.getName());
//			logger.info("customerDao's current session hashcode" + ((CustomerDaoImpl) customerDao).getSessionFactory().getCurrentSession().hashCode());
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
		
		logger.info("lessonDao's seesion factory hashcode" + ((LessonDaoImpl) lessonDao).getSessionFactory().hashCode());
//		logger.debug("lessonDao's seesion hashcode" + ((LessonDaoImpl) lessonDao).getSessionFactory().getCurrentSession().hashCode());
		List<Lesson> list = lessonDao.findLessonWeekList(
				storeId, courseType, teacherName, danceCatagory, startDate, endDate);
		List<MiniLesson> lm = new ArrayList<>();
		for (Lesson l : list) {
			MiniLesson ml = new MiniLesson(l);
			//添加最大预约人数
			if(null != l.getClassRoomId() && "" != l.getClassRoomId()){
				logger.info("roomDao's session factory hashCode" + ((ClassRoomDaoImpl) roomDao).getSessionFactory().hashCode());
//				logger.debug("roomDao's session hashCode" + ((ClassRoomDaoImpl) roomDao).getSessionFactory().getCurrentSession().hashCode());
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
			
			// 添加签到人数
			ml.setCheckedInsStudentCount(
					checkInsDao.findCheckedInStudentCountByLessonId(l.getLessonId().toString()));
					// checkInsDao.findCountByProperty("lessonId", l.getLessonId().toString()));
			
			
			if ("封闭式".equals(l.getCourseType())){
			//添加店员点名人数
				ml.setStoreManCallRollCount(
						scrDao.findCountByProperty("lessonId", l.getLessonId().toString()) );
				
			//添加老师点名人数
				ml.setTeacherCallRollCount(
						tcrDao.findCountByProperty("lessonId", l.getLessonId()));
			}
			
			
			lm.add(ml);
			
		}
		
		return wrapLessonWeekList(lm, startDate);
	}


	@Override
	public Integer save(Lesson lesson) {
		lesson.setCreateTime(new Date());
		lesson.setCreateUserId(lesson.getDueTeacherId());
		return lessonDao.save(lesson);
	}


	
}