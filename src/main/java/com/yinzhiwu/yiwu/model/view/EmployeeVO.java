package com.yinzhiwu.yiwu.model.view;

import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.enums.Gender;
import com.yinzhiwu.yiwu.service.FileService;
import com.yinzhiwu.yiwu.util.SpringUtils;
import com.yinzhiwu.yiwu.util.beanutils.Converter;
import com.yinzhiwu.yiwu.util.beanutils.MapedClassUtils;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

/**
*@Author ping
*@Time  创建时间:2017年10月21日上午10:24:00
*
*/
@MapedClass(EmployeeYzw.class)
public class EmployeeVO {

	private Integer id;
	private String number;
	private String name;
	private Gender gender;
	private String birthday;
	private String telephone;
	private String cellphone;
	private String email;
	private String signature;
	private String resume;
	private String portraitUri;
	@MapedProperty(ignored=true)
	private String portraitUrl;
	
	
	
	public String getPortraitUri() {
		return portraitUri;
	}
	public void setPortraitUri(String portraitUri) {
		this.portraitUri = portraitUri;
	}
	public String getPortraitUrl() {
		return portraitUrl;
	}
	public void setPortraitUrl(String portraitUrl) {
		this.portraitUrl = portraitUrl;
	}
	public Integer getId() {
		return id;
	}
	public String getNumber() {
		return number;
	}
	public String getName() {
		return name;
	}
	public Gender getGender() {
		return gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public String getTelephone() {
		return telephone;
	}
	public String getCellphone() {
		return cellphone;
	}
	public String getEmail() {
		return email;
	}
	public String getSignature() {
		return signature;
	}
	public String getResume() {
		return resume;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setCellphone(String cellPhone) {
		this.cellphone = cellPhone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	
	@MapedProperty(ignored=true)
	public final static Converter<EmployeeYzw, EmployeeVO> converter = new Converter<EmployeeYzw, EmployeeVO>() {

			@Override
			public EmployeeVO fromPO(EmployeeYzw po) {
				EmployeeVO vo = new EmployeeVO();
				MapedClassUtils.copyProperties(po, vo);
				
				FileService fileService = SpringUtils.getBean("qiniuServiceImpl");
				vo.setPortraitUrl(fileService.getFileUrl(vo.getPortraitUri()));
				return vo;
			}

			@Override
			public EmployeeYzw toPO(EmployeeVO vo) {
				EmployeeYzw emp = new EmployeeYzw();
				MapedClassUtils.copyProperties(vo, emp);
				return emp;
			}
	};
}


