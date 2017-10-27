package com.yinzhiwu.yiwu.event;

import com.yinzhiwu.yiwu.entity.LessonComment;

@SuppressWarnings("serial")
public class LessonCommentEvent extends LessonInteractiveEvent {

	public LessonCommentEvent(LessonComment source) {
		super(source);
	}

}
