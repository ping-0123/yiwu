package com.yinzhiwu.springmvc3.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 群组
 * @author ping
 *
 */

@ApiModel
public class UamGroup {

	@ApiModelProperty(value="群组的Id", required=true)
	private String groupId;
	@ApiModelProperty(value="group name" , required=true)
	private String name;
	@ApiModelProperty(value="group icon", required=true)
	private String icon;
	
	public UamGroup(){}
	
	public UamGroup(String i, String string) {
		this.groupId =i;
		this.name=string;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
