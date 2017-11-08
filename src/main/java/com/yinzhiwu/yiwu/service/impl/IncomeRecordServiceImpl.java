package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.DistributerIncomeDao;
import com.yinzhiwu.yiwu.dao.IncomeFactorDao;
import com.yinzhiwu.yiwu.dao.IncomeRecordDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.income.AppointmentEvent;
import com.yinzhiwu.yiwu.entity.income.IncomeEvent;
import com.yinzhiwu.yiwu.entity.income.IncomeFactor;
import com.yinzhiwu.yiwu.entity.income.IncomeRecord;
import com.yinzhiwu.yiwu.entity.income.UnAppointmentEvent;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw.AppointStatus;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.IncomeRecordApiView;
import com.yinzhiwu.yiwu.model.view.ShareTweetIncomeRecordApiView;
import com.yinzhiwu.yiwu.service.DistributerIncomeService;
import com.yinzhiwu.yiwu.service.IncomeRecordService;
import com.yinzhiwu.yiwu.service.MessageService;

@Service
public class IncomeRecordServiceImpl extends BaseServiceImpl<IncomeRecord, Integer> implements IncomeRecordService {

	@Autowired
	private DistributerIncomeService dIncomeService;

	@Autowired
	DistributerIncomeDao dIncomeDao;

	@Autowired
	IncomeRecordDao incomeRecordDao;

	@Autowired
	private MessageService messageService;

	@Autowired
	private IncomeFactorDao incomeFactorDao;

	@Autowired
	public void setBaseDao(IncomeRecordDao incomeRecordDao) {
		super.setBaseDao(incomeRecordDao);
	}

	@Override
	public Integer save(IncomeRecord incomeRecord) {
		Assert.notNull(incomeRecord);
		Assert.notNull(incomeRecord.getBenificiary());
		Assert.notNull(incomeRecord.getIncomeType());

		incomeRecord.setCurrentValue(incomeRecord.getIncomeValue() 
				+ dIncomeDao.findCurrentValue(
						incomeRecord.getBenificiary().getId(), 
						incomeRecord.getIncomeType().getId()));
		super.save(incomeRecord);
		dIncomeService.update_by_record(incomeRecord);
		if (IncomeType.BROKERAGE.equals(incomeRecord.getIncomeType()))
			messageService.save_by_record(incomeRecord);
		return incomeRecord.getId();
	}

	@Override
	public void save_records_produced_by_event(IncomeEvent event) {
		List<IncomeFactor> factors =  incomeFactorDao.findByEventType(event.getType());
		for (IncomeFactor factor : factors) {
			Distributer benificiary = factor.getRelation().getRelativeDistributer(event.getDistributer());
			if (benificiary != null && factor.getFactor() != 0f && event.getParam() != 0) {
				IncomeRecord record = new IncomeRecord(event, factor, benificiary);
				this.save(record);
			}
		}
	}

	@Override
	public int findCountByIncomeTypesByBeneficiary(int distributerId, int[] incomeTypeIds) {
		return incomeRecordDao.findCountByIncomeTypesByBeneficiary(distributerId, incomeTypeIds);
	}

	@Override
	public List<IncomeRecordApiView> getListFaster(int observerId, int eventTypeId, int relationTypeId,
			int incomeTypeId) {
		return incomeRecordDao.getListFaster(observerId, eventTypeId, relationTypeId, incomeTypeId);
	}

	@Override
	public List<ShareTweetIncomeRecordApiView> getShareTweetRecordApiViews(int beneficiaryId, int[] eventTypeIds,
			int[] relationTypeIds, int[] incomeTypeIds) {
		return incomeRecordDao.getShareTweetRecordApiViews(beneficiaryId, eventTypeIds, relationTypeIds, incomeTypeIds);
	}

	@Override
	public YiwuJson<Long> findCountBy_incomeTypes_relationTypes_eventTypes_benificiary(int observerId,
			List<Integer> eventTypeIds, List<Integer> relationTypeIds, List<Integer> incomeTypeIds) {
		Long count = incomeRecordDao.findCountBy_incomeTypes_relationTypes_eventTypes_benificiary(observerId, eventTypeIds, relationTypeIds, incomeTypeIds);
		return YiwuJson.createBySuccess(count);
	}
	
	@EventListener(classes={LessonAppointmentYzw.class})
	public void handleLessonAppointment(LessonAppointmentYzw appointment){
		IncomeEvent event;
		if(AppointStatus.APPONTED == appointment.getStatus())
			event = new AppointmentEvent(appointment.getDistributer(),  appointment.getLesson());
		else
			event = new UnAppointmentEvent(appointment.getDistributer(),  appointment.getLesson());
//		this.save(event);
	}
}
