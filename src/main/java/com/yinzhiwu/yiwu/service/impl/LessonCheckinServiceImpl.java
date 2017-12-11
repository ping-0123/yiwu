package com.yinzhiwu.yiwu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.yinzhiwu.yiwu.common.entity.search.SearchOperator;
import com.yinzhiwu.yiwu.common.entity.search.SearchRequest;
import com.yinzhiwu.yiwu.common.entity.search.Searchable;
import com.yinzhiwu.yiwu.dao.CustomerYzwDao;
import com.yinzhiwu.yiwu.dao.LessonCheckInYzwDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.LessonInteractive;
import com.yinzhiwu.yiwu.entity.yzw.ClassRoomYzw;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw.SettleStatus;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw.CheckedInStatus;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw.LessonStatus;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.exception.DataConsistencyException;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.exception.business.LessonCheckInException;
import com.yinzhiwu.yiwu.exception.business.LessonInteractiveException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.service.LessonCheckinService;
import com.yinzhiwu.yiwu.service.LessonInteractiveService;
import com.yinzhiwu.yiwu.util.CalendarUtil;

@Service
public class LessonCheckinServiceImpl extends BaseServiceImpl<LessonCheckInYzw, Integer> implements LessonCheckinService {

	@Autowired
	private LessonCheckInYzwDao lessonCheckInDao;
	@Autowired private CustomerYzwDao customerDao;
	@Autowired private LessonInteractiveService lessonInteractiveService;
	@Autowired private OrderYzwDao orderDao;
	@Autowired private ApplicationContext applicationContext;
	
	@Autowired
	public void setBaseDao(LessonCheckInYzwDao checkInsYzwDao) {
		super.setBaseDao(checkInsYzwDao);
	}

	@Override
	public int findCountByCustomerId(int customerId) {
		return lessonCheckInDao.findCountByCustomerId(customerId);
	}

