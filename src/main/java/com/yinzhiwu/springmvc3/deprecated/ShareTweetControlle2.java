package com.yinzhiwu.springmvc3.deprecated;

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
public class ShareTweetControlle2 {

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
	
	@GetMapping("/list/my")
	public YiwuJson<List<TweetShareApiView>> findListSharedByMe(int sharerId){
		return shareTweetService.findListBysharerByBeneficiary(sharerId, sharerId);
	}
	
	@GetMapping("/list/subordinates")
	public YiwuJson<List<TweetShareApiView>> findListByMySubordinates(int sharerId){
		return shareTweetService.findListByMySubordinates(sharerId);
	}
}
