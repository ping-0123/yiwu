package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.LessonInteractiveDao;
import com.yinzhiwu.yiwu.entity.LessonComment;
import com.yinzhiwu.yiwu.entity.LessonInteractive;
import com.yinzhiwu.yiwu.entity.LessonPraise;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataConsistencyException;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.business.LessonPraiseException;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.service.LessonInteractiveService;
import com.yinzhiwu.yiwu.service.LessonYzwService;

/**
*@Author ping
*@Time  创建时间:2017年10月27日上午10:04:00
*
*/

@Service
public class LessonInteractiveServiceImpl extends BaseServiceImpl<LessonInteractive,Integer> implements LessonInteractiveService {
	
	@Autowired public void setBaseDao(LessonInteractiveDao dao){super.setBaseDao(dao);}
	@Autowired private LessonInteractiveDao lessonInteractiveDao;
	
	@Override
	public LessonInteractive findByDistributerIdAndLessonId(Integer distributerId, Integer lessonId) throws DataNotFoundException {
			return lessonInteractiveDao.findByDistributerIdAndLessonId(distributerId, lessonId);
	}
	
	@EventListener(classes={LessonComment.class})
	public void handlelessonComment(LessonComment comment) throws DataConsistencyException{
		LessonInteractive interactive;
		try {
			interactive = lessonInteractiveDao
					.findByDistributerIdAndLessonId(comment.getCommenter().getId(), comment.getLesson().getId());
		} catch (DataNotFoundException e) {
			interactive = new LessonInteractive();
			interactive.init();
			interactive.setDistributer(comment.getCommenter());
			interactive.setLesson(comment.getLesson());
		}
		
		switch (comment.getType()) {
		case FIRST:
			interactive.setFirstCommented(true);
			break;
		case APPEND:
			if(!interactive.getFirstCommented())
				throw new DataConsistencyException("请先首评");
			interactive.setAppendCommented(true);
			break;
		default:
			break;
		}
		
		lessonInteractiveDao.saveOrUpdate(interactive);
	}
	
	
	@EventListener(classes={LessonPraise.class})
	public void handleLessonPraise(LessonPraise praise) throws LessonPraiseException{
		LessonInteractive interactive;
		try {
			interactive = lessonInteractiveDao
					.findByDistributerIdAndLessonId(praise.getDistributer().getId(),praise.getLesson().getId());
		} catch (DataNotFoundException e) {
			interactive = new LessonInteractive();
			interactive.init();
			interactive.setDistributer(praise.getDistributer());
			interactive.setLesson(praise.getLesson());
		}
		
		if(praise.getCanceled()){
			if(!interactive.getPraised())
				throw new LessonPraiseException("未点赞， 不能取消点赞");
			interactive.setPraised(false);
		}else {
			if(interactive.getPraised())
				throw new LessonPraiseException("已点赞,无需重复点赞");
			interactive.setPraised(true);
		}
		
		lessonInteractiveDao.saveOrUpdate(interactive);
	}



}
