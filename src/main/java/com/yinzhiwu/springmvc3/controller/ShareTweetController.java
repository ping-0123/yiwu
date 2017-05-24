package com.yinzhiwu.springmvc3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.TweetShareApiView;
import com.yinzhiwu.springmvc3.service.ShareTweetService;

@RestController
@RequestMapping("/api/tweet/share")
public class ShareTweetController {

	@Autowired
	private ShareTweetService shareTweetService;
	
	@PostMapping("")
	public YiwuJson<Boolean> share(int distributerId, int tweetId){
		return shareTweetService.shareByWechat(distributerId,tweetId);
	}
	
	@GetMapping("/list")
	public YiwuJson<List<TweetShareApiView>> findListBySharerByBeneficiary(int sharerId, int beneficiaryId){
		return shareTweetService.findListBysharerByBeneficiary(sharerId, beneficiaryId);
	}
}
