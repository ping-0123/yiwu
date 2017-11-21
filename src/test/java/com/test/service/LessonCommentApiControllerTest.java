package com.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.model.view.LessonCommentVO;
import com.yinzhiwu.yiwu.service.LessonCommentService;

/**
*@Author ping
*@Time  创建时间:2017年11月21日下午3:17:21
*
*/

public class LessonCommentApiControllerTest extends BaseSpringTest {
	
	@Autowired LessonCommentService lcService;
	
	@Test
	public void testDoPost(){
		LessonCommentVO vo = new LessonCommentVO();
		vo.setLessonId(223546);
		vo.setComment("非常好");
	}
}	
