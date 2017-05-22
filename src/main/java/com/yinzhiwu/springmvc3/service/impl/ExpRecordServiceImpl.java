package com.yinzhiwu.springmvc3.service.impl;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.ExpRecordDao;
import com.yinzhiwu.springmvc3.dao.ExpRecordTypeDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.ExpRecord;
import com.yinzhiwu.springmvc3.entity.ExpRecordType;
import com.yinzhiwu.springmvc3.entity.ShareTweet;
import com.yinzhiwu.springmvc3.service.ExpRecordService;

@Service
public class ExpRecordServiceImpl extends BaseServiceImpl<ExpRecord, Integer> implements ExpRecordService {
	
	@Autowired
	private ExpRecordDao expRecordDao;
	
	@Autowired
	private ExpRecordTypeDao expRecordTypeDao;
	
	@Autowired
	private DistributerDao distrituterDao;

	@Autowired
	public void setExpRecordDao(ExpRecordDao expRecordDao)
	{
		super.setBaseDao(expRecordDao);
	}

	@Override
	public void saveSubordinateRegisterExpRecord(Distributer beneficiaty, Distributer subordinate) {
		ExpRecordType type = expRecordTypeDao.findSubordinateRegisterExpRecordType();
		float value=1;
		_save_exp_record(beneficiaty, subordinate, value, type);
	}

	@Override
	public void saveSecondaryRegisterExpRecord(Distributer beneficiaty, Distributer secondary) {
		ExpRecordType type = expRecordTypeDao.findSecondaryRegisterExpRecordType();
		float value =1;
		_save_exp_record(beneficiaty, secondary, value, type);
		
	}
	

	private void _save_exp_record_with_share_tweet(
			@NotNull Distributer beneficiary,
			@NotNull Distributer contributor, 
			float value, ExpRecordType type, ShareTweet shareTweet ){
		if(beneficiary == null )
			return;
		ExpRecord expRecord = new ExpRecord(beneficiary, contributor, value, type);
		if(shareTweet != null)
			expRecord.setShareTweet(shareTweet);
		expRecordDao.save(expRecord);
		//update beneficiary
		beneficiary.setExp(expRecord.getCurrentExp());
		//如果积分够了就升级
		if(beneficiary.getExp()>=beneficiary.getExpGrade().getUpgradeExp())
			beneficiary.setExpGrade(beneficiary.getExpGrade().getNextGrade());
		distrituterDao.update(beneficiary);
	}
	
	private void _save_exp_record(Distributer beneficiary,Distributer contributor, float value, ExpRecordType type){
		_save_exp_record_with_share_tweet(beneficiary, contributor, value, type, null);
	}

	@Override
	public void saveShareTweetExprRecord(Distributer sharer, ShareTweet shareTweet) {
		
		//本人获取
		ExpRecordType type = expRecordTypeDao.findByShareTweetBySelf();
		_save_exp_record_with_share_tweet(sharer, sharer, 1, type, shareTweet);
		
		//上一级获取
		if(sharer.getSuperDistributer() == null)
			return;
		ExpRecordType type2 = expRecordTypeDao.findByShareTweetBySubordiante();
		_save_exp_record_with_share_tweet(
				sharer.getSuperDistributer(), sharer, 1, type2, shareTweet);
	}
}
