package com.yinzhiwu.yiwu.event;

import com.yinzhiwu.yiwu.entity.LessonPraise;

@SuppressWarnings("serial")
public class LessonPraiseEvent extends LessonInteractiveEvent{

	public LessonPraiseEvent(LessonPraise source) {
		super(source);
	}

}
