package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.CapitalAccountDao;
import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.MoneyRecordDao;
import com.yinzhiwu.springmvc3.dao.OrderYzwDao;
import com.yinzhiwu.springmvc3.dao.ProductYzwDao;
import com.yinzhiwu.springmvc3.dao.RecordTypeDao;
import com.yinzhiwu.springmvc3.entity.BrokerageRecord;
import com.yinzhiwu.springmvc3.entity.BrokerageRecordType;
import com.yinzhiwu.springmvc3.entity.CapitalAccount;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.FundsRecord;
import com.yinzhiwu.springmvc3.entity.FundsRecordType;
import com.yinzhiwu.springmvc3.entity.MoneyRecord;
import com.yinzhiwu.springmvc3.entity.MoneyRecordType;
import com.yinzhiwu.springmvc3.entity.WithDrawRecord;
import com.yinzhiwu.springmvc3.entity.yzw.Contract;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;
import com.yinzhiwu.springmvc3.entity.yzw.ProductYzw;
import com.yinzhiwu.springmvc3.enums.MoneyRecordCategory;
import com.yinzhiwu.springmvc3.model.MoneyRecordApiView;
import com.yinzhiwu.springmvc3.model.PayDepositModel;
import com.yinzhiwu.springmvc3.model.WithDrawModel;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.MoneyRecordService;
import com.yinzhiwu.springmvc3.util.GeneratorUtil;
import com.yinzhiwu.springmvc3.util.MoneyRecordCategoryUtil;

@Service
public class MoneyRecordServiceImpl extends BaseServiceImpl<MoneyRecord, Integer> implements MoneyRecordService{
	
	@Autowired
	private MoneyRecordDao moneyRecordDao;
	
	@Autowired
	@Qualifier("recordTypeDaoImpl")
	private RecordTypeDao recordTypeDao;
	
	@Autowired
	private DistributerDao distributerDao;
	
	
	@Autowired
	private CapitalAccountDao capitalAccountDao;

	
	@Autowired
	private OrderYzwDao orderYzwDao;
	
	@Autowired
	private ProductYzwDao productYzwDao;
	
	@Autowired
	private void setMoneyRecordDao(MoneyRecordDao moneyRecordDao)
	{
		super.setBaseDao(moneyRecordDao);
	}
	
	@Override
	public void saveRegisterFundsRecord(Distributer beneficiary, Distributer contributor) {
		FundsRecordType fundsRecordType = recordTypeDao.findRegisterFundsRecordType();
		saveFundsRecord(beneficiary, contributor, 1, fundsRecordType);
	}

	@Override
	public void saveMoneyRecord(Distributer beneficiary,Distributer contributor, float value, MoneyRecordType type )
	{
		if(type instanceof BrokerageRecordType)
			saveBrokerageRecord(beneficiary, contributor, value, (BrokerageRecordType)type);
		else if(type instanceof FundsRecordType)
			saveFundsRecord(beneficiary, contributor, value, (FundsRecordType) type);
	}
	
	
	
	private void saveBrokerageRecord(Distributer beneficiary,Distributer contributor, float value, BrokerageRecordType type )
	{
		BrokerageRecord brokerageRecord = new BrokerageRecord(beneficiary, contributor, value, type);
		moneyRecordDao.save(brokerageRecord);
		if(brokerageRecord.getIncome() >0)
			beneficiary.setAccumulativeBrokerage(beneficiary.getAccumulativeBrokerage() + brokerageRecord.getIncome());
		beneficiary.setBrokerage(brokerageRecord.getCurrentBrokerage());
		distributerDao.update(beneficiary);
	}
	
	
	
	private void saveFundsRecord(Distributer beneficiary,Distributer contributor, float value, FundsRecordType type )
	{
		FundsRecord fundsRecord = new FundsRecord(beneficiary, contributor, value, type);
		moneyRecordDao.save(fundsRecord);
		
		if(fundsRecord.getIncome() > 0)
			beneficiary.setAccumulativeFunds(beneficiary.getAccumulativeFunds() + fundsRecord.getIncome());
		beneficiary.setFunds(fundsRecord.getCurrentFunds());
		distributerDao.update(beneficiary);
	}

	private void saveWithDrawRecord(Distributer beneficiary, Distributer contributor, float value,
			BrokerageRecordType type, CapitalAccount account) 
	{
		WithDrawRecord record = new WithDrawRecord(beneficiary, contributor, value, type);
		record.setAccount(account);
		moneyRecordDao.save(record);
		beneficiary.setBrokerage(record.getCurrentBrokerage());
		distributerDao.update(beneficiary);
		
	}
	
