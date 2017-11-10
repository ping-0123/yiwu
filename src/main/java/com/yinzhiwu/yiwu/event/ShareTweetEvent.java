package com.yinzhiwu.yiwu.event;

import org.springframework.context.ApplicationEvent;

import com.yinzhiwu.yiwu.entity.ShareTweet;

/**
*@Author ping
*@Time  创建时间:2017年11月9日下午5:23:31
*
*/

@SuppressWarnings("serial")
public class ShareTweetEvent extends ApplicationEvent implements IncomeEvent {

	public ShareTweetEvent(ShareTweet shareTweet) {
		super(shareTweet);
	}

	@Override
	public IncomeEventType getType() {
		if(((ShareTweet)getSource()).getOrdinalNo()<=3)
			return IncomeEventType.SHARE_TWEET_BY_WECHAT_FIRST_THREE_TIMES_PER_DAY;
		else
			return IncomeEventType.SHARE_TWEET_BY_WECHAT_AFTER_THREE_TIMES_PER_DAY;
	}

	@Override
	public String getSourceId() {
		return ((ShareTweet)getSource()).getId().toString();
	}

	@Override
	public Object getSubject() {
		return ((ShareTweet)getSource()).getDistributer();
	}

	@Override
	public Float getValue() {
		return 1f;
	}
	
	

}
