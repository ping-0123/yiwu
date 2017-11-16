package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.dao.LessonInteractiveDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.LessonComment;
import com.yinzhiwu.yiwu.entity.LessonInteractive;
import com.yinzhiwu.yiwu.entity.LessonPraise;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.business.LessonCommentException;
import com.yinzhiwu.yiwu.exception.business.LessonInteractiveException;
import com.yinzhiwu.yiwu.exception.business.LessonPraiseException;
import com.yinzhiwu.yiwu.service.LessonInteractiveService;
import com.yinzhiwu.yiwu.service.OrderYzwService;

/**
*@Author ping
*@Time  创建时间:2017年10月27日上午10:04:00
*
*/

@Service
public class LessonInteractiveServiceImpl extends BaseServiceImpl<LessonInteractive,Integer> implements LessonInteractiveService {
	
	@Autowired private LessonInteractiveDao lessonInteractiveDao;
	@Autowired private OrderYzwService orderService;

	
	@Autowired public void setBaseDao(LessonInteractiveDao dao){super.setBaseDao(dao);}
	
	@Override
	public LessonInteractive findByDistributerIdAndLessonId(Integer distributerId, Integer lessonId) throws DataNotFoundException {
			return lessonInteractiveDao.findByDistributerIdAndLessonId(distributerId, lessonId);
	}
	
	@Transactional
	@EventListener(classes={LessonComment.class})
	public void handlelessonComment(LessonComment comment){
		LessonInteractive interactive;
		try {
			interactive = ensureInteractive( comment.getLesson(),comment.getCommenter());
		} catch (LessonInteractiveException e1) {
			throw new RuntimeException(e1);
		}
		
		try{
			switch (comment.getType()) {
			case FIRST:
				if(interactive.getFirstCommented())
					throw new LessonCommentException("请不要重复首评");
				interactive.setFirstCommented(true);
				break;
			case APPEND:
				if(!interactive.getFirstCommented())
					throw new LessonCommentException("请先首评");
				if(interactive.getAppendCommented())
					throw new LessonCommentException("不要重复追评");
				interactive.setAppendCommented(true);
				break;
			default:
				break;
			}
		}catch (LessonCommentException e) {
			throw new RuntimeException(e);
		}
		
		lessonInteractiveDao.saveOrUpdate(interactive);
	}

	@Transactional
	@EventListener(classes={LessonPraise.class})
	public void handleLessonPraise(LessonPraise praise) {
		logger.info("start handle lesson praise event");
		LessonInteractive interactive;
		try {
			interactive = ensureInteractive(praise.getLesson(), praise.getDistributer());
		} catch (LessonInteractiveException e) {
			throw new RuntimeException(e);
		}
		
		try{
			if(praise.getCanceled()){
				if(!interactive.getPraised())
					throw new LessonPraiseException("未点赞， 不能取消点赞");
				interactive.setPraised(false);
			}else {
				if(interactive.getPraised())
					throw new LessonPraiseException("已点赞,无需重复点赞");
				interactive.setPraised(true);
			}
		}catch (LessonPraiseException e) {
			throw new RuntimeException(e);
		}
		
		lessonInteractiveDao.saveOrUpdate(interactive);
	}

	
	@Transactional
	@EventListener(classes={LessonAppointmentYzw.class})
	public void handleLessonAppointment(LessonAppointmentYzw appointment){
		LessonInteractive interactive;
		try {
			interactive = ensureInteractive(appointment.getLesson(), appointment.getDistributer());
		} catch (LessonInteractiveException e1) {
			throw new RuntimeException(e1);
		}
	
		switch (appointment.getStatus()) {
		case APPONTED:
			interactive.setAppointed(true);
			break;
		case UN_APOINTED:
			interactive.setAppointed(false);
			break;
		default:
			break;
		}
				
		lessonInteractiveDao.update(interactive);
	}
	
	@Transactional
	@EventListener(classes={LessonCheckInYzw.class})
	public void handleLessonCheckIn(LessonCheckInYzw checkIn){
		LessonInteractive interactive;
		try {
			interactive = ensureInteractive(checkIn.getLesson(), checkIn.getDistributer());
		} catch (LessonInteractiveException e) {
			throw new RuntimeException(e);
		}
		interactive.setCheckedIn(true);
		lessonInteractiveDao.update(interactive);
	}
	

	@Override
	public LessonInteractive ensureInteractive(LessonYzw lesson, Distributer distributer) throws LessonInteractiveException {
		
		
		LessonInteractive interactive;
		try {
			interactive = lessonInteractiveDao
					.findByDistributerIdAndLessonId(distributer.getId(), lesson.getId());
		} catch (DataNotFoundException e) {
			interactive = new LessonInteractive();
			interactive.setDistributer(distributer);
			interactive.setLesson(lesson);
			Contract contract;
			try {
				contract = orderService.findEnableInteractiveContractByLessonAndDistributer(lesson, distributer);
			} catch (DataNotFoundException e1) {
				throw new LessonInteractiveException(distributer.getId() +"无权与" + lesson.getId() + "交互",e1);
			}
			interactive.setContracNo(contract.getContractNo());
			
			lessonInteractiveDao.save(interactive);
		}
		return interactive;
	}
}
