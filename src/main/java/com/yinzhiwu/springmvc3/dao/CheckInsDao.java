package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.yzwOld.CheckIns;

public interface CheckInsDao extends IBaseDao<CheckIns,Integer>{

	int findCheckedInStudentCountByLessonId(String lessonId);
	
}
