package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.LessonComment;
import com.yinzhiwu.yiwu.entity.LessonComment.CommentType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

/**
*@Author ping
*@Time  创建时间:2017年10月10日下午5:40:29
*
*/

public interface LessonCommentDao extends IBaseDao<LessonComment,Integer> {

	LessonComment findByDistributerIdAndLessonIdAndType(Integer distributerId, Integer lessonId, CommentType type) throws DataNotFoundException;
	
	Long findCountByDistributerIdAndLessonIdAndType(Integer distributerId, Integer lessonId, CommentType type);
}