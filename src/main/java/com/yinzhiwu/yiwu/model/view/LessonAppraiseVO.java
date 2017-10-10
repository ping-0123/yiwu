package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.google.common.base.Converter;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.LessonAppraise;
import com.yinzhiwu.yiwu.entity.LessonAppraise.AppraiseType;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;

import io.swagger.annotations.ApiModelProperty;

/**
*@Author ping
*@Time  创建时间:2017年10月10日下午4:23:18
*
*/

public class LessonAppraiseVO {
	
	@NotNull
	@ApiModelProperty(value="评论的课时Id", required=true)
	private Integer lessonId;
	@NotNull
	@ApiModelProperty(value="评论者Id")
	private Integer commenterId;
	@ApiModelProperty(value="评论者姓名")
	private String commenterName;
	@NotNull
	@ApiModelProperty(value="评论的类型",allowableValues="{FIRST,APPEND,REPLY}")
	private AppraiseType type;
	@ApiModelProperty(value="评论星级", allowableValues="{range[1,5]}")
	
	private Integer stars;
	@NotNull
	@ApiModelProperty(value="评论的内容")
	private String comment;
	@ApiModelProperty(value="评论时间")
	private Date date;
	@ApiModelProperty(value="被回复的评论的Id")
	private Integer reAppraiseId;
	@ApiModelProperty(value="被回复的评论的评论者姓名")
	private String repliedCommenterName;
	
	public static LessonAppraiseVO fromVO(LessonAppraise po){
		return VOConverter.instance.reverse().convert(po);
	}
	
	public static LessonAppraise toPO(LessonAppraiseVO vo){
		return VOConverter.instance.convert(vo);
	}
	
	
	private static class VOConverter extends Converter<LessonAppraiseVO, LessonAppraise>{
		public static final VOConverter instance = new VOConverter();
		
		@Override
		protected LessonAppraise doForward(LessonAppraiseVO vo) {
			LessonAppraise po = new LessonAppraise();
			BeanUtils.copyProperties(vo, po);
			
			LessonYzw lesson = new LessonYzw();
			lesson.setId(vo.getLessonId());
			po.setLesson(lesson);
			
			Distributer distributer = new Distributer();
			distributer.setId(vo.getCommenterId());
			po.setCommenter(distributer);
			
			LessonAppraise reAppraise = new LessonAppraise();
			reAppraise.setId(vo.getReAppraiseId());
			po.setReAppraise(reAppraise);
			return po;
		}

		@Override
		protected LessonAppraiseVO doBackward(LessonAppraise po) {
			LessonAppraiseVO vo = new LessonAppraiseVO();
			if(po.getLesson() !=null){
				vo.setLessonId(po.getLesson().getId());
			}
			if(po.getCommenter() !=null){
				vo.setCommenterId(po.getCommenter().getId());
				vo.setCommenterName(po.getCommenter().getName());
			}
			vo.setType(po.getType());
			vo.setStars(po.getStars());
			vo.setComment(po.getComment());
			vo.setDate(po.getDate());
			if(po.getType()== AppraiseType.REPLY && po.getReAppraise() != null){
				LessonAppraise re = po.getReAppraise();
				vo.setReAppraiseId(re.getId());
				vo.setRepliedCommenterName(re.getCommenter().getName());
			}
			
			return vo;
		}
		
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
	public AppraiseType getType() {
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
	public void setType(AppraiseType type) {
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
