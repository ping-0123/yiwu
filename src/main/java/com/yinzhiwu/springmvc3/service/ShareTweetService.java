package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.ShareTweet;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.TweetShareApiView;

public interface ShareTweetService extends IBaseService<ShareTweet, Integer>{


	YiwuJson<Boolean> shareByWechat(int distributerId, int tweetId);

	YiwuJson<List<TweetShareApiView>> findListBysharerByBeneficiary(int sharerId, int beneficiaryId);

}