	@Override
	public YiwuJson<Integer> findCountByDistributerid(int distributerId) {
		int count =  moneyRecordDao.findCountByBeneficiatyId(distributerId);
		return new YiwuJson<Integer>(count);
	}

	@Override
	public YiwuJson<List<MoneyRecordApiView>> findList(int benificiaryId, MoneyRecordCategory category) {
		List<Integer> typeIds  = MoneyRecordCategoryUtil.toMoneyRecordTypeIds(category);
		List<MoneyRecord> moneyRecords = moneyRecordDao.findByTypesByBeneficiaryId(benificiaryId,typeIds);
		List<MoneyRecordApiView> views = new ArrayList<MoneyRecordApiView>();
		for (MoneyRecord m : moneyRecords) {
			views.add(new MoneyRecordApiView(m));
		}
		return new YiwuJson<List<MoneyRecordApiView>>(views);
	}

	@Override
	public YiwuJson<Boolean> saveWithdraw(WithDrawModel m) {
		BrokerageRecordType type = recordTypeDao.getWithDrawMoneyRecordType();
		Distributer beneficiary = distributerDao.get(m.getDistributerId());
		Distributer contributor = beneficiary;
		float value = m.getAmount();
		CapitalAccount account = capitalAccountDao.get(m.getAccountId());
		if(account.getDistributer().getId() != beneficiary.getId()){
			return new YiwuJson<>("提现者不是提现帐号的拥有者");
		}
		if(value>beneficiary.getBrokerage())
			return new YiwuJson<>("提现金额大于账户总金额");
		saveWithDrawRecord(beneficiary,contributor,value,type,account);
		return new YiwuJson<>(new Boolean(true));
	}

	@Override
	public YiwuJson<Boolean> payDeposit(PayDepositModel m) {
		float payedFundsValue =0;
		float payedBrokerageValue =0;
		Distributer beneficiary = distributerDao.get(m.getDistributerId());
		Distributer contributor = beneficiary;
		
		//1. 判断
		if(m.isFundsFisrt()){
			if(m.getAmount() <= beneficiary.getFunds()){
				payedFundsValue = m.getAmount();
				payedBrokerageValue = 0;
			}else if (m.getAmount() <= beneficiary.getFunds() + beneficiary.getBrokerage()) {
				payedFundsValue = beneficiary.getFunds();
				payedBrokerageValue = m.getAmount()-beneficiary.getFunds();
			}else {
				return new YiwuJson<>("支付定金金额大于佣金和基金总和");
			}
		}else{
			if(m.getAmount()<=beneficiary.getBrokerage())
				payedBrokerageValue =  m.getAmount();
			else
				return new YiwuJson<>("支付定金金额大于佣金, 如果有基金，请选择优先使用基金");
		}
		
		//2.save order;
		try {
			_save_deposit_order(beneficiary , m.getAmount());
		} catch (Exception e) {
			return new YiwuJson<Boolean>(e.getMessage());
		}
		
		//3.save fundsrecord
		if(payedFundsValue> 0){
			FundsRecordType fundsRecordType=recordTypeDao.getPayFundsRecordType();
		}
		
		
		//4.save brockerageRcord
		
		
		return null;
	}

	
	private void _save_deposit_order(Distributer beneficiary, float deposit_amount) throws Exception {
		//customer
		CustomerYzw customer = beneficiary.getCustomer();
		if (customer == null) 
			throw new  Exception(beneficiary.getName() + ":该分享帐号未与音之舞客户绑定");
		
		//product
		ProductYzw product = null;
		if(beneficiary.isAudit())
			product = productYzwDao.get_audit_deposit_product();
		else
			product = productYzwDao.get_children_deposit_product();
		
		OrderYzw order = new OrderYzw();
		order.setId(GeneratorUtil.generateYzwId(orderYzwDao.find_last_id()));
		order.setProduct(product);
		order.setCustomer(customer);
		order.setMemberCardNo(customer.getMemberCard());
		order.setMarkedPrice((float) product.getMarkedPrice());
		order.setCount(1);
		order.setPayedAmount(deposit_amount);
		order.setDiscount(deposit_amount/order.getMarkedPrice());
		order.setPayedDate(new Date());
		
		//设置会籍合约
		Contract contract = new Contract();
		contract.setStatus("已审核");
	}


}
