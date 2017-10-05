package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.RegionDao;
import com.yinzhiwu.yiwu.entity.Region;
import com.yinzhiwu.yiwu.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService {
	
	@Autowired private RegionDao regionDao;
	
	@Override
	public Region get(Integer id){
		return regionDao.get(id);
	}
}
