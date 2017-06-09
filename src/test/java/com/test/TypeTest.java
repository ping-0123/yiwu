package com.test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.springmvc3.entity.type.BaseType;
import com.yinzhiwu.springmvc3.entity.type.BrokerageRecordType;
import com.yinzhiwu.springmvc3.entity.type.CapitalAccountType;
import com.yinzhiwu.springmvc3.entity.type.ExpRecordType;
import com.yinzhiwu.springmvc3.entity.type.FundsRecordType;
import com.yinzhiwu.springmvc3.entity.type.TweetType;
import com.yinzhiwu.springmvc3.service.BaseTypeService;
import com.yinzhiwu.springmvc3.service.RecordTypeService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TypeTest {

	@Autowired
	public RecordTypeService recordTypeService;
	
	@Autowired
	public BaseTypeService baseTypeService;

	//@Test
	public void saveFundsRecordTypes(){
		FundsRecordType fundsRecordType = new FundsRecordType("注册", "一级客户注册获取50基金", 50F);
		recordTypeService.save(fundsRecordType);
	}
	
//	@Test
	public void insertRecordTypes(){
		//佣金
		BrokerageRecordType b1 = new BrokerageRecordType("提成","一级客首次购买音之舞产品提成", 0.05F);
		BrokerageRecordType  b2 = new BrokerageRecordType("提成", "二级客户首次购买音之舞产品提成", 0.02F);
		BrokerageRecordType b3 = new BrokerageRecordType("提成", "一级客户非首次购买音之舞产品提成", 0.02F);
		BrokerageRecordType b4 = new BrokerageRecordType("提成", "二级客户非首次购买音之舞产品提成", 0.01F);
		BrokerageRecordType b5 = new BrokerageRecordType("提现","将钱包里的前转到支付宝或微信账户", 1);
		
		FundsRecordType f1 = new FundsRecordType("利息", "存在钱包中的拥挤产生的利息", 0.1F);
		
		ExpRecordType e1 = new ExpRecordType("分享推文", "本人分享推文一次获得的经验值", 50);
		ExpRecordType e2 = new ExpRecordType("分享推文", "一级客户分享推文一次获取的经验值", 10);
		
		recordTypeService.save(b1);
		recordTypeService.save(b2);
		recordTypeService.save(b3);
		recordTypeService.save(b4);
		recordTypeService.save(b5);
		recordTypeService.save(f1);
		recordTypeService.save(e1);
		recordTypeService.save(e2);
	}
	
	@Test
	public  void intertPayRecordType(){
		BrokerageRecordType b1 = new BrokerageRecordType("支出" , "使用佣金支付定金" ,1);
		FundsRecordType f1 = new FundsRecordType("支出", " 使用基金支付定金", 1);
		recordTypeService.save(b1);
		recordTypeService.save(f1);
	}
	
//	@Test
	public void insertOhtertypes(){
		CapitalAccountType c1 = new CapitalAccountType("微信");
		CapitalAccountType c2 = new CapitalAccountType("支付宝");
		
		java.util.List<BaseType>  baseTypes = new ArrayList<>();
		baseTypes.add(c1);
		baseTypes.add(c2);
		
		baseTypes.add(new TweetType("产品"));
		baseTypes.add(new TweetType("市场活动"));
		baseTypes.add(new TweetType("优惠促销"));
		baseTypes.add(new TweetType("比赛表演"));
		baseTypes.add(new TweetType("新闻"));
		baseTypes.add(new TweetType("少儿软文"));
		baseTypes.add(new TweetType("成人软文"));
		baseTypes.add(new TweetType("其他"));
		
		for (BaseType baseType : baseTypes) {
			baseTypeService.save(baseType);
		}
	}
	
	@Test
	public void initTypes(){
		
		Field[] fields = CapitalAccountType.class.getDeclaredFields();
		
	}
}
