package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.ShareTweet;
import com.yinzhiwu.springmvc3.model.YiwuJson;

public interface ShareTweetService extends IBaseService<ShareTweet, Integer>{


	YiwuJson<Boolean> shareByWechat(int distributerId, int tweetId);

}
