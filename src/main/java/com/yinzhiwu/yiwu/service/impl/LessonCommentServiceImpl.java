package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.LessonCommentDao;
import com.yinzhiwu.yiwu.entity.LessonComment;
import com.yinzhiwu.yiwu.entity.LessonComment.CommentType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.LessonCommentService;

/**
*@Author ping
*@Time  创建时间:2017年10月10日下午5:43:12
*
*/

@Service
public class LessonCommentServiceImpl  extends BaseServiceImpl<LessonComment,Integer> implements LessonCommentService{

		@Autowired public void setBaseDao(LessonCommentDao laDao){super.setBaseDao(laDao);}
		@Autowired private LessonCommentDao lessonCommentDao;
		@Autowired private ApplicationContext applicationContext;
		
		@Override
		public LessonComment findByDistributerIdAndLessonIdAndType(Integer distributerId, Integer lessonId,
				CommentType type) throws DataNotFoundException {
			return lessonCommentDao.findByDistributerIdAndLessonIdAndType(distributerId, lessonId,type);
		}

		@Override
		public boolean checkFirstComment(Integer distributerId, Integer lessonId) {
			return lessonCommentDao.findCountByDistributerIdAndLessonIdAndType(distributerId, lessonId, CommentType.FIRST) >0;
		}

		@Override
		public boolean checkAppendComment(Integer distributerId, Integer lessonId) {
			return lessonCommentDao.findCountByDistributerIdAndLessonIdAndType(distributerId, lessonId, CommentType.APPEND                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ) >0;
		}


		@Override
		public LessonComment doLessonComment(LessonComment comment) {
			if(CommentType.FIRST==comment.getType() || CommentType.APPEND==comment.getType())
				applicationContext.publishEvent(comment);
			lessonCommentDao.save(comment);
			return comment;
		}
		
		
}
