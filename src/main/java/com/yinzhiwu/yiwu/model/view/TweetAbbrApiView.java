package com.yinzhiwu.yiwu.model.view;

import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.enums.TweetType;
import com.yinzhiwu.yiwu.service.FileService;
import com.yinzhiwu.yiwu.util.SpringUtils;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

@MapedClass(Tweet.class)
public class TweetAbbrApiView {
	
	@MapedProperty("id")
	private Integer tweetId;

	@MapedProperty("coverImage")
	private String coverIconUrl;

	private String title;

	private String digest;

	private TweetType type;
	
	public static final class TweetAbbrApiViewConverter extends AbstractConverter<Tweet, TweetAbbrApiView>{
		public static final TweetAbbrApiViewConverter INSTANCE = new TweetAbbrApiViewConverter();
		
		private final FileService fileService = SpringUtils.getBean("fileServiceImpl");

		@Override
		public TweetAbbrApiView fromPO(Tweet po) {
			TweetAbbrApiView vo =  super.fromPO(po);
			vo.setCoverIconUrl(fileService.generateFileUrl(vo.getCoverIconUrl()));
			return vo;
		}
		
		
	}
	
	public TweetAbbrApiView() {
	};

	public Integer getTweetId() {
		return tweetId;
	}

	public String getCoverIconUrl() {
		return coverIconUrl;
	}

	public String getTitle() {
		return title;
	}

	public String getDigest() {
		return digest;
	}

	public void setTweetId(int tweetId) {
		this.tweetId = tweetId;
	}

	public void setCoverIconUrl(String coverIconUrl) {
		this.coverIconUrl = coverIconUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public TweetType getType() {
		return type;
	}

	public void setTweetId(Integer tweetId) {
		this.tweetId = tweetId;
	}

	public void setType(TweetType type) {
		this.type = type;
	}

}
