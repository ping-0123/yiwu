package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.LessonInteractiveDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.LessonComment;
import com.yinzhiwu.yiwu.entity.LessonInteractive;
import com.yinzhiwu.yiwu.entity.LessonPraise;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.business.LessonAppointmentException;
import com.yinzhiwu.yiwu.exception.business.LessonCommentException;
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
	
	@EventListener(classes={LessonComment.class})
	public void handlelessonComment(LessonComment comment){
		LessonInteractive interactive;
		try {
			interactive = ensureInteractive( comment.getLesson(),comment.getCommenter());
		} catch (DataNotFoundException e1) {
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

	
	@EventListener(classes={LessonPraise.class})
	public void handleLessonPraise(LessonPraise praise) {
		LessonInteractive interactive;
		try {
			interactive = ensureInteractive(praise.getLesson(), praise.getDistributer());
		} catch (DataNotFoundException e1) {
			throw new RuntimeException(e1);
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

	
	@EventListener(classes={LessonAppointmentYzw.class})
	public void handleLessonAppointment(LessonAppointmentYzw appointment){
		LessonInteractive interactive;
		try {
			interactive = lessonInteractiveDao
					.findByDistributerIdAndLessonId(appointment.getDistributer().getId(), appointment.getLesson().getId());
		} catch (DataNotFoundException e) {
			interactive = new LessonInteractive();
			interactive.init();
			interactive.setDistributer(appointment.getDistributer());
			interactive.setLesson(appointment.getLesson());
			interactive.setContracNo(appointment.getContractNo());
		}
		
		try{
			switch (appointment.getStatus()) {
			case APPONTED:
				if(interactive.getAppointed())
					throw new LessonAppointmentException("已预约， 无须重复预约");
				interactive.setContracNo(appointment.getContractNo()); //设置两个会籍合约一样
				interactive.setAppointed(true);
				break;
			case UN_APOINTED:
				if(!interactive.getAppointed())
					throw new LessonAppointmentException("未预约， 不能取消预约");
				interactive.setAppointed(false);
				break;
			default:
				break;
			}
				
			
		}catch (LessonAppointmentException	e) {
			throw new RuntimeException(e);
		}
		
		lessonInteractiveDao.saveOrUpdate(interactive);
	}
	

	@Override
	public LessonInteractive ensureInteractive(LessonYzw lesson, Distributer distributer) throws DataNotFoundException {
		
		
		LessonInteractive interactive;
		try {
			interactive = lessonInteractiveDao
					.findByDistributerIdAndLessonId(distributer.getId(), lesson.getId());
		} catch (DataNotFoundException e) {
			interactive = new LessonInteractive();
			interactive.init();
			interactive.setDistributer(distributer);
			interactive.setLesson(lesson);
			Contract contract = orderService.findEnableInteractiveContractByLessonAndDistributer(lesson, distributer);
			interactive.setContracNo(contract.getContractNo());
		}
		return interactive;
	}
}
