package com.yinzhiwu.yiwu.service;

import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;

public interface CourseYzwService extends IBaseService<CourseYzw, String> {

	@Transactional
	public void scheduledSetOne_StudentCount_SumLessonTimesAndLessonOrdialNo();

}
