package com.yinzhiwu.springmvc3.service.impl;

import java.awt.FontFormatException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
				distributerId, CalendarUtil.getTodayZeroTime());
		if(count <3)
			expRecordService.saveShareTweetExprRecord(sharer, shareTweet);
		return new YiwuJson<>(new Boolean(true));
	}
	
}
