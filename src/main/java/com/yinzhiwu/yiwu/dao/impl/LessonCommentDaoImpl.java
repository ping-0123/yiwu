package com.yinzhiwu.yiwu.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.LessonCommentDao;
import com.yinzhiwu.yiwu.entity.LessonComment;
import com.yinzhiwu.yiwu.entity.LessonComment.CommentType;
import com.yinzhiwu.yiwu.exception.DataConsistencyException;

/**
*@Author ping
*@Time  创建时间:2017年10月10日下午5:41:28
*
*/

@Repository
public class LessonCommentDaoImpl extends BaseDaoImpl<LessonComment,Integer> implements LessonCommentDao {

	@Override
	public LessonComment findByDistributerIdAndLessonIdAndType(Integer distributerId, Integer lessonId,
			CommentType type) {
		
		return findOneByProperties(
				new String[]{"commenter.id", "lesson.id","type"}, 
				new Object[]{distributerId, lessonId,type});
	}
	

	@Override
	public Long findCountByDistributerIdAndLessonIdAndType(Integer distributerId, Integer lessonId,
			CommentType type) {
		return findCountByProperties(
				new String[]{"commenter.id", "lesson.id","type"}, 
				new Object[]{distributerId, lessonId,type});
	}
	
	
	@Override
	public Integer save(LessonComment comment) {
		
		//检查是否符合评论逻辑
		try {
			switch (comment.getType()) {
			case FIRST:
				if(findCountByDistributerIdAndLessonIdAndType(comment.getCommenter().getId(), comment.getLesson().getId(), CommentType.FIRST) > 0)
					throw new DataConsistencyException("首评 只有一次");
				if(null == comment.getStars() || comment.getStars()<1 || comment.getStars()>6)
					throw new DataConsistencyException("首评请指定正确的星级。");
				break;
			case APPEND:
				if(findCountByDistributerIdAndLessonIdAndType(comment.getCommenter().getId(), comment.getLesson().getId(), CommentType.APPEND) > 0)
					throw new DataConsistencyException("已追评论，不能多次追评");
				else if(findCountByDistributerIdAndLessonIdAndType(comment.getCommenter().getId(), comment.getLesson().getId(), CommentType.FIRST) == 0) {
					throw new DataConsistencyException("尚未首评论");
				}
				break;
			case REPLY:
				if(null == comment.getReAppraise())
					throw new DataConsistencyException("被回复的评论未知");
				break;
			default:
				throw new DataConsistencyException("unsurported comment type ", null);
			}
		} catch (DataConsistencyException e) {
			throw new RuntimeException(e);
		}
		
		return super.save(comment);
	}





}
