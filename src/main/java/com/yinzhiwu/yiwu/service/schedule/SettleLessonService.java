
package com.yinzhiwu.yiwu.service.schedule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.yinzhiwu.yiwu.dao.CoachCallRollDao;
import com.yinzhiwu.yiwu.dao.LessonCheckInYzwDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.dao.StoreManCallRollYzwDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.Contract.ContractStatus;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw.SettleStatus;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw.CheckedInStatus;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw.LessonStatus;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.exception.business.SettleLessonException;
import com.yinzhiwu.yiwu.exception.data.DataConsistencyException;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.data.DuplicateContractNoException;
import com.yinzhiwu.yiwu.service.CourseYzwService;
import com.yinzhiwu.yiwu.service.LessonYzwService;
import com.yinzhiwu.yiwu.util.CalendarUtil;

/**
 * @author ping
 * @date 2017年12月12日上午10:53:21
 * @since v2.2.0
 *	
 */

@Service
public class SettleLessonService {
	
	private final static Logger logger = LoggerFactory.getLogger(SettleLessonService.class);
	
	@Autowired private LessonCheckInYzwDao lessonCheckInDao;
	@Autowired private OrderYzwDao orderDao;
	@Autowired private StoreManCallRollYzwDao smcrDao;
	@Autowired private CoachCallRollDao ccrDao;
	@Autowired private CourseYzwService courseService;
	@Autowired private LessonYzwService lessonService;
	
//	@Scheduled(fixedRate=9999999, initialDelay=10000)
	@Transactional
	public void tempSettleLessons(){
		settleLessons();
	}
	
	
	@Scheduled(cron="0 0 4 * * ?")
	@Transactional
	public void settleLessons(){
		List<LessonYzw> lessons = lessonCheckInDao.findNeedSettledLessons();
		logger.info(lessons.size() + "课时待结算");
		for (LessonYzw lesson : lessons) {
				try {
					settleOneLesson(lesson);
				} catch (DataConsistencyException e) {
					SettleLessonException ex = new SettleLessonException(lesson.getId(), e);
					logger.info(ex.getMessage());
				} catch (SettleLessonException e) {
					logger.info(e.getMessage());
				}catch(Exception e){
					logger.info(e.getMessage(),e);
				}
		}
		
	}

	
//	@Async		
	@Transactional(propagation=Propagation.NESTED)
	public void settleOneLesson(LessonYzw lesson) throws DataConsistencyException, SettleLessonException{
		logger.debug("current thread is " + Thread.currentThread().getId() + "  " + Thread.currentThread().getName());
		try {
			lesson = lessonService.get(lesson.getId());
		} catch (DataNotFoundException e) {
			return;
		}
		logger.info("start settle lesson#" + lesson.getId());
		
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
	
	private void settleOpenLesson(LessonYzw lesson) throws DataConsistencyException , SettleLessonException{
		if(LessonStatus.UN_CHECKED == lesson.getLessonStatus()){
			throw new SettleLessonException(lesson.getId(), "课程未审核");
		}
		
		LessonCheckInYzw effectiveCoachCheckin = getEffictiveCoachCheckin(lesson);
		if(null == effectiveCoachCheckin) {
			throw new SettleLessonException(lesson.getId(), "教练未签到");
		}
		
		List<LessonCheckInYzw> studentsCheckedins = getStudentsCheckedins(lesson);
		if(studentsCheckedins.size()<3) {
			throw new SettleLessonException(lesson.getId(), "开放式课上课人数不足三人");
		}
		
		settleOneLesson(lesson, effectiveCoachCheckin, studentsCheckedins);
		settleEffectiveCoachCheckin(effectiveCoachCheckin);
		settleStudentCheckins(studentsCheckedins);
		setNoSettled(lesson);
	}
	
	


	private void settlePrivateLesson(LessonYzw lesson) throws DataConsistencyException, SettleLessonException{
		if(LessonStatus.UN_CHECKED == lesson.getLessonStatus()){
			throw new SettleLessonException(lesson.getId(), "课程未审核");
		}
		
		LessonCheckInYzw effectiveCoachCheckin = getEffictiveCoachCheckin(lesson);
		if(null == effectiveCoachCheckin) {
			throw new SettleLessonException(lesson.getId(), "教练未签到");
		}
		
		List<LessonCheckInYzw> studentsCheckedins = new ArrayList<>();
		if(StringUtils.isEmpty( lesson.getAppointedContracts()))
			throw new DataConsistencyException(lesson.getId() + " 私教课排课没有选择会籍合约");
		String[] contractNos = lesson.getAppointedContracts().split(";");
		for (String contractNo : contractNos) {
			LessonCheckInYzw checkin = lessonCheckInDao.findByLessonIdAndContractNo(lesson.getId(), contractNo);
			if(null == checkin ){
				throw new SettleLessonException(lesson.getId(), contractNo + "未签到");
			}
			studentsCheckedins.add(checkin);
		}
		
		settleOneLesson(lesson, effectiveCoachCheckin, studentsCheckedins);
//		settleCoachCheckins(coachCheckins);
		settleEffectiveCoachCheckin(effectiveCoachCheckin);
		settleStudentCheckins(studentsCheckedins);
		setNoSettled(lesson);
	}
	

	private void settleClosedLesson(LessonYzw lesson) throws DataConsistencyException, SettleLessonException{
		if(LessonStatus.UN_CHECKED == lesson.getLessonStatus()){
			throw new SettleLessonException(lesson.getId(), "课程未审核");
		}
		
		LessonCheckInYzw effectiveCoachCheckin = getEffictiveCoachCheckin(lesson);
		if(null == effectiveCoachCheckin) {
			throw new SettleLessonException(lesson.getId(), "教练未签到");
		}
		
		List<LessonCheckInYzw> studentsCheckedins = createStudentCheckedins(lesson);
		if(studentsCheckedins.size() == 0){
			throw new SettleLessonException(lesson.getId(),lesson.getCourse().getId()+ "班级里没有学员");
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
	 * @throws DataNotFoundException 
	 */
	private void settleStudentCheckins(List<LessonCheckInYzw> studentsCheckedins) throws DataConsistencyException{
			for (LessonCheckInYzw checkin : studentsCheckedins) {
				if(SettleStatus.UN_SETTLED == checkin.getSettleStatus() || null == checkin.getSettleStatus()){
					
					float consumedTimes = checkin.getLesson().getCourseType() == CourseType.PRIVATE? checkin.getLesson().getHours():1f;
					try {
						decreaseContractRemainTimes(checkin.getContractNo(), BigDecimal.valueOf(consumedTimes));
						checkin.setSettleStatus(SettleStatus.SETTLED);
						/** 兼容chenck系统 **/
						checkin.setFlag(true);
					} catch (DataNotFoundException e) {
						logger.info(e.getLocalizedMessage());
						checkin.setSettleStatus(SettleStatus.NO_SETTLE);
						/** 兼容chenck系统 **/
						checkin.setFlag(true);
					}
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
		
		Assert.notNull(lesson);
		
		lesson.setActualTeacher(effectiveCoachCheckin.getTeacher());
		lesson.setActualTeacherName(effectiveCoachCheckin.getTeacher().getName());
		int studentCount = studentsCheckedins.size();
		lesson.setCheckedInStudentCount(studentCount);
		lesson.setActualStudentCount(studentCount);
		lesson.setDueStudentCount(studentCount);
		lesson.setLessonStatus(LessonStatus.FINISHED);
		if(effectiveCoachCheckin.getCreateTime().before(lesson.getStartDateTime()))
			lesson.setCoachCheckedinStatus(CheckedInStatus.CHECKED);
		else if (effectiveCoachCheckin.getCreateTime().before(CalendarUtil.getDayEnd(lesson.getStartDateTime()).getTime())) {
			lesson.setCoachCheckedinStatus(CheckedInStatus.LATE);
		}else {
			lesson.setCoachCheckedinStatus(CheckedInStatus.PATCHED);
		}
		
		lesson.setRollCalledStudentCountByCoach((int) ccrDao.countOfcalled(lesson.getId()));
		lesson.setRollCalledStudentCountByStoreman((int) smcrDao.countOfCalled(lesson.getId()));
		
		lessonService.update(lesson);
	}

	

	
//	private List<LessonCheckInYzw> getCoachCheckins(LessonYzw lesson){
//		if(null == lesson) return new ArrayList<>();
//		
//		Searchable<LessonCheckInYzw> search = new SearchRequest<>();
//		return  lessonCheckInDao.findAll(search.and("lesson.id", SearchOperator.eq,lesson.getId())
//							  .and("teacher.id", SearchOperator.isNotNull, null)
//							  .addOrder(Direction.DESC,"createTime"))
//				.getContent();
//	}
//	
	private LessonCheckInYzw getEffictiveCoachCheckin(LessonYzw lesson){
		if(null == lesson) return null;
		return lessonCheckInDao.findEffictiveCoachCheckinByLessonId(lesson.getId());
	}
	

	
//	private LessonCheckInYzw getEffictiveCoachCheckin(List<LessonCheckInYzw> checkins){
//		if(null == checkins || checkins.size()==0) return null;
//		LessonCheckInYzw coachCheckin =checkins.get(0);
//		for (int i=1; i<checkins.size() ; i++) {
//			if(coachCheckin.getCreateTime().before(checkins.get(i).getCreateTime())){
//				coachCheckin = checkins.get(i);
//			}else ;
//		}
//		return coachCheckin;
//	}
	
	private List<LessonCheckInYzw> getStudentsCheckedins(LessonYzw lesson){
		if(null == lesson) return new ArrayList<>();
		
		return lessonCheckInDao.findEffictiveStudentCheckins(lesson.getId());
	}
	
	/**
	 * 封闭式课程创建刷卡记录
	 * @param lesson
	 * @return
	 */
	private List<LessonCheckInYzw> createStudentCheckedins(LessonYzw lesson){
		Assert.notNull(lesson);
		
		List<LessonCheckInYzw> checkins = new ArrayList<>();
		checkins.addAll(getStudentsCheckedins(lesson));
		if(checkins.size()==0){
			List<OrderYzw> orders = orderDao.findCheckedOrdersByCourseId(lesson.getCourse().getId());
			for (OrderYzw order : orders) {
				LessonCheckInYzw checkin = new LessonCheckInYzw();
				CustomerYzw customer = order.getCustomer();
				Distributer distributer = order.getCustomer().getDistributer();
				checkin.setDistributer(distributer);
				checkin.setMemberCard(customer.getMemberCard());
				checkin.setLesson(lesson);
				checkin.setContractNo(order.getContract().getContractNo());
				
				lessonCheckInDao.save(checkin);
				checkins.add(checkin);
			}
		}
		
		return checkins;
	}
	
	/**
	 * @param contractNos
	 * @return
	 * @throws DataConsistencyException 
	 * @throws SettleLessonException 
	 */
	private List<LessonCheckInYzw> getPrivateLessonEffictiveStudentCheckins(LessonYzw lesson) throws  SettleLessonException {
		Assert.notNull(lesson);
		
		if(StringUtils.isEmpty( lesson.getAppointedContracts())){
			Exception ex =  new DataConsistencyException(lesson.getId() + " 私教课排课没有选择会籍合约");
			throw new SettleLessonException(lesson.getId(), ex);
		}
		String[] contractNos = lesson.getAppointedContracts().split(";");
		
		List<LessonCheckInYzw> checkins = lessonCheckInDao.findStudentCheckedinsByLessonIdContractNos(lesson.getId(),contractNos);
		if(checkins.size() < contractNos.length)
			throw new SettleLessonException(lesson.getId() , " 有学员未刷卡");
		
		return checkins;
	}

	
	private void decreaseContractRemainTimes(String constractNo, BigDecimal times) throws  DataNotFoundException{
			OrderYzw order;
			try {
				order = orderDao.findByContractNO(constractNo);
			} catch (DuplicateContractNoException e) {
				order = orderDao.findCheckedOrderByContractNo(constractNo);
			}
			Contract contract = order.getContract();
			BigDecimal remainTimes = contract.getRemainTimes().subtract(times);
			if(remainTimes.compareTo(BigDecimal.valueOf(0)) <=0){
				contract.setStatus(ContractStatus.EXPIRED);
				/** 踢出班级 **/
				if(CourseType.CLOSED == contract.getType() && StringUtils.hasLength(contract.getCourseId())){
					/** 踢学员出班级后 需要重新审核 **/
					courseService.updateCourseStatus(contract.getCourseId());
					contract.setCourseId(null);;
				}
			}
			contract.setRemainTimes(remainTimes);
			/** 非封闭式课程， 通过预约刷卡机制上课，需要清除掉withHoldtimes **/
			if(contract.getType() != CourseType.CLOSED){
				Integer endWithHoldTimes = contract.getWithHoldTimes() - times.intValue();
				/**兼容补刷的情况下， 不会去设置 withhold times **/
				endWithHoldTimes = endWithHoldTimes < 0? 0: endWithHoldTimes;
				contract.setWithHoldTimes(endWithHoldTimes.shortValue());
			}
			
			orderDao.update(order);
		
	}
}
