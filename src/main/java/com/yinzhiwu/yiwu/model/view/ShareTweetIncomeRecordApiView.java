package com.yinzhiwu.yiwu.model.view;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.income.IncomeRecord;
import com.yinzhiwu.yiwu.entity.income.ShareTweetEvent;

public class ShareTweetIncomeRecordApiView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3765087348892278100L;

	private static Log LOG = LogFactory.getLog(ShareTweetIncomeRecordApiView.class);

	private int id;

	private String sharer;

	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date shareDate;

	private String tweetType;

	private String tweetTitle;

	private float exp;

	public ShareTweetIncomeRecordApiView() {
	}

	public ShareTweetIncomeRecordApiView(IncomeRecord record) {
		try {
			this.id = record.getId();
			this.sharer = record.getContributor().getName();
			this.shareDate = record.getRecordTimestamp();
			ShareTweetEvent event = (ShareTweetEvent) record.getIncomeEvent();
			this.tweetType = event.getTweet().getTweetType().getName();
			this.tweetTitle = event.getTweet().getTitle();
			this.exp = record.getIncomeValue();

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

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

	public ShareTweetIncomeRecordApiView(int id, String sharer, Date shareDate, String tweetType, String tweetTitle,
			float exp) {
		super();
		this.id = id;
		this.sharer = sharer;
		this.shareDate = shareDate;
		this.tweetType = tweetType;
		this.tweetTitle = tweetTitle;
		this.exp = exp;
	}

	public ShareTweetIncomeRecordApiView(int id, String sharer) {
		this.id = id;
		this.sharer = sharer;
	}
}
