package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.CheckIns;

public interface CheckInsDao extends IBaseDao<CheckIns,Integer>{

	int findCheckedInStudentCountByLessonId(String lessonId);
	
}