	@Override
	public int findCheckedInLessonsCountOfCustomer(int customerId) {
		String memberCard;
		try {
			memberCard = customerDao.get(customerId).getMemberCard();
		} catch (DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e);
		}
		return lessonCheckInDao.findCheckedInLessonsCountByMemeberCard(memberCard);
	}
	
	@Override
	public YiwuJson<List<LessonApiView>> findByCustomerId(int customerId) {
		String memberCard;
		try {
			memberCard = customerDao.get(customerId).getMemberCard();
		} catch (DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
		List<LessonApiView> lessonApiViews = lessonCheckInDao.findLessonApiViewsByMemeberCard(memberCard);
		return YiwuJson.createBySuccess(lessonApiViews);
	}

	@Override
	public PageBean<LessonApiView> findPageViewByCustomer(int customerId, Integer pageNo, Integer pageSize) {
		String memberCard;
		try {
			memberCard = customerDao.get(customerId).getMemberCard();
		} catch (DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			return null;
		}
		return lessonCheckInDao.findPageCheckedInLessonApiViewsByMemberCard(memberCard, pageNo, pageSize);

	}


	@Override
	public YiwuJson<PageBean<LessonApiView>> findPageOfCheckedInLessonApiViewsByContractNo(String contractNo,
			int pageNo, int pageSize) {
		PageBean<LessonApiView> page = lessonCheckInDao.findPageOfLessonApiViewsByContractNo(contractNo, pageNo, pageSize);
		return YiwuJson.createBySuccess(page);
	}

	@Override
	public LessonCheckInYzw doCheckIn(Distributer distributer, LessonYzw lesson) throws LessonCheckInException, LessonInteractiveException {
		if(null == distributer)
			throw new LessonCheckInException("签到人不能为空");
		if(null == lesson)
			throw new LessonCheckInException("签到的课程不能为空");
		
		//封闭式课不需要刷卡
		if(CourseType.CLOSED == lesson.getCourseType())
			throw new LessonCheckInException("封闭式课程无须刷卡");
		//刷卡时间判定
//		Calendar calendar = Calendar.getInstance();
//		Date current = calendar.getTime();
//		calendar.setTime(lesson.getStartDateTime());
//		calendar.add(Calendar.HOUR_OF_DAY, -1);
//		Date checkInStart = calendar.getTime();
//		if(current.before(checkInStart))
//			throw new LessonCheckInException("离上课时间还远,请在上课前1小时内刷卡");
		
/*   TODO 刷卡结束时间暂不做限制
 * 		Calendar checkInEndCalendar = Calendar.getInstance();
		checkInEndCalendar.setTime(lesson.getEndTime());
		calendar.set(Calendar.HOUR_OF_DAY, checkInEndCalendar.get(Calendar.HOUR_OF_DAY));
		Date checkInEnd = calendar.getTime();
		if(current.after(checkInEnd))
			throw new LessonCheckInException("上课已结束, 请在上课前1小时内刷卡");*/
		
		//判断是否已刷卡
		LessonInteractive interactive = lessonInteractiveService.ensureInteractive(lesson, distributer);
		if(interactive.getCheckedIn())
			throw new LessonCheckInException("已刷卡，无须重复刷卡");
		
		//判断上课人数是否已满
		int checkedCount = lesson.getCheckedInStudentCount()==null?0:lesson.getCheckedInStudentCount();
		int maxCount = null== lesson.getClassRoom()? ClassRoomYzw.DEFAULT_MAX_STUDENT_COUNT:
			(null == lesson.getClassRoom().getMaxStudentCount()? ClassRoomYzw.DEFAULT_MAX_STUDENT_COUNT: lesson.getClassRoom().getMaxStudentCount());
		if(checkedCount>= maxCount)
			throw new LessonCheckInException("上课人数已满");

		
		//保存签到
		LessonCheckInYzw checkIn = new LessonCheckInYzw();
		checkIn.setDistributer(distributer);
		checkIn.setMemberCard(distributer.getMemberCard());
		checkIn.setLesson(lesson);
		checkIn.setContractNo(interactive.getContracNo());
		checkIn.setAppointed(interactive.getAppointed());
		lessonCheckInDao.save(checkIn);
		
		//推送签到事件
		applicationContext.publishEvent(checkIn);
		
		//返回
		return checkIn;
		
	}

	@Scheduled(fixedDelay=999999999, initialDelay=10000)
	@Transactional
	public void settleLessons(){
		List<LessonYzw> lessons = lessonCheckInDao.findNeedSettledLessons();
		logger.info(lessons.size() + "课时待结算");
		for (LessonYzw lesson : lessons) {
			try {
				settleOneLesson(lesson);
			} catch (DataConsistencyException e) {
				logger.info(lesson.getId() + " 数据错误导致结算课时失败");
			}catch (Exception e) {
				logger.info(lesson.getId() + " 未知原因导致结算课时失败");
			}
		}
	}

	
	@Transactional(propagation=Propagation.NESTED)
	public void settleOneLesson(LessonYzw lesson) throws DataConsistencyException{
		switch (lesson.getCourseType()) {
		case OPENED:
			settleOpenLesson(lesson);
			break;
		case CLOSED:
			settleClosedLesson(lesson);
			break;
		case PRIVATE:
			settlePrivateLesson(lesson);
			break;
		default:
			break;
		}
	}
	
	private void settleOpenLesson(LessonYzw lesson) throws DataConsistencyException{
		if(LessonStatus.UN_CHECKED == lesson.getLessonStatus()){
			if(logger.isInfoEnabled())
				logger.info(lesson.getId() + " 课程未审核");
			return;
		}
		
		LessonCheckInYzw effectiveCoachCheckin = getEffictiveCoachCheckin(lesson);
		if(null == effectiveCoachCheckin) {
			if(logger.isInfoEnabled())
				logger.info(lesson.getId() + " 教师未签到");
			return;
		}
		
		List<LessonCheckInYzw> studentsCheckedins = getStudentsCheckedins(lesson);
		if(studentsCheckedins.size()<3) {
			logger.info(lesson.getId() + " 开放式课上课人数不足三人");
			return;
		}
		
		settleOneLesson(lesson, effectiveCoachCheckin, studentsCheckedins);
//		settleCoachCheckins(coachCheckins);
		settleEffectiveCoachCheckin(effectiveCoachCheckin);
		settleStudentCheckins(studentsCheckedins);
		setNoSettled(lesson);
	}
	
	


	private void settlePrivateLesson(LessonYzw lesson) throws DataConsistencyException{
		if(LessonStatus.UN_CHECKED == lesson.getLessonStatus()){
			if(logger.isInfoEnabled())
				logger.info(lesson.getId() + " 课程未审核");
			return;
		}
		
		LessonCheckInYzw effectiveCoachCheckin = getEffictiveCoachCheckin(lesson);
		if(null == effectiveCoachCheckin) {
			if(logger.isInfoEnabled())
				logger.info(lesson.getId() + " 教师未签到");
			return;
		}
		
		List<LessonCheckInYzw> studentsCheckedins = new ArrayList<>();
		if(StringUtils.isEmpty( lesson.getAppointedContracts()))
			throw new DataConsistencyException(lesson.getId() + " 私教课排课没有选择会籍合约");
		String[] contractNos = lesson.getAppointedContracts().split(";");
		for (String contractNo : contractNos) {
			LessonCheckInYzw checkin = lessonCheckInDao.findByLessonIdAndContractNo(lesson.getId(), contractNo);
			if(null == checkin ){
				logger.info(lesson.getId() + " 课时中会籍合约为 " + contractNo + " 的学员未签到， 不能结算");
				return;
			}
			studentsCheckedins.add(checkin);
		}
		
		settleOneLesson(lesson, effectiveCoachCheckin, studentsCheckedins);
//		settleCoachCheckins(coachCheckins);
		settleEffectiveCoachCheckin(effectiveCoachCheckin);
		settleStudentCheckins(studentsCheckedins);
		setNoSettled(lesson);
	}
	

	private void settleClosedLesson(LessonYzw lesson) throws DataConsistencyException{
		if(LessonStatus.UN_CHECKED == lesson.getLessonStatus()){
			if(logger.isInfoEnabled())
				logger.info(lesson.getId() + " 课程未审核");
			return;
		}
		
		LessonCheckInYzw effectiveCoachCheckin = getEffictiveCoachCheckin(lesson);
		if(null == effectiveCoachCheckin) {
			if(logger.isInfoEnabled())
				logger.info(lesson.getId() + " 教师未签到");
			return;
		}
		
		List<LessonCheckInYzw> studentsCheckedins = createStudentCheckedins(lesson);
		if(studentsCheckedins.size() == 0){
			logger.info(lesson.getCourse().getId() + "班级里没有学员");
			return;
		}
		
		
		settleOneLesson(lesson, effectiveCoachCheckin, studentsCheckedins);
//		settleCoachCheckins(coachCheckins);
		settleEffectiveCoachCheckin(effectiveCoachCheckin);
		settleStudentCheckins(studentsCheckedins);
		setNoSettled(lesson);
	}

	/**
	 * @param lesson
	 */
	private void setNoSettled(LessonYzw lesson) {
		lessonCheckInDao.setNoSettled(lesson.getId());
		
	}
	
	/**
	 * @param studentsCheckedins
	 * @throws DataConsistencyException 
	 */
	private void settleStudentCheckins(List<LessonCheckInYzw> studentsCheckedins) throws DataConsistencyException {
			for (LessonCheckInYzw checkin : studentsCheckedins) {
				if(SettleStatus.UN_SETTLED == checkin.getSettleStatus() || null == checkin.getSettleStatus()){
					checkin.setSettleStatus(SettleStatus.SETTLED);
					/** 兼容chenck系统 **/
					checkin.setFlag(true);
					
					float consumedTimes = checkin.getLesson().getCourseType() == CourseType.PRIVATE? checkin.getLesson().getHours():1f;
					decreaseContractRemainTimes(checkin.getContractNo(), BigDecimal.valueOf(consumedTimes));
				}
			}
	}

	/**
	 * @param effectiveCoachCheckin
	 */
	private void settleEffectiveCoachCheckin(LessonCheckInYzw effectiveCoachCheckin) {
		effectiveCoachCheckin.setFlag(true);
		effectiveCoachCheckin.setSettleStatus(SettleStatus.SETTLED);
	}

	/**
	 * @param coachCheckins
	 */
//	private void settleCoachCheckins(List<LessonCheckInYzw> coachCheckins) {
//		for (LessonCheckInYzw checkin : coachCheckins) {
//			checkin.setFlag(true);
//			checkin.setSettleStatus(SettleStatus.NO_SETTLE);
//		}
//	}

	/**
	 * @param lesson
	 * @param effectiveCoachCheckin
	 * @param studentsCheckedins
	 */
	private void settleOneLesson(LessonYzw lesson, LessonCheckInYzw effectiveCoachCheckin,
			List<LessonCheckInYzw> studentsCheckedins) {
		
		lesson.setActualTeacher(effectiveCoachCheckin.getTeacher());
		lesson.setActualTeacherName(effectiveCoachCheckin.getTeacher().getName());
		lesson.setCheckedInStudentCount(studentsCheckedins.size());
		lesson.setLessonStatus(LessonStatus.FINISHED);
		if(effectiveCoachCheckin.getCreateTime().before(lesson.getStartDateTime()))
			lesson.setCoachCheckedinStatus(CheckedInStatus.CHECKED);
		else if (effectiveCoachCheckin.getCreateTime().before(CalendarUtil.getDayEnd(lesson.getStartDateTime()).getTime())) {
			lesson.setCoachCheckedinStatus(CheckedInStatus.LATE);
		}else {
			lesson.setCoachCheckedinStatus(CheckedInStatus.PATCHED);
		}
	}

	

	
	private List<LessonCheckInYzw> getCoachCheckins(LessonYzw lesson){
		if(null == lesson) return new ArrayList<>();
		
		Searchable<LessonCheckInYzw> search = new SearchRequest<>();
		return  findAll(search.and("lesson.id", SearchOperator.eq,lesson.getId())
							  .and("teacher.id", SearchOperator.isNotNull, null)
							  .addOrder(Direction.DESC,"createTime"))
				.getContent();
	}
	
	private LessonCheckInYzw getEffictiveCoachCheckin(LessonYzw lesson){
		if(null == lesson) return null;
		return lessonCheckInDao.findEffictiveCoachCheckinByLessonId(lesson.getId());
	}
	

	
	private LessonCheckInYzw getEffictiveCoachCheckin(List<LessonCheckInYzw> checkins){
		if(null == checkins || checkins.size()==0) return null;
		LessonCheckInYzw coachCheckin =checkins.get(0);
		for (int i=1; i<checkins.size() ; i++) {
			if(coachCheckin.getCreateTime().before(checkins.get(i).getCreateTime())){
				coachCheckin = checkins.get(i);
			}else ;
		}
		return coachCheckin;
	}
	
	private List<LessonCheckInYzw> getStudentsCheckedins(LessonYzw lesson){
		if(null == lesson) return new ArrayList<>();
		
		return lessonCheckInDao.findStudentCheckedinsByLessonId(lesson.getId());
	}
	
	private List<LessonCheckInYzw> createStudentCheckedins(LessonYzw lesson){
		Assert.notNull(lesson);
		Assert.notNull(lesson.getCourse());
		
		List<LessonCheckInYzw> checkins = new ArrayList<>();
		List<OrderYzw> orders = orderDao.findByCourseId(lesson.getCourse().getId());
		for (OrderYzw order : orders) {
			LessonCheckInYzw checkin = new LessonCheckInYzw();
			CustomerYzw customer = order.getCustomer();
			Distributer distributer = order.getCustomer().getDistributer();
			checkin.setDistributer(distributer);
			checkin.setMemberCard(customer.getMemberCard());
			checkin.setLesson(lesson);
			checkin.setContractNo(order.getContract().getContractNo());
			
			save(checkin);
			checkins.add(checkin);
		}
		
		return checkins;
	}
	
	/**
	 * @param contractNos
	 * @return
	 * @throws DataConsistencyException 
	 */
	private List<LessonCheckInYzw> getPrivateLessonEffictiveStudentCheckins(LessonYzw lesson) throws DataConsistencyException {
		Assert.notNull(lesson);
		
		if(StringUtils.isEmpty( lesson.getAppointedContracts()))
			throw new DataConsistencyException(lesson.getId() + " 私教课排课没有选择会籍合约");
		String[] contractNos = lesson.getAppointedContracts().split(";");
		
		List<LessonCheckInYzw> checkins = lessonCheckInDao.findStudentCheckedinsByLessonIdContractNos(lesson.getId(),contractNos);
		if(checkins.size() < contractNos.length)
			throw new RuntimeException(lesson.getId() + " 有学员未刷卡");
		
		return checkins;
	}

	
	private void decreaseContractRemainTimes(String constractNo, BigDecimal times) throws DataConsistencyException{
		try {
			OrderYzw order = orderDao.findByContractNO(constractNo);
			if(null == order)
				throw new DataConsistencyException(constractNo + " 无效的会籍合约号");
			Contract contract = order.getContract();
			contract.setRemainTimes(contract.getRemainTimes().subtract(times));
			/** 非封闭式课程， 通过预约刷卡机制上课，需要清除掉withHoldtimes **/
			if(contract.getType() != CourseType.CLOSED){
				Integer endWithHoldTimes = contract.getWithHoldTimes() - times.intValue();
				endWithHoldTimes = endWithHoldTimes < 0? 0: endWithHoldTimes;
				contract.setWithHoldTimes(endWithHoldTimes.shortValue());
			}
		} catch (YiwuException e) {
			logger.error(e.getMessage(),e);
		}
		
	}
}
