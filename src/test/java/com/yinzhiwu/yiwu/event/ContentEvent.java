package com.yinzhiwu.yiwu.event;

import org.springframework.context.ApplicationEvent;

/**
*@Author ping
*@Time  创建时间:2017年10月27日下午3:17:22
*
*/

@SuppressWarnings("serial")
public class ContentEvent extends ApplicationEvent{

	public ContentEvent(Object source) {
		super(source);
	}

}
