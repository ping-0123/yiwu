package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.entity.income.ShareTweetEvent;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.ShareTweetEventService;

@RestController
@RequestMapping("/api/event/shareTweet")
public class ShareTweetApiController extends BaseController {

	@Autowired
	private ShareTweetEventService shareTweetEventService;

	@PostMapping
	public YiwuJson<Boolean> doPost(int distributerId, int tweetId) {
		Distributer d = new Distributer();
		d.setId(distributerId);
		Tweet tweet = new Tweet();
		tweet.setId(tweetId);

		ShareTweetEvent event = new ShareTweetEvent(d, null, 1f);
		event.init();
		event.setTweet(tweet);

		try {
			shareTweetEventService.save(event);
			return new YiwuJson<>(new Boolean(true));
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	@GetMapping("/count")
	public YiwuJson<Long> findCount(int distributerId) {
		try {
			Long count = shareTweetEventService.findCountByDistributerId( distributerId);
			return new YiwuJson<>(count);
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
}
