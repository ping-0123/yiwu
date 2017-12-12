package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.LessonComment;
import com.yinzhiwu.yiwu.entity.LessonComment.CommentType;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;

/**
*@Author ping
*@Time  创建时间:2017年10月10日下午5:42:35
*
*/

public interface LessonCommentService  extends IBaseService<LessonComment,Integer>{

	LessonComment findByDistributerIdAndLessonIdAndType(Integer distributerId, Integer lessonId, CommentType type) throws DataNotFoundException;
	
	boolean checkFirstComment(Integer distributerId, Integer lessonId);
	
	boolean checkAppendComment(Integer distributerId, Integer lessonId);
	
	public LessonComment doLessonComment(LessonComment comment);
}
