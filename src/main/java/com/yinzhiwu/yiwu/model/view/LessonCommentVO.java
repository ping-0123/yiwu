package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.yinzhiwu.yiwu.entity.LessonComment;
import com.yinzhiwu.yiwu.entity.LessonComment.CommentType;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
*@Author ping
*@Time  创建时间:2017年10月10日下午4:23:18
*
*/

@ApiModel(description="课程评论")
@MapedClass(LessonComment.class)
public class LessonCommentVO {
	
	@NotNull
	@ApiModelProperty(value="评论的课时Id", required=true)
	@MapedProperty("lesson.id")
	private Integer lessonId;

	@MapedProperty("commenter.id")
	@ApiModelProperty(value="评论者Id")
	private Integer commenterId;
	
	@MapedProperty(value="commenter.name", inverse=false)
	@ApiModelProperty(value="评论者姓名")
	private String commenterName;
	
	@NotNull
	@ApiModelProperty(value="评论的类型",allowableValues="{FIRST,APPEND,REPLY}", required=true)
	private CommentType type;
	
	@ApiModelProperty(value="评论星级", allowableValues="{range[1,5]}")
	private Integer stars;
	
	@NotNull
	@ApiModelProperty(value="评论的内容", required=true)
	private String comment;
	
	@ApiModelProperty(value="评论时间")
	private Date date;
	
	@MapedProperty("reAppraise.id")
	@ApiModelProperty(value="被回复的评论的Id")
	private Integer reAppraiseId;
	
	@MapedProperty(value="reAppraise.commenter.name", inverse=false)
	@ApiModelProperty(value="被回复的评论的评论者姓名")
	private String repliedCommenterName;
	
	public static class LessonCommentVOConverter extends AbstractConverter<LessonComment, LessonCommentVO>{
		public static final LessonCommentVOConverter instance = new LessonCommentVOConverter();
	}
	
	public Integer getLessonId() {
		return lessonId;
	}
	public Integer getCommenterId() {
		return commenterId;
	}
	public String getCommenterName() {
		return commenterName;
	}
	public CommentType getType() {
		return type;
	}
	public Integer getStars() {
		return stars;
	}
	public String getComment() {
		return comment;
	}
	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}
	public void setCommenterId(Integer commenterId) {
		this.commenterId = commenterId;
	}
	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}
	public void setType(CommentType type) {
		this.type = type;
	}
	public void setStars(Integer stars) {
		this.stars = stars;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getReAppraiseId() {
		return reAppraiseId;
	}
	public String getRepliedCommenterName() {
		return repliedCommenterName;
	}
	public void setReAppraiseId(Integer reAppraiseId) {
		this.reAppraiseId = reAppraiseId;
	}
	public void setRepliedCommenterName(String repliedCommenterName) {
		this.repliedCommenterName = repliedCommenterName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
