package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.yzw.CourseYzw;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.CourseApiView;

public interface CourseYzwService extends IBaseService<CourseYzw, String> {

	YiwuJson<CourseApiView> findById(String id);

}
