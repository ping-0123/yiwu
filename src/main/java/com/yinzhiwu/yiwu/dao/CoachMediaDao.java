package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.CoachMedia;
import com.yinzhiwu.yiwu.entity.CoachMedia.MediaTag;

/**
*@Author ping
*@Time  创建时间:2017年10月7日下午3:30:49
*
*/

public interface CoachMediaDao extends IBaseDao<CoachMedia,Integer>{

	List<CoachMedia> findByCoachIdAndTag(Integer coachId, MediaTag header);

	List<CoachMedia> findByCoachId(Integer coachId);

}
