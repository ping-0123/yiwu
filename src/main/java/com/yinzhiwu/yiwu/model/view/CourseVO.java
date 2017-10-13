package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Converter;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseStatus;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.service.LessonYzwService;
import com.yinzhiwu.yiwu.util.SpringUtils;
import com.yinzhiwu.yiwu.util.convert.annotation.BeanClass;
import com.yinzhiwu.yiwu.util.convert.annotation.BeanProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@BeanClass(CourseYzw.class)
@ApiModel(description="课程VO")
public class CourseVO {
	
	@BeanProperty
	private String id;
	
	@BeanProperty
	@ApiModelProperty("课程名")
	private String name;
	
	@BeanProperty("dance.id")
	@ApiModelProperty("舞种Id")
	private String danceId;
	
	@BeanProperty("dance.name")
	@ApiModelProperty("舞种名")
	private String danceName;
	
	@BeanProperty
	@ApiModelProperty("舞种等级")
	private String danceGrade;
	
	@BeanProperty("store.id")
	@ApiModelProperty("门店Id")
	private Integer storeId;
	
	@BeanProperty("teacher.id")
	@ApiModelProperty("教练Id")
	private Integer teacherId;

	@BeanProperty
	@ApiModelProperty("课程开始日期")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date startDate;
	
	@BeanProperty
	@ApiModelProperty("课程结束日期")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date endDate;
	
	@BeanProperty
	@ApiModelProperty(value="课程类型", allowableValues="{PRIVATE, CLOSED,OPENED}")
	private CourseType courseType;
	
	@BeanProperty
	@ApiModelProperty(value="课程状态", allowableValues="{UN_ARRANGED,UN_CHECKED,READY}")
	private CourseStatus courseStatus;
	
	@BeanProperty
	@ApiModelProperty("上该课程的学员数量")
	private Integer studentCount;
	
	@BeanProperty
	@ApiModelProperty("总课时数")
	private Integer sumLessonTimes;
	
	@BeanProperty
	@ApiModelProperty("课程内容信息")
	private CourseConnotationVO connotation;
	
	@ApiModelProperty("是否为即将上课")
	private boolean comming;
	
	public CourseVO() {
	};

	public String getId() {
		return id;
	}

	public String getDanceName() {
		return danceName;
	}

	public String getDanceGrade() {
		return danceGrade;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDanceName(String danceName) {
		this.danceName = danceName;
	}

	public void setDanceGrade(String danceGrade) {
		this.danceGrade = danceGrade;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public static CourseVO fromDAO(CourseYzw dao){
		return DTOCoverter.instance.reverse().convert(dao);
	}
	
	public static CourseYzw toDAO(CourseVO dto){
		return DTOCoverter.instance.convert(dto);
	}
	
	private static class DTOCoverter extends Converter<CourseVO, CourseYzw>{

		public static final DTOCoverter instance = new DTOCoverter();
		
		@Override
		protected CourseYzw doForward(CourseVO a) {
			CourseYzw course = new CourseYzw();
			BeanUtils.copyProperties(a, course);
			return course;
		}

		@Override
		protected CourseVO doBackward(CourseYzw po) {
			CourseVO vo = new CourseVO();
			BeanUtils.copyProperties(po, vo);
			LessonYzwService service = SpringUtils.getBean(LessonYzwService.class);
			vo.setComming(service.findComingLessonByCourseId(po.getId())!=null);
			if(po.getDance() != null){
				vo.setDanceId(po.getDance().getId());
				vo.setDanceName(po.getDance().getName());
			}
			vo.setStoreId(po.getStore()==null?null:po.getStore().getId());
			vo.setTeacherId(po.getTeacher()==null?null:po.getTeacher().getId());
			
			if(po.getConnotation() !=null){
				vo.setConnotation(CourseConnotationVO.fromPO(po.getConnotation()));
			}
			return vo;
		}
		
	}

	public boolean isComming() {
		return comming;
	}

	public void setComming(boolean comming) {
		this.comming = comming;
	}

	public String getName() {
		return name;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public CourseStatus getCourseStatus() {
		return courseStatus;
	}

	public Integer getStudentCount() {
		return studentCount;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public void setCourseStatus(CourseStatus courseStatus) {
		this.courseStatus = courseStatus;
	}

	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}

	public String getDanceId() {
		return danceId;
	}

	public void setDanceId(String danceId) {
		this.danceId = danceId;
	}

	public Integer getSumLessonTimes() {
		return sumLessonTimes;
	}

	public void setSumLessonTimes(Integer sumLessonTimes) {
		this.sumLessonTimes = sumLessonTimes;
	}

	public CourseConnotationVO getConnotation() {
		return connotation;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setConnotation(CourseConnotationVO connotation) {
		this.connotation = connotation;
	}

}
