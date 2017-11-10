package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.DistributerIncomeDao;
import com.yinzhiwu.yiwu.dao.IncomeFactorDao;
import com.yinzhiwu.yiwu.dao.IncomeRecordDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.PayDeposit;
import com.yinzhiwu.yiwu.entity.ShareTweet;
import com.yinzhiwu.yiwu.entity.income.DistributerIncome;
import com.yinzhiwu.yiwu.entity.income.IncomeFactor;
import com.yinzhiwu.yiwu.entity.income.IncomeRecord;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.event.IncomeEvent;
import com.yinzhiwu.yiwu.event.LessonAppointmentEvent;
import com.yinzhiwu.yiwu.event.LessonCheckInEvent;
import com.yinzhiwu.yiwu.event.PurchaseEvent;
import com.yinzhiwu.yiwu.event.RegisterEvent;
import com.yinzhiwu.yiwu.event.ShareTweetEvent;
import com.yinzhiwu.yiwu.event.WithdrawEvent;
import com.yinzhiwu.yiwu.event.YieldInterestEvent;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.IncomeRecordApiView;
import com.yinzhiwu.yiwu.model.view.ShareTweetIncomeRecordApiView;
import com.yinzhiwu.yiwu.service.DistributerIncomeService;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.service.IncomeRecordService;

@Service
public class IncomeRecordServiceImpl extends BaseServiceImpl<IncomeRecord, Integer> implements IncomeRecordService {

	@Autowired private IncomeRecordDao incomeRecordDao;
	@Autowired private IncomeFactorDao incomeFactorDao;
	@Autowired private OrderYzwDao orderDao;
	@Autowired private DistributerIncomeDao incomedao;
	
	@Autowired private DistributerIncomeService distributerIncomeService;
	@Autowired private DistributerService distributerService;

	@Autowired private ApplicationContext applicationContext;


	@Autowired
	public void setBaseDao(IncomeRecordDao incomeRecordDao) {
		super.setBaseDao(incomeRecordDao);
	}

	/**
	 * {@link MessageServiceImpl#handleBrokerageRecord(IncomeRecord)}
	 */
	@Override
	public Integer save(IncomeRecord record) {
		Assert.notNull(record);

		super.save(record);
		distributerIncomeService.updateIncome(record);
		
		applicationContext.publishEvent(record);

		return record.getId();
	}

	
	@Override
	public void produceIncomes(IncomeEvent event){
		List<IncomeFactor> factors = incomeFactorDao.findByEventType(event.getType());
		for (IncomeFactor factor : factors) {
			IncomeRecord record = createIncomeRecord(event, factor);
			if(null != record)
				save(record);
		}
	}

	private IncomeRecord createIncomeRecord(IncomeEvent event, IncomeFactor factor) {
		Distributer subject =null;
		if(event.getSubject() instanceof Distributer)
			subject = (Distributer) event.getSubject();
		else if(event.getSubject() instanceof CustomerYzw){
			CustomerYzw customer = (CustomerYzw) event.getSubject();
			try {
				subject = distributerService.findbyCustomer(customer);
			} catch (DataNotFoundException e) {
				logger.error("customer " + customer.getId() +  "未注册");
				return null;
			}
		}else {
			throw new UnsupportedOperationException(event.getSubject().getClass() +  " is not the supported subject for income event");
		}
		
		Distributer benificiary = subject.getRelatives(factor.getRelation());
		if(null != benificiary && 0 != factor.getFactor() && 0 != event.getValue()){
			IncomeRecord record = new IncomeRecord();
			record.setEventType(event.getType());
			record.setEventSourceClass(event.getSource().getClass().getSimpleName());
			record.setEventSourceId(event.getSourceId());
			record.setIncomeType(factor.getIncomeType());
			record.setContributor(subject);
			record.setContributedValue(event.getValue());
			record.setBenificiary(benificiary);
			record.setIncomeFactor(factor.getFactor());
			record.setIncomeValue(factor.getFactor() * event.getValue());
			record.setCurrentValue(record.getIncomeValue() + benificiary.getIncomeValue(record.getIncomeType()));
			record.setCon_ben_relation(factor.getRelation());
			
			return record;
		}
		
		return null;
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
	
	@TransactionalEventListener(classes={LessonAppointmentYzw.class})
	public void handleLessonAppointment(LessonAppointmentYzw appointment){
		LessonAppointmentEvent event  = new LessonAppointmentEvent(appointment);
		produceIncomes(event);
	}
	
	@TransactionalEventListener(classes={LessonCheckInYzw.class})
	public void handlerLessonCheckIn(LessonCheckInYzw checkIn){
		IncomeEvent event  = new LessonCheckInEvent(checkIn);
		produceIncomes(event);
	}
	
	@TransactionalEventListener(classes={WithdrawEvent.class})
	public void handleWithdrawBrokerage(WithdrawEvent event){
		produceIncomes(event);
	}
	
	@TransactionalEventListener(classes={ShareTweet.class})
	public void handleShareTweet(ShareTweet shareTweet){
		IncomeEvent event = new ShareTweetEvent(shareTweet);
		produceIncomes(event);
	}
	
	@TransactionalEventListener(classes={PayDeposit.class}, phase= TransactionPhase.BEFORE_COMMIT)
	public void handlePayDeposit(PayDeposit deposit){
		produceIncomes(deposit);
	}
	
	@TransactionalEventListener(classes={RegisterEvent.class})
	public void handleRegisterEvent(RegisterEvent event){
		produceIncomes(event);
	}
	
	@Transactional
	@Scheduled(cron="0 0 4 * * ?") //每日4点
	public void scheduleHandlePurchases(){
		logger.info("start execute handle purchase event ");
		List<OrderYzw> orders = orderDao.findAllLastDayOrders();
		for (OrderYzw order : orders) {
			PurchaseEvent event = new PurchaseEvent(order);
			produceIncomes(event);
		}
	}
	
	@Transactional
	@Scheduled(cron="0 0 3 * * ?")  //每日3点
	public void scheduleYieldInterest(){
		logger.info("start yield interest");
		List<DistributerIncome> incomes = incomedao.findBrokerageIncomes();
		for (DistributerIncome income : incomes) {
			YieldInterestEvent event = new YieldInterestEvent(income);
			produceIncomes(event);
		}
	}
}
