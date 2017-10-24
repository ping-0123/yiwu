package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.CoachMediaDao;
import com.yinzhiwu.yiwu.entity.CoachMedia;
import com.yinzhiwu.yiwu.service.CoachMediaService;

/**
*@Author ping
*@Time  创建时间:2017年10月7日下午3:35:20
*
*/

@Service
public class CoachMediaServiceImpl extends BaseServiceImpl<CoachMedia,Integer> implements CoachMediaService{

	@Autowired public void setBaseDao(CoachMediaDao mediaDao){super.setBaseDao(mediaDao);}
	@Autowired private CoachMediaDao coachMediaDao;

	@Override
	public CoachMedia findHeaderMediaByCoachId(Integer coachId) {
		List<CoachMedia> medias = coachMediaDao.findByCoachIdAndTag(coachId, CoachMedia.MediaTag.HEADER);
		if(medias.size()>0)
			return medias.get(0);
		return null;
	}

	@Override
	public List<CoachMedia> findByCoachId(Integer coachId) {
		return coachMediaDao.findByCoachId(coachId);
	}
}
