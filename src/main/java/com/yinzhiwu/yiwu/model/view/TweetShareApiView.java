package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TweetShareApiView {
	
	private int id;
	
	private String sharer;
	
	@JsonFormat(pattern="yyyy/MM/dd")
	private Date shareDate;
	
	private String tweetType;
	
	private String tweetTitle;
	
	private float exp;
	
	public TweetShareApiView(){};
	
//	public TweetShareApiView(ShareTweet t, Distributer beneficiary ){
//		if(t== null)
//			return;
//		this.id = t.getId();
//		this.sharer = t.getSharer()==null?null:t.getSharer().getName();
//		this.shareDate = t.getShareDate();
//		this.tweetType = t.getTweet().getTweetType().getName();
//		this.tweetTitle = t.getTweet().getTitle();
//		List<ExpRecord> expRecords = t.getExpRecords();
//		if(expRecords == null || expRecords.size() == 0)
//			this.exp = 0;
//		for (ExpRecord record : expRecords) {
//			if(beneficiary.equals(record.getBeneficiaty())){
//				this.exp = record.getIncome();
//				break;
//			}
//		}
//		
//	}

	public int getId() {
		return id;
	}

	public String getSharer() {
		return sharer;
	}

	public Date getShareDate() {
		return shareDate;
	}

	public String getTweetType() {
		return tweetType;
	}

	public String getTweetTitle() {
		return tweetTitle;
	}

	public float getExp() {
		return exp;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSharer(String sharer) {
		this.sharer = sharer;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}

	public void setTweetType(String tweetType) {
		this.tweetType = tweetType;
	}

	public void setTweetTitle(String tweetTitle) {
		this.tweetTitle = tweetTitle;
	}

	public void setExp(float exp) {
		this.exp = exp;
	}
	
	
}
