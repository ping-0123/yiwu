package com.yinzhiwu.yiwu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.entity.income.ShareTweetEvent;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.ShareTweetEventService;

@RestController
@RequestMapping("/api/event/shareTweet")
public class ShareTweetController extends BaseController {

	@Autowired
	private ShareTweetEventService shareTweetEventService;
	
	@PostMapping
	public YiwuJson<Boolean> doPost(int distributerId, int tweetId){
		Distributer d = new Distributer();
		d.setId(distributerId);
		Tweet tweet = new Tweet();
		tweet.setId(tweetId);
		
		ShareTweetEvent event = new ShareTweetEvent(d, null, 1f);
		event.init();
		event.setTweet(tweet);
		
		try{
			shareTweetEventService.save(event);
			return new YiwuJson<>(new Boolean(true));
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	@GetMapping("/count")
	public YiwuJson<Integer> findCount(int distributerId){
		try{
			int count = shareTweetEventService.findCountByProperty("distributer.id", distributerId);
			return new YiwuJson<>(new Integer(count));
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
}
