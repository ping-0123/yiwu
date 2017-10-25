package com.yinzhiwu.yiwu.entity.yzw;

import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.CascadeType;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.ElectricContractYzw.GenderConverter;
import com.yinzhiwu.yiwu.enums.Gender;
import com.yinzhiwu.yiwu.util.CalendarUtil;

@SuppressWarnings("serial")
@Entity
@Table(name = "vcustomer")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class CustomerYzw extends BaseYzwEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "salesman_id", foreignKey = @ForeignKey(name = "fk_customerYzw_salesman_id", value = ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw salesman;

	@Convert(converter=CustomerAgeTypeConverter.class)
	@Column(length = 32, name = "audit_child")
	private CustomerAgeType customerAgeType;

	@Convert(converter=MemberStatusCoverter.class)
	@Column(length = 32)
	private MemberStatus isMember;

	@Column(length = 32)
	private String memberCard;

	@Column(length = 32)
	private String name;

	@Column(length = 32, name = "sex")
	@Convert(converter=GenderConverter.class)
	private Gender gender;

	@Column(length = 32)
	private String mobilePhone;

	@Column(length = 32)
	private String residentId;

	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	@Column
	private Integer age;

	@Column(length = 32)
	private String qq;

	@Column(length = 32)
	private String weChat;

	@Column(length = 128)
	private String address;

	@Column(length = 32)
	private String company;

	@Column(length = 32)
	private String profession;

	@Column(length = 32)
	private String interesting;

	@Column(length = 255)
	private String photo;

	@Column(length = 32, name = "customer_level")
	private String customerLevel;

	@Column(length = 32, name = "source_of_customer")
	private String sourceOfCustomer;

	@Column(name = "date_of_next_track")
	private Date DayOfNextTrack;

	@Column(name = "times_of_untrack")
	private Integer timesOfUntrack;

	@Column(name = "times_of_remainder_course")
	private Integer timesOfRemainderCourse;

	@Column(length = 32)
	private String password;

	@Column
	private Integer credit;

	//定金
	private Float earnest;

	// @OneToOne(mappedBy="customer",fetch=FetchType.LAZY)
	// private Distributer distributer;
	//
	public CustomerYzw() {
		super();
	}

	public CustomerYzw(Distributer d) {
		if (d.getBirthday() != null){
			if( CalendarUtil.isAudit(d.getBirthday()))
				this.customerAgeType = CustomerAgeType.ADULT;
			else
				this.customerAgeType = CustomerAgeType.CHILDREN;
		}
		this.isMember = MemberStatus.POTENTIAL;
		this.name = d.getName();
		this.gender = d.getGender();
		this.mobilePhone = d.getPhoneNo();
		this.birthday = d.getBirthday();
		if(this.birthday != null)
			this.age = (int) CalendarUtil.getAge(this.getBirthday());
		this.weChat = d.getWechatNo();
		this.sourceOfCustomer = "微信";
		/**since 1.2.3  添加服务归属*/
		this.salesman = d.getServer();
		
		this.init();
	}

	@Override
	public void init() {
		super.init();
		if(this.earnest == null)
			this.earnest=0f;
		if(this.credit == null)
			this.credit=0;
	}
	
	public Integer getId() {
		return id;
	}

	public EmployeeYzw getSalesman() {
		return salesman;
	}


	public MemberStatus getIsMember() {
		return isMember;
	}

	public String getMemberCard() {
		return memberCard;
	}

	public String getName() {
		return name;
	}

	public Gender getGender() {
		return gender;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public String getResidentId() {
		return residentId;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Integer getAge() {
		return age;
	}

	public String getQq() {
		return qq;
	}

	public String getWeChat() {
		return weChat;
	}

	public String getAddress() {
		return address;
	}

	public String getCompany() {
		return company;
	}

	public String getProfession() {
		return profession;
	}

	public String getInteresting() {
		return interesting;
	}

	public String getPhoto() {
		return photo;
	}

	public String getCustomerLevel() {
		return customerLevel;
	}

	public String getSourceOfCustomer() {
		return sourceOfCustomer;
	}

	public Date getDayOfNextTrack() {
		return DayOfNextTrack;
	}

	public Integer getTimesOfUntrack() {
		return timesOfUntrack;
	}

	public Integer getTimesOfRemainderCourse() {
		return timesOfRemainderCourse;
	}

	public String getPassword() {
		return password;
	}

	public Integer getCredit() {
		return credit;
	}

	public Float getEarnest() {
		return earnest;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSalesman(EmployeeYzw salesman) {
		this.salesman = salesman;
	}

	public void setIsMember(MemberStatus isMember) {
		this.isMember = isMember;
	}

	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public void setResidentId(String residentId) {
		this.residentId = residentId;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public void setInteresting(String interesting) {
		this.interesting = interesting;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	public void setSourceOfCustomer(String sourceOfCustomer) {
		this.sourceOfCustomer = sourceOfCustomer;
	}

	public void setDayOfNextTrack(Date dayOfNextTrack) {
		DayOfNextTrack = dayOfNextTrack;
	}

	public void setTimesOfUntrack(Integer timesOfUntrack) {
		this.timesOfUntrack = timesOfUntrack;
	}

	public void setTimesOfRemainderCourse(Integer timesOfRemainderCourse) {
		this.timesOfRemainderCourse = timesOfRemainderCourse;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public void setEarnest(Float earnest) {
		this.earnest = earnest;
	}

	public CustomerAgeType getCustomerAgeType() {
		return customerAgeType;
	}

	public void setCustomerAgeType(CustomerAgeType customerAgeType) {
		this.customerAgeType = customerAgeType;
	}

	
	
	public enum CustomerAgeType{
		
		CHILDREN("少儿"),
		ADULT("成人");
		
		private final String name;
		private CustomerAgeType(String name){
			this.name = name;
		}
		public String getName() {
			return name;
		}
		
		public static CustomerAgeType fromName(String name){
			switch (name) {
			case "少儿":
				return CustomerAgeType.CHILDREN;
			case "成人":
				return CustomerAgeType.ADULT;
			default:
				throw new UnsupportedOperationException(name + "not supported for enum CustomerAgeType");
			}
		}
	}
	
	@Converter
	public static class CustomerAgeTypeConverter implements AttributeConverter<CustomerAgeType, String>{

		@Override
		public String convertToDatabaseColumn(CustomerAgeType attribute) {
			if(attribute == null)
				return null;
			return attribute.getName();
		}

		@Override
		public CustomerAgeType convertToEntityAttribute(String dbData) {
			if(dbData == null || "".equals(dbData.trim()))
				return null;
			return CustomerAgeType.fromName(dbData);
		}
		
	}
	
	public enum MemberStatus{
		MEMBER("会员"),
		POTENTIAL("潜在"),
		LOST("流失"),
		FORBIDDEN("禁用");
		
		private final String status;

		public String getStatus() {
			return status;
		}
		
		private MemberStatus(String status){
			this.status = status;
		}
		
		public static MemberStatus fromStatus(String status){
			switch (status) {
			case "会员":
				return MemberStatus.MEMBER;
			case "潜在":
				return MemberStatus.POTENTIAL;
			case "流失":
				return LOST;
			case "禁用":
				return FORBIDDEN;
			default:
				throw new UnsupportedOperationException(status + "not supported for enum MemberStatus");
			}
		}
	}

	@Converter
	public static class MemberStatusCoverter implements AttributeConverter<MemberStatus, String>{

		@Override
		public String convertToDatabaseColumn(MemberStatus attribute) {
			if(attribute == null)
				return null;
			return attribute.getStatus();
		}

		@Override
		public MemberStatus convertToEntityAttribute(String dbData) {
			if(dbData== null || "".equals(dbData.trim()))
				return null;
			return MemberStatus.fromStatus(dbData);
		}
		
	}
}
