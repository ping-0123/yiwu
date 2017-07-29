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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw.CustomerAgeType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw.CustomerAgeTypeConverter;

@Entity
@Table(name = "vproduct")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class ProductYzw extends BaseYzwEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1893100957719617919L;
	public enum ProductCardType{
		ADULT_MEMBER_CARD("成人会员卡"),
		CHILDREN_MEMBER_CARD("少儿会员卡"),
		GROUP_MEMEBER_CARD("团体会员卡"),
		DANCE_SERVICE_CARD("舞蹈服务卡"),
		COACH_TRAIN_CARD("教师培训卡"),
		EXAM_TRAIN_CARD("考辅培训卡"),
		CERTIFICATION_FEE("认证费"),
		INCIDENTALS("杂费"),
		GOODS("商品"),
		DEPOSIT("定金");
		
		private final String name;

		public String getName() {
			return name;
		}
		
		private ProductCardType(String name){
			this.name = name;
		}
		
		public static ProductCardType fromName(String name){
			switch (name) {
			case "成人会员卡":
				return ProductCardType.ADULT_MEMBER_CARD;
			case "少儿会员卡":
				return ProductCardType.CHILDREN_MEMBER_CARD;
			case "团体会员卡":
				return ProductCardType.GROUP_MEMEBER_CARD;
			case "舞蹈服务卡":
				return ProductCardType.DANCE_SERVICE_CARD;
			case "教师培训卡":
				return ProductCardType.COACH_TRAIN_CARD;
			case "考辅培训卡":
				return ProductCardType.EXAM_TRAIN_CARD;
			case "认证费":
				return ProductCardType.CERTIFICATION_FEE;
			case "杂费":
				return ProductCardType.INCIDENTALS;
			case "商品":
				return ProductCardType.GOODS;
			case "定金":
				return ProductCardType.DEPOSIT;
			default:
				throw new UnsupportedOperationException(name + "not supported for enum" + ProductCardType.class.getSimpleName());
			}
		}
	}
	
	@Converter
	public static class ProductCardTypeConverter implements AttributeConverter<ProductCardType, String>{

		@Override
		public String convertToDatabaseColumn(ProductCardType attribute) {
			if(attribute == null)
				return null;
			return attribute.getName();
		}

		@Override
		public ProductCardType convertToEntityAttribute(String dbData) {
			if(dbData == null || dbData.trim().length() ==0)
				return null;
			return ProductCardType.fromName(dbData);
		}
		
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(length = 32)
	private String name;

	@Convert(converter=ProductCardTypeConverter.class)
	@Column(name = "card_type", length = 32)
	private ProductCardType cardType;

	@Convert(converter=CustomerAgeTypeConverter.class)
	@Column(name = "customer_type", length = 32)
	private CustomerAgeType customerType;

	@Column(name = "marked_price")
	private Integer markedPrice;

	@Column(name = "useful_life")
	private Short usefulLife;

	@Column(name = "useful_times")
	private Short usefulTimes;

	@Column(name = "obsolete_flag")
	private Boolean isObsolete =Boolean.FALSE;

	@Column(name = "DY_RCP", length = 32)
	private String dyRCP;

	@Column(name = "max_leave_times")
	private Short maxLeaveTimes;
	
	@Column(name="organization_code")
	private String visableDepartmentIds;
	
	@Column(name="useful_hours")
	private Float usefulHours;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="econtract_type_id", 
		foreignKey=@ForeignKey(name="fk_product_econtract_type_id", value=ConstraintMode.NO_CONSTRAINT))
	private ElectricContractTypeYzw contractType;
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ProductCardType getCardType() {
		return cardType;
	}

	public CustomerAgeType getCustomerType() {
		return customerType;
	}

	public Integer getMarkedPrice() {
		return markedPrice;
	}

	public Short getUsefulLife() {
		return usefulLife;
	}

	public Short getUsefulTimes() {
		return usefulTimes;
	}

	public Boolean getIsObsolete() {
		return isObsolete;
	}

	public String getDyRCP() {
		return dyRCP;
	}

	public Short getMaxLeaveTimes() {
		return maxLeaveTimes;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCardType(ProductCardType cardType) {
		this.cardType = cardType;
	}

	public void setCustomerType(CustomerAgeType customerType) {
		this.customerType = customerType;
	}

	public void setMarkedPrice(Integer markedPrice) {
		this.markedPrice = markedPrice;
	}

	public void setUsefulLife(Short usefulLife) {
		this.usefulLife = usefulLife;
	}

	public void setUsefulTimes(Short usefulTimes) {
		this.usefulTimes = usefulTimes;
	}

	public void setIsObsolete(Boolean isObsolete) {
		this.isObsolete = isObsolete;
	}

	public void setDyRCP(String dyRCP) {
		this.dyRCP = dyRCP;
	}

	public void setMaxLeaveTimes(Short maxLeaveTimes) {
		this.maxLeaveTimes = maxLeaveTimes;
	}

	public ProductYzw() {
		super();
	}

	public String getVisableDepartmentIds() {
		return visableDepartmentIds;
	}

	public Float getUsefulHours() {
		return usefulHours;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public ElectricContractTypeYzw getContractType() {
		return contractType;
	}

	public void setVisableDepartmentIds(String visableDepartmentIds) {
		this.visableDepartmentIds = visableDepartmentIds;
	}

	public void setUsefulHours(Float usefulHours) {
		this.usefulHours = usefulHours;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setContractType(ElectricContractTypeYzw contractType) {
		this.contractType = contractType;
	}

}
