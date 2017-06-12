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
	
//	@OneToOne(mappedBy="customer",fetch=FetchType.LAZY)
//	private Distributer distributer;
//	
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
		this.birthday = d.getBirthday()==null ? new Date(): d.getBirthday();
		this.age = (int) CalendarUtil.getAge(this.getBirthday());
		this.weChat = d.getWechatNo();
		this.sourceOfCustomer = "微信";
	}

	public Integer getId() {
		return id;
	}

	public EmployeeYzw getSalesman() {
		return salesman;
	}

	public String getAuditOrChild() {
		return auditOrChild;
	}

	public String getIsMember() {
		return isMember;
	}

	public String getMemberCard() {
		return memberCard;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
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

	public void setAuditOrChild(String auditOrChild) {
		this.auditOrChild = auditOrChild;
	}

	public void setIsMember(String isMember) {
		this.isMember = isMember;
	}

	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
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

	

	
	
}
