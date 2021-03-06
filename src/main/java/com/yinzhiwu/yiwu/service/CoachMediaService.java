package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.CoachMedia;

/**
*@Author ping
*@Time  创建时间:2017年10月7日下午3:34:49
*
*/

public interface CoachMediaService extends IBaseService<CoachMedia,Integer>{

	CoachMedia findHeaderMediaByCoachId(Integer coachId);

	List<CoachMedia> findByCoachId(Integer coachId);

}
