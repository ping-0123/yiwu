package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.ExpRecord;
import com.yinzhiwu.springmvc3.entity.ShareTweet;

public interface ExpRecordService extends IBaseService<ExpRecord, Integer>{
	
	public void saveSubordinateRegisterExpRecord(Distributer beneficiary, Distributer subordinate);
	
	public void saveSecondaryRegisterExpRecord(Distributer beneficiaty, Distributer secondary);
	
	public void saveShareTweetExprRecord(Distributer sharer, ShareTweet shareTweet);
}
