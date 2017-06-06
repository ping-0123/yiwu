package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.ShareTweetDao;
import com.yinzhiwu.springmvc3.dao.TweetDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.ShareTweet;
import com.yinzhiwu.springmvc3.entity.Tweet;
import com.yinzhiwu.springmvc3.entity.WeChatShareTweet;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.TweetShareApiView;
import com.yinzhiwu.springmvc3.service.ExpRecordService;
import com.yinzhiwu.springmvc3.service.ShareTweetService;
import com.yinzhiwu.springmvc3.util.CalendarUtil;

@Service
public class ShareTweetServiceImpl extends BaseServiceImpl<ShareTweet, Integer>
	implements ShareTweetService{
	private static Log log = LogFactory.getLog(ShareTweetServiceImpl.class);
	
	
	@Autowired
	private ShareTweetDao shareTweetDao;
	
	@Autowired
	private DistributerDao distributerDao;
	
	@Autowired
	private TweetDao tweetDao;
	
	@Autowired
	private ExpRecordService expRecordService;
	
	@Autowired
	public void setBaseDao(ShareTweetDao shareTweetDao){
		super.setBaseDao(shareTweetDao);
	}

	@Override
	public YiwuJson<Boolean> shareByWechat(int distributerId, int tweetId) {
		try{
			Distributer sharer = distributerDao.get(distributerId);
			if(sharer == null)
				return new YiwuJson<>("无效的分享者 " + distributerId);
			Tweet tweet = tweetDao.get(tweetId);
			if(tweet == null)
				return new YiwuJson<>("无效的推文 " + tweetId);
			
			//1.保存一条shareTweet的记录
			ShareTweet  shareTweet= new WeChatShareTweet();
			shareTweet.setSharer(sharer);
			shareTweet.setTweet(tweet);
			try{
				shareTweetDao.save(shareTweet);
			}catch (Exception e) {
				log.debug(e.getMessage());
				return new YiwuJson<>("保存失败: " + e.getMessage() );
			}
			
			//2.产生经验收益
				//2.1如果是当天前三次， 则产生经验收益
			int count = shareTweetDao.findCountBySharerByDate(
					distributerId, CalendarUtil.getTodayBegin().getTime());
			if(count <=3)
				expRecordService.saveShareTweetExprRecord(sharer, shareTweet);
			return new YiwuJson<>(new Boolean(true));
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	@Override
	public YiwuJson<List<TweetShareApiView>> findListBysharerByBeneficiary(int sharerId, int beneficiaryId) {
		try{
			List<ShareTweet> shareTweets = shareTweetDao.findBySharerId(sharerId);
			if(shareTweets.size() ==0 )
				return new YiwuJson<>(sharerId + "： 尚未分享消息");
			Distributer beneficiary = distributerDao.get(beneficiaryId);
			if(beneficiary == null)
				return new YiwuJson<>("不存在id为:" + beneficiaryId + "的用户, 请传入正确的受益者Id" );
			
			List<TweetShareApiView> views = new ArrayList<>();
			for (ShareTweet shareTweet : shareTweets) {
				views.add(new TweetShareApiView(shareTweet,beneficiary));
			}
			
			return new YiwuJson<>(views);
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	@Override
	public YiwuJson<List<TweetShareApiView>> findListByMySubordinates(int sharerId) {
		try{
			Distributer beneficiary = distributerDao.get(sharerId);
			if(beneficiary == null)
				return new YiwuJson<>("不存在id为:" + sharerId + "的用户, 请传入正确的受益者Id" );
			List<Distributer> subordiates = beneficiary.getSubordinates();
			if(subordiates.size() == 0 )
				return new YiwuJson<>("分享者：" + beneficiary.getName() + " 没有下级客户");
			
			//获取一级客户分享的推文
			List<ShareTweet> shareTweets = new ArrayList<>();
			for (Distributer distributer : subordiates) {
				shareTweets.addAll(distributer.getShareTweets());
			}
			
			//包装成DTO
			List<TweetShareApiView> views = new ArrayList<>();
			for (ShareTweet shareTweet : shareTweets) {
				views.add(new TweetShareApiView(shareTweet,beneficiary));
			}
			
			return new YiwuJson<>(views);
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
}
