package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.ExpRecordDao;
import com.yinzhiwu.springmvc3.dao.MoneyRecordDao;
import com.yinzhiwu.springmvc3.model.SumRecordApiView;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.BusinessService;


@Service
public class BusinessServiceImpl implements BusinessService{
	
	@Autowired 
	private ExpRecordDao expRecordDao;
	
	@Autowired
	private MoneyRecordDao moneyRecordDao;

	private YiwuJson<SumRecordApiView> mYiwuJson = new YiwuJson<>();
	
	@Override
	public YiwuJson<SumRecordApiView> getPerformance(int distributerId) {
		SumRecordApiView apiView = new SumRecordApiView();
		apiView.setDistributerId(distributerId);
		apiView.setMyShareTweetTimes(expRecordDao.findMyShareTweetTimes(distributerId));
		apiView.setSubordinateShareTweetTimes(expRecordDao.findSubordinateShareTweetTimes(distributerId));
		apiView.setSubordinateCount(expRecordDao.findSubordinateRegisterTimes(distributerId));
		apiView.setSecondaryCount(expRecordDao.findSecondaryRegisterTimes(distributerId));
		apiView.setSubordinateOrderCount(moneyRecordDao.findSubordinateOrderTimes(distributerId));
		apiView.setSecondaryOrderCount(moneyRecordDao.findSecondaryOrderTimes(distributerId));
		mYiwuJson.setData(apiView);
		return mYiwuJson;
	}

}
