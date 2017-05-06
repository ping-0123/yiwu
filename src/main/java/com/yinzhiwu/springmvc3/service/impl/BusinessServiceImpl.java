package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.ExpRecordDao;
import com.yinzhiwu.springmvc3.dao.MoneyRecordDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.model.DistributerApiView;
import com.yinzhiwu.springmvc3.model.SumRecordApiView;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.BusinessService;


@Service
public class BusinessServiceImpl implements BusinessService{
	
	@Autowired 
	private ExpRecordDao expRecordDao;
	
	@Autowired
	private MoneyRecordDao moneyRecordDao;
	
	@Autowired
	private DistributerDao distributerDao;

	private YiwuJson<SumRecordApiView> mYiwuJson = new YiwuJson<>();
	
	@Override
	public YiwuJson<SumRecordApiView> getPerformance(int distributerId) {
		mYiwuJson.setData(wrapToSumRecordApiView(
							distributerDao.get(distributerId)));
		return mYiwuJson;
	}

	@Override
	public YiwuJson<List<SumRecordApiView>> getTopThreePerfomance() {
		List<Distributer> distributers = distributerDao.findTopThree();
		List<SumRecordApiView> recordApiViews = new ArrayList<SumRecordApiView>();
		for (Distributer d : distributers) {
			recordApiViews.add(wrapToSumRecordApiView(d));
		}
		return new YiwuJson<List<SumRecordApiView>>(recordApiViews);
	}

	private SumRecordApiView wrapToSumRecordApiView(Distributer d) {
		SumRecordApiView apiView = new SumRecordApiView();
		apiView.setDistributerApiView(new DistributerApiView(d));
		apiView.setAccumulativeBrokerage(d.getAccumulativeBrokerage());
		apiView.setMyShareTweetTimes(expRecordDao.findMyShareTweetTimes(d.getId()));
		apiView.setSubordinateShareTweetTimes(expRecordDao.findSubordinateShareTweetTimes(d.getId()));
		apiView.setSubordinateCount(expRecordDao.findSubordinateRegisterTimes(d.getId()));
		apiView.setSecondaryCount(expRecordDao.findSecondaryRegisterTimes(d.getId()));
		apiView.setSubordinateOrderCount(moneyRecordDao.findSubordinateOrderTimes(d.getId()));
		apiView.setSecondaryOrderCount(moneyRecordDao.findSecondaryOrderTimes(d.getId()));
		return apiView;
	}

}
