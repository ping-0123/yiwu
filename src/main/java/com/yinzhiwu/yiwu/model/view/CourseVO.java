package com.yinzhiwu.yiwu.model.view;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Converter;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseStatus;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.model.view.LessonVO.LessonVOConverter;
import com.yinzhiwu.yiwu.service.FileService;
import com.yinzhiwu.yiwu.service.LessonYzwService;
import com.yinzhiwu.yiwu.util.SpringUtils;
import com.yinzhiwu.yiwu.util.beanutils.AbstractVO;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@MapedClass(CourseYzw.class)
@ApiModel(description="课程VO")
public class CourseVO extends AbstractVO<CourseYzw, CourseVO> {
	
	@MapedProperty
	private String id;
	
	@MapedProperty
	@ApiModelProperty("课程名")
	private String name;
	
	@MapedProperty("dance.id")
	@ApiModelProperty("舞种Id")
	private String danceId;
	
	@MapedProperty("dance.name")
	@ApiModelProperty("舞种名")
	private String danceName;
	
	@MapedProperty
	@ApiModelProperty("舞种等级")
	private String danceGrade;
	
	@MapedProperty("store.id")
	@ApiModelProperty("门店Id")
	private Integer storeId;
	
	@MapedProperty("teacher.id")
	@ApiModelProperty("教练Id")
	private Integer teacherId;

	@MapedProperty
	@ApiModelProperty("课程开始日期")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date startDate;
	
	@MapedProperty
	@ApiModelProperty("课程结束日期")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date endDate;
	
	@MapedProperty
	@ApiModelProperty(value="课程类型", allowableValues="{PRIVATE, CLOSED,OPENED}")
	private CourseType courseType;
	
	@MapedProperty
	@ApiModelProperty(value="课程状态", allowableValues="{UN_ARRANGED,UN_CHECKED,READY}")
	private CourseStatus courseStatus;
	
	@MapedProperty
	@ApiModelProperty("上该课程的学员数量")
	private Integer studentCount;
	
	@MapedProperty
	@ApiModelProperty("总课时数")
	private Integer sumLessonTimes;
	
	@MapedProperty("connotation")
	@ApiModelProperty("课程内容信息")
	private CourseConnotationVO connotation;
	
	@MapedProperty(ignored=true)
	@ApiModelProperty(value= "是否为即将上课", required = false)
	private boolean comming;
	
	@MapedProperty(ignored=true)
	@ApiModelProperty(value="第一节课的课时内容信息", required =false)
	private LessonConnotationVO firstLessonConnotation;
	
	@Override
	public CourseVO fromPO(CourseYzw po) {
		super.fromPO(po);
		if(this.connotation !=null){
			FileService fileService = SpringUtils.getBean("fileServiceImpl");
			connotation.setAudioUrl(fileService.getFileUrl(connotation.getAudioUrl()));
			connotation.setPictureUrl(fileService.getFileUrl(connotation.getPictureUrl()));
			connotation.setVideoUrl(fileService.getFileUrl(connotation.getVideoUrl()));
			connotation.setVideoPosterUrl(fileService.getFileUrl(connotation.getVideoPosterUrl()));
		}
		List<LessonYzw> lessons = po.getLessons();
		if(lessons.size() >0){
			for (LessonYzw lesson : lessons) {
				if(lesson.getOrdinalNo() !=null && lesson.getOrdinalNo() == 1){
					firstLessonConnotation = LessonVOConverter.instance.fromPO(lesson).getConnotation();
					break;
				}
			}
		}
		return this;
	}
	
	public LessonConnotationVO getFirstLessonConnotation() {
		return firstLessonConnotation;
	}

	public void setFirstLessonConnotation(LessonConnotationVO firstLessonConnotation) {
		this.firstLessonConnotation = firstLessonConnotation;
	}

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
//				vo.setConnotation(CourseConnotationVO.fromPO(po.getConnotation()));
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

	public void setName(String name) {
		this.name = name;
	}

	public void setSumLessonTimes(Integer sumLessonTimes) {
		this.sumLessonTimes = sumLessonTimes;
	}

	public CourseConnotationVO getConnotation() {
		return connotation;
	}

	public void setConnotation(CourseConnotationVO connotation) {
		this.connotation = connotation;
	}

}
