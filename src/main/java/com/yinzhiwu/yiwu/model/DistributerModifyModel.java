package com.yinzhiwu.yiwu.model;

import org.springframework.web.multipart.MultipartFile;

import com.yinzhiwu.yiwu.entity.Distributer;

public class DistributerModifyModel {

	private String nickName;

	private String name;

//	private String phoneNo;

	private MultipartFile image;

	private String imageUrl;

	public String getNickName() {
		return nickName;
	}

	public String getName() {
		return name;
	}

//	public String getPhoneNo() {
//		return phoneNo;
//	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public void setPhoneNo(String phoneNo) {
//		this.phoneNo = phoneNo;
//	}

	public Distributer toDistributer() {
		Distributer d = new Distributer();
		d.setName(name);
		d.setNickName(nickName);
//		d.setPhoneNo(phoneNo);
		return d;
	}

	public DistributerModifyModel fromDistributer(Distributer distributer) {
		DistributerModifyModel model = new DistributerModifyModel();
		model.setName(distributer.getName());
		model.setNickName(distributer.getNickName());
//		model.setPhoneNo(distributer.getPhoneNo());
		return model;
	}

	public MultipartFile getImage() {
		return image;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
