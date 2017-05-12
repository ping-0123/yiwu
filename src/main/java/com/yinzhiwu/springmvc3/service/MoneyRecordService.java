package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.MoneyRecord;
import com.yinzhiwu.springmvc3.entity.MoneyRecordType;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;
import com.yinzhiwu.springmvc3.enums.MoneyRecordCategory;
import com.yinzhiwu.springmvc3.model.MoneyRecordApiView;
import com.yinzhiwu.springmvc3.model.PayDepositModel;
import com.yinzhiwu.springmvc3.model.WithDrawModel;
import com.yinzhiwu.springmvc3.model.YiwuJson;

public interface MoneyRecordService extends IBaseService<MoneyRecord, Integer>{
	
	public void saveRegisterFundsRecord(Distributer beneficiary, Distributer contributor);

	void _save_money_record(Distributer beneficiary, Distributer contributor, float value, MoneyRecordType type);

	public YiwuJson<Integer> findCountByDistributerid(int distributerId);

	public YiwuJson<List<MoneyRecordApiView>> findList(int benificiaryId, MoneyRecordCategory category);


	public YiwuJson<Boolean> saveWithdraw(WithDrawModel m);

	public YiwuJson<Boolean> payDeposit(PayDepositModel m);

	void saveCommissionRecord();

}
