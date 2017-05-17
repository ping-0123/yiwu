package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.TweetType;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.TweetTypeApiView;

public interface TweetTypeService extends IBaseService<TweetType, Integer>{

	YiwuJson<List<TweetTypeApiView>> findAllTweetTypes();

}
