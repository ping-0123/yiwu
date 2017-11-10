package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.ShareTweetService;
import com.yinzhiwu.yiwu.service.TweetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/shareTweets")
@Api(description="分享推文APIs")
public class ShareTweetApiController extends BaseController {

	@Autowired private ShareTweetService shareTweetService;
	@Autowired private TweetService tweetService;

	@PostMapping
	@ApiOperation(value="分享推文")
	public YiwuJson<Boolean> doShareTweet(int tweetId) throws DataNotFoundException {
		Distributer distributer = UserContext.getDistributer();
		Tweet tweet = tweetService.get(tweetId);
		
		shareTweetService.doShareTweet(distributer, tweet);
		return YiwuJson.createBySuccess();
	}

	@GetMapping("/count")
	@ApiOperation(value="获取分享推文的次数")
	public YiwuJson<Long> findCount() {
		
		Distributer distributer = UserContext.getDistributer();
		
		Long count = shareTweetService.findSumShareTimes(distributer);
		return new YiwuJson<>(count);
	}
}
