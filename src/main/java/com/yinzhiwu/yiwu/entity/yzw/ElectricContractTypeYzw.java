package com.yinzhiwu.yiwu.entity.yzw;

import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;

@Entity
@Table(name = "velectric_contract_type")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class ElectricContractTypeYzw {
	
	@Converter
	public static class ContractTypeConverter implements AttributeConverter<CourseType, String>{

		@Override
		public String convertToDatabaseColumn(CourseType attribute) {
			if(attribute == null)
				return null;
			return attribute.getName();
		}

		@Override
		public CourseType convertToEntityAttribute(String dbData) {
			if(dbData == null)
				return null;
			return CourseType.fromName(dbData);
		}
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Convert(converter=ContractTypeConverter.class)
	@Column(name="description")
	private CourseType contractType;

	private String content;

	@JsonIgnore
	private Integer sf_create_user;

	@JsonIgnore
	private Integer sf_last_change_user;

	@JsonIgnore
	private Date sf_create_time;

	@JsonIgnore
	private Date sf_last_change_time;

	@JsonIgnore
	private Integer machineCode;

	@JsonIgnore
	private Date sf_last_sync_timeStamp;

	public ElectricContractTypeYzw() {
		this.sf_create_user = 1;
		this.sf_last_change_user = 1;
		Date date = new Date();
		this.sf_create_time = date;
		this.sf_last_change_time = date;
		this.sf_last_sync_timeStamp = date;
	}

	public int getId() {
		return id;
	}


	public String getContent() {
		return content;
	}

	public int getSf_create_user() {
		return sf_create_user;
	}

	public int getSf_last_change_user() {
		return sf_last_change_user;
	}

	public Date getSf_last_change_time() {
		return sf_last_change_time;
	}

	public int getMachineCode() {
		return machineCode;
	}

	public Date getSf_last_sync_timeStamp() {
		return sf_last_sync_timeStamp;
	}

	public void setId(int id) {
		this.id = id;
	}


	public void setContent(String content) {
		this.content = content;
	}

	public void setSf_create_user(int sf_create_user) {
		this.sf_create_user = sf_create_user;
	}

	public void setSf_last_change_user(int sf_last_change_user) {
		this.sf_last_change_user = sf_last_change_user;
	}

	public void setSf_last_change_time(Date sf_last_change_time) {
		this.sf_last_change_time = sf_last_change_time;
	}

	public void setMachineCode(int machineCode) {
		this.machineCode = machineCode;
	}

	public void setSf_last_sync_timeStamp(Date sf_last_sync_timeStamp) {
		this.sf_last_sync_timeStamp = sf_last_sync_timeStamp;
	}

	public Date getSf_create_time() {
		return sf_create_time;
	}

	public void setSf_create_time(Date sf_create_time) {
		this.sf_create_time = sf_create_time;
	}

	public CourseType getContractType() {
		return contractType;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setContractType(CourseType contractType) {
		this.contractType = contractType;
	}

	public void setSf_create_user(Integer sf_create_user) {
		this.sf_create_user = sf_create_user;
	}

	public void setSf_last_change_user(Integer sf_last_change_user) {
		this.sf_last_change_user = sf_last_change_user;
	}

	public void setMachineCode(Integer machineCode) {
		this.machineCode = machineCode;
	}

}
