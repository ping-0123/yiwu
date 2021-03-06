package com.yinzhiwu.yiwu.model.view;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.LessonPraise;
import com.yinzhiwu.yiwu.entity.yzw.LessonConnotation;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.service.FileService;
import com.yinzhiwu.yiwu.util.SpringUtils;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
*@Author ping
*@Time  创建时间:2017年10月9日上午11:07:04
*
*/

@MapedClass(LessonYzw.class)
@ApiModel(description="课时VO")
public class LessonVO  {
	
	
	@MapedProperty
	private Integer id;
	
	@MapedProperty
	private String name;
	
	@MapedProperty("course.id")
	private String courseId;
	
	@MapedProperty
	@ApiModelProperty(value="课时的序号， 即第几节课")
	private Integer ordinalNo;
	
	@MapedProperty("lessonDate")
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	@ApiModelProperty(value="课时的上课日期")
	private Date lessonDate;
	
	@MapedProperty
	@ApiModelProperty(value="课时的开始时间")
	private Time startTime;
	
	@MapedProperty
	@ApiModelProperty(value="课时的结束时间")
	private Time endTime;
	
	@MapedProperty("store.id")
	private Integer storeId;
	
	@MapedProperty("store.name")
	@ApiModelProperty(value="上课所在门店名")
	private String storeName;
	
	@MapedProperty(value="store.officialAddress.detailAddress", inverse=false)
	@ApiModelProperty(value="上课所在门店地址", required=false)
	private String storeAddress;
	
	@MapedProperty("dueTeacher.id")
	private Integer dueTeacherId;
	
	@MapedProperty("dueTeacher.name")
	@ApiModelProperty(value="排课上课老师姓名")
	private String dueTeacherName;
	
	@MapedProperty("actualTeacher.id")
	private Integer actualTeacherId;
	
	@MapedProperty("actualTeacher.name")
	@ApiModelProperty(value="实际上课老师姓名")
	private String actualTeacherName;
	
	@MapedProperty(value="connotation", ignored=true)
	@ApiModelProperty(value="课时内容信息")
	private LessonConnotationVO connotation;
	
	@MapedProperty(ignored=true)
	@ApiModelProperty(value="所有点赞人姓名，以逗号分隔", required=false)
	private String praisers;
	
	
	public static final class LessonVOConverter extends AbstractConverter<LessonYzw, LessonVO>{
		public static final LessonVOConverter instance = new LessonVOConverter();
		private static final FileService fileService = SpringUtils.getBean("qiniuServiceImpl");

		@Override
		public LessonVO fromPO(LessonYzw po) {
			LessonVO vo = super.fromPO(po);
			LessonConnotation lc = po.getConnotation();
			if(lc !=null){
				LessonConnotationVO connotation = new LessonConnotationVO();
				connotation.setAudioUrl(fileService.generateFileUrl(lc.getAudioUri()));
				connotation.setPictureUrl(fileService.generateFileUrl(lc.getPictureUri()));
				connotation.setStandardVideoUrl(fileService.generateFileUrl(lc.getStandardVideoUri()));
				connotation.setStandardVideoPosterUrl(fileService.generateFileUrl(lc.getStandardVideoPosterUri()));
				connotation.setPuzzleVideoPosterUrl(fileService.generateFileUrl(lc.getPuzzleVideoPosterUri()));
				connotation.setPuzzleVideoUrl(fileService.generateFileUrl(lc.getPuzzleVideoUri()));
				connotation.setPracticalVideoPosterUrl(fileService.generateFileUrl(lc.getPracticalVideoPosterUri()));
				connotation.setPracticalVideoUrl(fileService.generateFileUrl(lc.getPracticalVideoUri()));
				vo.setConnotation(connotation);
			}
			List<LessonPraise> lps = po.getPraises();
			if(lps.size()>0){
				StringBuilder builder =new StringBuilder();
				for(LessonPraise praise:lps)
					builder.append(praise.getDistributer().getName()).append(",");
				vo.setPraisers(builder.substring(0, builder.length()-1));
			}
			
//			Distributer distributer = UserContext.getDistributer();
//			distributer = SpringUtils.getBean(DistributerService.class).get(3002005);
//			if(null != distributer){
//				LessonPraiseService lpService = SpringUtils.getBean(LessonPraiseService.class);
//				vo.setPraised(null != lpService.findByDistributerIdAndLessonId(distributer.getId(), po.getId()));
//				LessonCommentService lcService = SpringUtils.getBean(LessonCommentService.class);
//				vo.setFirstCommented(lcService.checkFirstComment(distributer.getId(), po.getId()));
//				vo.setAppendCommented(lcService.checkAppendComment(distributer.getId(), po.getId()));
//			}
			
			return vo;
		}
		
		
	}
	
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCourseId() {
		return courseId;
	}
	public Integer getOrdinalNo() {
		return ordinalNo;
	}
	public Date getLessonDate() {
		return lessonDate;
	}
	public Time getStartTime() {
		return startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public void setOrdinalNo(Integer ordinalNo) {
		this.ordinalNo = ordinalNo;
	}
	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	
	public Integer getDueTeacherId() {
		return dueTeacherId;
	}
	public String getDueTeacherName() {
		return dueTeacherName;
	}
	public Integer getActualTeacherId() {
		return actualTeacherId;
	}
	public String getActualTeacherName() {
		return actualTeacherName;
	}
	public void setDueTeacherId(Integer dueTeacherId) {
		this.dueTeacherId = dueTeacherId;
	}
	public void setDueTeacherName(String dueTeacherName) {
		this.dueTeacherName = dueTeacherName;
	}
	public void setActualTeacherId(Integer actualTeacherId) {
		this.actualTeacherId = actualTeacherId;
	}
	public void setActualTeacherName(String actualTeacherName) {
		this.actualTeacherName = actualTeacherName;
	}
	public String getPraisers() {
		return praisers;
	}
	public void setPraisers(String praisers) {
		this.praisers = praisers;
	}


	/**
	 * @return the connotation
	 */
	public LessonConnotationVO getConnotation() {
		return connotation;
	}


	/**
	 * @param connotation the connotation to set
	 */
	public void setConnotation(LessonConnotationVO connotation) {
		this.connotation = connotation;
	}



}
