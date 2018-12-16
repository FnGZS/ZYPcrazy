package com.crazyBird.dao.user.dataobject;

public class UserDO {
	private Integer id;
	private Long schoolNum;
	private String userName;
	private String password;
	private String openId;
	private String phone;
	private Long isBinding;
	private Integer sex;
	private String headimgurl;
	
	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getSchoolNum() {
		return schoolNum;
	}

	public void setSchoolNum(Long schoolNum) {
		this.schoolNum = schoolNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Long getIsBinding() {
		return isBinding;
	}

	public void setIsBinding(Long isBinding) {
		this.isBinding = isBinding;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
