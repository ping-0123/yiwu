package com.yinzhiwu.springmvc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.Tweet;
import com.yinzhiwu.springmvc3.entity.income.ShareTweetEvent;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.ShareTweetEventService;

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
