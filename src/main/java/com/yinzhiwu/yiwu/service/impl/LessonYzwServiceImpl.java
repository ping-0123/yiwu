package com.yinzhiwu.yiwu.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.yinzhiwu.yiwu.dao.LessonCheckInYzwDao;
import com.yinzhiwu.yiwu.dao.LessonYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonConnotation;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw.CheckedInStatus;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw.LessonStatus;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.model.view.LessonForWeeklyVO;
import com.yinzhiwu.yiwu.model.view.LessonForWeeklyVO.LessonForWeeklyVOConverter;
import com.yinzhiwu.yiwu.model.view.OneDayLessonsVO;
import com.yinzhiwu.yiwu.model.view.PrivateLessonApiView;
import com.yinzhiwu.yiwu.service.FileService;
import com.yinzhiwu.yiwu.service.LessonYzwService;
import com.yinzhiwu.yiwu.util.CalendarUtil;

@Service
public class LessonYzwServiceImpl extends BaseServiceImpl<LessonYzw, Integer> implements LessonYzwService {

	@Autowired private LessonYzwDao lessonDao;
	@Qualifier(value="fileServiceImpl")
	@Autowired private FileService fileService;
	@Autowired private LessonCheckInYzwDao lessonCheckinDao;
	
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
	public LessonConnotation getLastNLessonConnotation(int thisLessonId, int lastN) throws Exception {
		LessonYzw thislesson = lessonDao.get(thisLessonId);
		LessonYzw lesson = lessonDao.findLastNLesson(thislesson, lastN);
		if (lesson != null)
			return lesson.getConnotation();
		return null;
	}

	@Override
	public List<OneDayLessonsVO> findWeeklyLessons(Integer storeId, CourseType courseType, String teacherName, String danceCatagory,
			Date date) {
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
		
		List<LessonForWeeklyVO> vos = new ArrayList<>();
		List<LessonYzw> lessons = lessonDao.findWeeklyLessons(storeId, courseType, teacherName,
				danceCatagory,start, end);
		for (LessonYzw lesson : lessons) {
			LessonForWeeklyVO vo = LessonForWeeklyVOConverter.INSTANCE.fromPO(lesson);
			//必要时记录教练的签到状态
			if(StringUtils.hasLength(teacherName))
				vo.setCoachCheckedInStatus(getCoachCheckinStatus(lesson));
			vos.add(vo);
		}
		
		return _wrapToOneDayLessonsVOs(vos,start);
	}

	private List<OneDayLessonsVO> _wrapToOneDayLessonsVOs(List<LessonForWeeklyVO> vos, Date start){
		List<OneDayLessonsVO> oneDays = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		for(int i=2; i<=8; i++){
			oneDays.add(new OneDayLessonsVO(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_WEEK, 1);
		}
		
		for(LessonForWeeklyVO vo: vos){
			for(int j =0; j<vos.size(); j++){
				if(vo.getWeekDay() == oneDays.get(j).getWeekday()){
					oneDays.get(j).getList().add(vo);
					break;
				}
			}
		}
		
		return oneDays;
	}
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		System.err.println("current date is " + calendar.getTime());
		System.err.println(calendar.get(Calendar.DAY_OF_WEEK));
	}
	
	@Override
	public YiwuJson<List<PrivateLessonApiView>> findPrivateLessonApiViewsByContracNo(String contractNo) {
		List<PrivateLessonApiView> views = lessonDao.findPrivateLessonApiViewsByContracNo(contractNo);
		if(views.size() == 0)
			return YiwuJson.createByErrorMessage("未找到会籍合约为" + contractNo + "的私教课");
		return YiwuJson.createBySuccess(views);
	}

	@Override
	public YiwuJson<PageBean<LessonApiView>> findPageOfClosedLessonApiViewByStoreIdAndLessonDate(Integer storeId,
			Date date, int pageNo, int pageSize) {
		PageBean<LessonApiView> page = lessonDao.findPageOfClosedLessonApiViewByStoreIdAndLessonDate(storeId ,date, pageNo, pageSize);
		if(page.getList().size() ==0)
			return YiwuJson.createByErrorMessage("未找到封闭式课程");
		//设置pictureURL
		for (LessonApiView view : page.getList()) {
			view.setPictureUrl(fileService.getFileUrl(view.getPictureUrl()));
		}
		return YiwuJson.createBySuccess(page);
	}


	@Override
	public LessonYzw findComingLessonByCourseId(String courseId) {
		Calendar calendar = Calendar.getInstance();
		Date start =calendar.getTime();
		Date end = CalendarUtil.getDayEnd(calendar).getTime();
		return lessonDao.findByCourseIdAndStartDateTime(courseId, start,end);
	}

	
	@Override
	public  void setConnatationUrls(LessonConnotation con){
		Assert.notNull(con);
		
		con.setAudioUri(fileService.getFileUrl(con.getAudioUri()));
		con.setPictureUri(fileService.getFileUrl(con.getPictureUri()));
		con.setStandardVideoPosterUri(fileService.getFileUrl(con.getStandardVideoUri()));
		con.setPracticalVideoUri(fileService.getFileUrl(con.getPracticalVideoUri()));
		con.setPuzzleVideoUri(fileService.getFileUrl(con.getPuzzleVideoUri()));
	}

	@Override
	public LessonYzw findByCourseIdAndOrdinalNo(String courseId, Integer ordinalNo) throws DataNotFoundException {
		Assert.hasLength(courseId);
		Assert.notNull(ordinalNo);
		
		return lessonDao.findByCourseIdAndOrdinalNo(courseId, ordinalNo);
	}
	
	
	
	@EventListener(classes={LessonAppointmentYzw.class})
	public void handleLessonAppointment(LessonAppointmentYzw appointment){
		LessonYzw lesson = appointment.getLesson();
		int appointedStudentCount = lesson.getAppointedStudentCount()==null? 0:lesson.getAppointedStudentCount();
		switch (appointment.getStatus()) {
		case APPONTED:
			appointedStudentCount++;
			break;
		case UN_APOINTED:
			appointedStudentCount--;
			break;
		default:
			return;
		}
		lesson.setAppointedStudentCount(appointedStudentCount);
		
		update(lesson);
	}
	
	@EventListener(classes={LessonCheckInYzw.class})
	public void handleLessonCheckIn(LessonCheckInYzw checkIn){
		LessonYzw lesson = checkIn.getLesson();
		int currentCount = null==lesson.getCheckedInStudentCount()? 0: lesson.getCheckedInStudentCount();
		lesson.setCheckedInStudentCount(++currentCount);
		update(lesson);
	}
	
	
	@Override
	public List<LessonYzw> findOpenedLessonsOfYesterday() {
		return lessonDao.findOpenedLessonsOfYesterday();
	}

	@Override
	public CheckedInStatus getCoachCheckinStatus(LessonYzw lesson) {
		if(LessonStatus.UN_CHECKED == lesson.getLessonStatus() )
			return CheckedInStatus.NON_CHECKABLE;
		if(null == lesson.getActualTeacher())
			return CheckedInStatus.UN_CHECKED;
		LessonCheckInYzw checkin;
		try {
			checkin = lessonCheckinDao.findByLessonIdAndCoachId(lesson.getId(), lesson.getActualTeacher().getId());
		} catch (DataNotFoundException e) {
			throw new RuntimeException("数据有误，教练未签到却记录了课时");
		}
		if(checkin.getCreateTime().before(lesson.getStartDateTime()))
			return CheckedInStatus.CHECKED;
		else {
			return CheckedInStatus.PATCHED;
		}
	}
	
	
	
}
