package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.CourseApiView;

public interface CourseYzwService extends IBaseService<CourseYzw, String> {

	YiwuJson<CourseApiView> findById(String id);

}
