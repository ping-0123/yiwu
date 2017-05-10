package com.yinzhiwu.springmvc3.entity.yzw;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.enums.Gender;
import com.yinzhiwu.springmvc3.util.CalendarUtil;

@Entity
@Table(name="vcustomer")
public class CustomerYzw  extends BaseYzwEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1246891395254977126L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="salesmanId", 
			foreignKey=@ForeignKey(name="fk_customerYzw_salesman_id", value=ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw salesman;
	
	@Column(length=32, name="audit_child")
	private String auditOrChild;
	
	@Column(length=32)
	private String isMember;
	
	@Column(length=32)
	private String memberCard;
	
	@Column(length=32)
	private String name;
	
	@Column(length=32, name="sex")
	private String gender;
	
	@Column(length=32)
	private String mobilePhone;
	
	@Column(length=32)
	private String residentId;
	

	@Column
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date birthday;
	
	@Column
	private Integer age;
	
	@Column(length=32)
	private String qq;
	
	@Column(length=32)
	private String weChat;
	
	@Column(length=128)
	private String address;
	
	@Column(length=32)
	private String company;
	
	@Column(length=32)
	private String profession;
	
	@Column(length=32)
	private String interesting;
	
	@Column(length=255)
	private String photo;
	
	@Column(length=32, name="customer_level")
	private String customerLevel;
	
	@Column(length=32 , name="source_of_customer")
	private String sourceOfCustomer;
	
	@Column(name="date_of_next_track")
	private Date DayOfNextTrack;
	
	@Column(name="times_of_untrack")
	private Integer timesOfUntrack;
	
	@Column(name="times_of_remainder_course")
	private Integer timesOfRemainderCourse;
	
	@Column(length=32)
	private String password;
	
	@Column
	private Integer credit;
	
	@Column
	private Float earnest;
	
	
	public CustomerYzw() {
		super();
	}
	
	public CustomerYzw(Distributer d){
		super();
		if(d.getBirthday() != null && CalendarUtil.isAudit(d.getBirthday()))
			this.auditOrChild="成人";
		else
			this.auditOrChild="少儿";
		this.isMember = "潜在";
		this.name = d.getName();
		if(d.getGender()==Gender.FEMALE)
			this.gender="女";
		else
			this.gender="男";
		this.mobilePhone = d.getPhoneNo();
		this.birthday = new java.sql.Date(d.getBirthday().getTime());
		this.age = (int) CalendarUtil.getAge(d.getBirthday());
		this.weChat = d.getWechatNo();
		this.sourceOfCustomer = "微信";
	}

	

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}


	public final String getAuditOrChild() {
		return auditOrChild;
	}

	public final void setAuditOrChild(String auditOrChild) {
		this.auditOrChild = auditOrChild;
	}

	public final String getIsMember() {
		return isMember;
	}

	public final void setIsMember(String isMember) {
		this.isMember = isMember;
	}

	public final String getMemberCard() {
		return memberCard;
	}

	public final void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getGender() {
		return gender;
	}

	public final void setGender(String gender) {
		this.gender = gender;
	}

	public final String getMobilePhone() {
		return mobilePhone;
	}

	public final void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public final String getResidentId() {
		return residentId;
	}

	public final void setResidentId(String residentId) {
		this.residentId = residentId;
	}

	public final Date getBirthday() {
		return birthday;
	}

	public final void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public final Integer getAge() {
		return age;
	}

	public final void setAge(Integer age) {
		this.age = age;
	}

	public final String getQq() {
		return qq;
	}

	public final void setQq(String qq) {
		this.qq = qq;
	}

	public final String getWeChat() {
		return weChat;
	}

	public final void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public final String getAddress() {
		return address;
	}

	public final void setAddress(String address) {
		this.address = address;
	}

	public final String getCompany() {
		return company;
	}

	public final void setCompany(String company) {
		this.company = company;
	}

	public final String getProfession() {
		return profession;
	}

	public final void setProfession(String profession) {
		this.profession = profession;
	}

	public final String getInteresting() {
		return interesting;
	}

	public final void setInteresting(String interesting) {
		this.interesting = interesting;
	}

	public final String getPhoto() {
		return photo;
	}

	public final void setPhoto(String photo) {
		this.photo = photo;
	}

	public final String getCustomerLevel() {
		return customerLevel;
	}

	public final void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	public final String getSourceOfCustomer() {
		return sourceOfCustomer;
	}

	public final void setSourceOfCustomer(String sourceOfCustomer) {
		this.sourceOfCustomer = sourceOfCustomer;
	}

	public final Date getDayOfNextTrack() {
		return DayOfNextTrack;
	}

	public final void setDayOfNextTrack(Date dayOfNextTrack) {
		DayOfNextTrack = dayOfNextTrack;
	}

	public final Integer getTimesOfUntrack() {
		return timesOfUntrack;
	}

	public final void setTimesOfUntrack(Integer timesOfUntrack) {
		this.timesOfUntrack = timesOfUntrack;
	}

	public final Integer getTimesOfRemainderCourse() {
		return timesOfRemainderCourse;
	}

	public final void setTimesOfRemainderCourse(Integer timesOfRemainderCourse) {
		this.timesOfRemainderCourse = timesOfRemainderCourse;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final Integer getCredit() {
		return credit;
	}

	public final void setCredit(Integer credit) {
		this.credit = credit;
	}

	public final Float getEarnest() {
		return earnest;
	}

	public final void setEarnest(Float earnest) {
		this.earnest = earnest;
	}

	


	public EmployeeYzw getSalesman() {
		return salesman;
	}

	public void setSalesman(EmployeeYzw salesman) {
		this.salesman = salesman;
	}
	
	
}
