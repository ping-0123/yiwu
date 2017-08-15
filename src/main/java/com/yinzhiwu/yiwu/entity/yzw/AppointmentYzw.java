package com.yinzhiwu.yiwu.entity.yzw;

import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yinzhiwu.yiwu.entity.Distributer;

@Entity
@Table(name = "vappointment")
public class AppointmentYzw {

	public enum AppointStatus {
		APPONTED("预约"), UN_APOINTED("取消");
		private final String status;

		public static AppointStatus fromStatus(String status) {
			switch (status) {
			case "预约":
				return AppointStatus.APPONTED;
			case "取消":
				return AppointStatus.UN_APOINTED;
			default:
				throw new UnsupportedOperationException(status + "is not supported for enum AppointStatus");
			}
		}

		public String getStatus() {
			return status;
		}

		private AppointStatus(String status) {
			this.status = status;
		}

	}

	@Converter
	public static class AppointStatusConverter implements AttributeConverter<AppointStatus, String> {

		@Override
		public String convertToDatabaseColumn(AppointStatus attribute) {
			if (attribute == null)
				return null;
			return attribute.getStatus();
		}

		@Override
		public AppointStatus convertToEntityAttribute(String dbData) {
			if (dbData == null)
				return null;
			return AppointStatus.fromStatus(dbData);
		}

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lesson_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private LessonYzw lesson;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private CustomerYzw customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
	private Distributer distributer;
	
	@Column(length=32)
	private String contractNo;
	
	@Column(name = "status")
	@Convert(converter = AppointStatusConverter.class)
	private AppointStatus status;

	@JsonIgnore
	@Column(name = "sf_create_user")
	private Integer createUserId;

	@JsonIgnore
	@Column(name = "sf_last_change_user")
	private Integer lastChangeUserId;

	@JsonIgnore
	@Column(name = "sf_create_time")
	private Date createTime;

	@JsonIgnore
	@Column(name = "sf_last_change_time")
	private Date lastChangeTime;

	@JsonIgnore
	@Column
	private Integer machinecode;

	@JsonIgnore
	@Column(name = "sf_Last_Sync_TimeStamp")
	private Date lastSyncTimeStamp;

	public AppointmentYzw() {
	};

	public AppointmentYzw(LessonYzw lesson, CustomerYzw customer) {
		init();
		this.lesson = lesson;
		this.customer = customer;
	};
	
	public AppointmentYzw(LessonYzw lesson , Distributer distributer, String contractNo){
		init();
		this.distributer =distributer;
		this.customer = distributer.getCustomer();
		this.lesson = lesson;
		this.contractNo = contractNo;
	}

	protected void init() {
		this.createUserId = 1;
		Date date = new Date();
		this.createTime = date;
		this.lastChangeUserId = 1;
		this.lastChangeTime = date;
		this.lastSyncTimeStamp = date;
		this.status = AppointStatus.APPONTED;
	}

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final Integer getCreateUserId() {
		return createUserId;
	}

	public final void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public final Integer getLastChangeUserId() {
		return lastChangeUserId;
	}

	public final void setLastChangeUserId(Integer lastChangeUserId) {
		this.lastChangeUserId = lastChangeUserId;
	}

	public final Date getCreateTime() {
		return createTime;
	}

	public final void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public final Date getLastChangeTime() {
		return lastChangeTime;
	}

	public final void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}

	public final Integer getMachinecode() {
		return machinecode;
	}

	public final void setMachinecode(Integer machinecode) {
		this.machinecode = machinecode;
	}

	public final Date getLastSyncTimeStamp() {
		return lastSyncTimeStamp;
	}

	public final void setLastSyncTimeStamp(Date lastSyncTimeStamp) {
		this.lastSyncTimeStamp = lastSyncTimeStamp;
	}

	public LessonYzw getLesson() {
		return lesson;
	}

	public CustomerYzw getCustomer() {
		return customer;
	}

	public AppointStatus getStatus() {
		return status;
	}

	public void setLesson(LessonYzw lesson) {
		this.lesson = lesson;
	}

	public void setCustomer(CustomerYzw customer) {
		this.customer = customer;
	}

	public void setStatus(AppointStatus status) {
		this.status = status;
	}

	public Distributer getDistributer() {
		return distributer;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setDistributer(Distributer distributer) {
		this.distributer = distributer;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

}
