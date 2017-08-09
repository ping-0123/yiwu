package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.type.TweetType;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.TweetTypeApiView;

public interface TweetTypeService extends IBaseService<TweetType, Integer> {

	YiwuJson<List<TweetTypeApiView>> findAllTweetTypes();

}
