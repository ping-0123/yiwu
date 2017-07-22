package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.yzwOld.CheckIns;

public interface CheckInsDao extends IBaseDao<CheckIns,Integer>{

	int findCheckedInStudentCountByLessonId(String lessonId);
	
}
