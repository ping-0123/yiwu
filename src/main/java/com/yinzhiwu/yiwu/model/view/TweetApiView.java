package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.enums.TweetType;
import com.yinzhiwu.yiwu.service.FileService;
import com.yinzhiwu.yiwu.util.SpringUtils;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

@MapedClass(Tweet.class)
public class TweetApiView {


	private int id;

	private String title;

	private String author;
	
	private String digest;
	
	@MapedProperty("coverImage")
	private String coverIconUrl;
	
	private TweetType type;
	
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date editDate;

	@MapedProperty(ignored=true)
	private String contentHtml;

	public static final class TweetApiViewConverter extends AbstractConverter<Tweet, TweetApiView>{
		public static final TweetApiViewConverter INSTANCE = new TweetApiViewConverter();
		
		private final FileService fileService = SpringUtils.getBean("fileServiceImpl");

		@Override
		public TweetApiView fromPO(Tweet po) {
			TweetApiView view =  super.fromPO(po);
			view.setCoverIconUrl(fileService.generateFileUrl(view.getCoverIconUrl()));
			view.setContentHtml(new String(po.getContent()));
			return view;
		}

		@Override
		public Tweet toPO(TweetApiView vo) {
			Tweet po = super.toPO(vo);
			po.setContent(vo.getContentHtml().getBytes());
			return po;
		}
		
		
	}
	
	public TweetApiView() {
	}


	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public String getContentHtml() {
		return contentHtml;
	}

	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}


	public String getDigest() {
		return digest;
	}


	public String getCoverIconUrl() {
		return coverIconUrl;
	}


	public TweetType getType() {
		return type;
	}


	public void setDigest(String digest) {
		this.digest = digest;
	}


	public void setCoverIconUrl(String coverIconUrl) {
		this.coverIconUrl = coverIconUrl;
	}


	public void setType(TweetType type) {
		this.type = type;
	}

}
