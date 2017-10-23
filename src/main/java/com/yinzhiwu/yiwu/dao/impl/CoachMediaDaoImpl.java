package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.CoachMediaDao;
import com.yinzhiwu.yiwu.entity.CoachMedia;
import com.yinzhiwu.yiwu.entity.CoachMedia.MediaTag;

/**
*@Author ping
*@Time  创建时间:2017年10月7日下午3:31:40
*
*/

@Repository
public class CoachMediaDaoImpl extends BaseDaoImpl<CoachMedia,Integer> implements CoachMediaDao {

	@Override
	public List<CoachMedia> findByCoachIdAndTag(Integer coachId, MediaTag header) {
		return findByProperties(new String[]{"coach.id", "tag"}, new Object[]{coachId,header});
	}

}
