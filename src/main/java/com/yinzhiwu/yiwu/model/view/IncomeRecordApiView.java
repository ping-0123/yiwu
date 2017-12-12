package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.ShareTweet;
import com.yinzhiwu.yiwu.entity.income.IncomeRecord;
import com.yinzhiwu.yiwu.enums.IncomeType;
import com.yinzhiwu.yiwu.enums.TweetType;
import com.yinzhiwu.yiwu.event.IncomeEventType;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
import com.yinzhiwu.yiwu.service.ShareTweetService;
import com.yinzhiwu.yiwu.util.SpringUtils;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

import io.swagger.annotations.ApiModelProperty;

@MapedClass(IncomeRecord.class)
public class IncomeRecordApiView {

	private static final Logger LOG = LoggerFactory.getLogger(IncomeRecordApiView.class);

	private int id;

	@MapedProperty("recordTimestamp")
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date date;

	@MapedProperty("eventType")
	private IncomeEventType eventTypeName;

	@ApiModelProperty("即转发记录里的转发人")
	@MapedProperty("contributor.name")
	private String memberName;

	@MapedProperty("contributor.memberCard")
	private String memberId;

	@MapedProperty("contributor.phoneNo")
	private String phoneNo;
	
	@MapedProperty("contributor.customer.name")
	private String customerName;

	@MapedProperty("contributor.superDistributer.name")
	private String superMemberName;

	@MapedProperty("incomeType")
	private IncomeType incomeTypeName;

	private Float incomeValue;

	@MapedProperty("contributedValue")
	private Float payedAmount;

	private Float currentValue;

	@MapedProperty("incomeFactor")
	private Float factor;

	//分享推文字段
	@ApiModelProperty("分享的推文类型")
	@MapedProperty(ignored=true)
	private TweetType tweetType;
	
	@ApiModelProperty("推文标题")
	@MapedProperty(ignored=true)
	private String tweetTitle;
	
	
	public IncomeRecordApiView() {
	}

	public IncomeRecordApiView(IncomeRecord r) {
		Assert.notNull(r);
		Assert.notNull(r.getContributor());

		this.id = r.getId();
		this.date = r.getRecordTimestamp();
		try {
//			this.eventTypeName = r.getIncomeEvent().getType().getName();
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

		this.phoneNo = r.getContributor().getPhoneNo();
		this.customerName = r.getContributor().getCustomer().getName();
		this.memberId = r.getContributor().getMemberCard();
		this.memberName = r.getContributor().getName();
		if (r.getContributor().getSuperDistributer() != null)
			this.superMemberName = r.getContributor().getSuperDistributer().getName();
		if (r.getIncomeType() != null)
//			this.incomeTypeName = r.getIncomeType().getName();
		this.incomeValue = r.getIncomeValue();
		this.payedAmount = r.getContributedValue();
		this.currentValue = r.getCurrentValue();
		this.factor = r.getIncomeFactor();
	}

	public IncomeRecordApiView(int id, Date date, String eventTypeName, String memberName, String memberId,
			 String phoneNo, String customerName,
			String superMemberName, String incomeTypeName, float incomeValue, float payedAmount, float currentValue,
			float factor) {
		super();
		this.id = id;
		this.date = date;
//		this.eventTypeName = eventTypeName;
		this.memberName = memberName;
		this.memberId = memberId;
		this.phoneNo = phoneNo;
		this.customerName  = customerName;
		this.superMemberName = superMemberName;
//		this.incomeTypeName = incomeTypeName;
		this.incomeValue = incomeValue;
		this.payedAmount = payedAmount;
		this.currentValue = currentValue;
		this.factor = factor;
	}
	
	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public String getSuperMemberName() {
		return superMemberName;
	}

	public float getFactor() {
		return factor;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setSuperMemberName(String superMemberName) {
		this.superMemberName = superMemberName;
	}

	public void setFactor(float factor) {
		this.factor = factor;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public float getIncomeValue() {
		return incomeValue;
	}

	public float getPayedAmount() {
		return payedAmount;
	}

	public void setIncomeValue(float incomeValue) {
		this.incomeValue = incomeValue;
	}

	public void setPayedAmount(float payedAmount) {
		this.payedAmount = payedAmount;
	}

	

	public IncomeEventType getEventTypeName() {
		return eventTypeName;
	}

	public IncomeType getIncomeTypeName() {
		return incomeTypeName;
	}

	public Float getCurrentValue() {
		return currentValue;
	}

	public TweetType getTweetType() {
		return tweetType;
	}

	public String getTweetTitle() {
		return tweetTitle;
	}

	public void setEventTypeName(IncomeEventType eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public void setIncomeTypeName(IncomeType incomeTypeName) {
		this.incomeTypeName = incomeTypeName;
	}

	public void setIncomeValue(Float incomeValue) {
		this.incomeValue = incomeValue;
	}

	public void setPayedAmount(Float payedAmount) {
		this.payedAmount = payedAmount;
	}

	public void setCurrentValue(Float currentValue) {
		this.currentValue = currentValue;
	}

	public void setFactor(Float factor) {
		this.factor = factor;
	}

	public void setTweetType(TweetType tweetType) {
		this.tweetType = tweetType;
	}

	public void setTweetTitle(String tweetTitle) {
		this.tweetTitle = tweetTitle;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	
	public static final class  IncomeRecordApiViewConverter extends AbstractConverter<IncomeRecord, IncomeRecordApiView>{
		public final static IncomeRecordApiViewConverter INSTANCE =  new IncomeRecordApiViewConverter();

		@Override
		public IncomeRecordApiView fromPO(IncomeRecord po) {
			IncomeRecordApiView vo =  super.fromPO(po);
			if(IncomeEventType.SHARE_TWEET_BY_WECHAT_FIRST_THREE_TIMES_PER_DAY == vo.getEventTypeName()
				|| IncomeEventType.SHARE_TWEET_BY_WECHAT_AFTER_THREE_TIMES_PER_DAY == vo.getEventTypeName())
			{
				ShareTweetService shareTweetService = SpringUtils.getBean(ShareTweetService.class);
				try {
					ShareTweet shareTweet = shareTweetService.get(Integer.valueOf(po.getEventSourceId()));
					vo.setTweetType(shareTweet.getTweet().getType());
					vo.setTweetTitle(shareTweet.getTweet().getTitle());
				} catch (NumberFormatException e) {
					;
				} catch (DataNotFoundException e) {
					;
				}
			}
			return vo;
		}
		
		
	}
}
