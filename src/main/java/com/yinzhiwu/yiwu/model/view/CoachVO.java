package com.yinzhiwu.yiwu.model.view;

import org.springframework.beans.BeanUtils;

import com.google.common.base.Converter;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.enums.Gender;
import com.yinzhiwu.yiwu.service.impl.FileService;
import com.yinzhiwu.yiwu.util.SpringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
*@Author ping
*@Time  创建时间:2017年10月7日下午3:39:21
*
*/

@ApiModel(description="教练信息")
public class CoachVO {
	
	@ApiModelProperty(value="教练在系统中的员工Id")
	private Integer id;
	@ApiModelProperty(value="教练姓名")
	private String name;
	@ApiModelProperty(value="教练性别")
	private Gender gender;
	@ApiModelProperty(value="个性签名")
	private String signature;
	@ApiModelProperty(value="资历介绍")
	private String resume;
	@ApiModelProperty(value="头像Uri")
	private String portraitUri;
	
	public static CoachVO fromDAO(EmployeeYzw dao){
		return VOConverter.instance.reverse().convert(dao);
	}
	
	public static EmployeeYzw toDAO(CoachVO dto){
		return VOConverter.instance.convert(dto);
	}
	
	private static class VOConverter  extends Converter<CoachVO, EmployeeYzw>{
		public static final VOConverter instance = new VOConverter();
		
		@Override
		protected EmployeeYzw doForward(CoachVO a) {
			EmployeeYzw emp = new EmployeeYzw();
			BeanUtils.copyProperties(a, emp);
			return emp;
		}

		@Override
		protected CoachVO doBackward(EmployeeYzw b) {
			CoachVO coach = new CoachVO();
			BeanUtils.copyProperties(b, coach);
			FileService fileService=SpringUtils.getBean(FileService.class);
			coach.setPortraitUri(fileService.getFileUrl(coach.getPortraitUri()));
			return coach;
		}
		
	}
	
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Gender getGender() {
		return gender;
	}
	public String getSignature() {
		return signature;
	}
	public String getResume() {
		return resume;
	}
	public String getPortraitUri() {
		return portraitUri;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public void setPortraitUri(String portraitUri) {
		this.portraitUri = portraitUri;
	}
	
	
}