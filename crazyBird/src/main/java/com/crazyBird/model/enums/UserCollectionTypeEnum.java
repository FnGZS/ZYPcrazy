package com.crazyBird.model.enums;

/**
 * @Type UserCollectionTypeEnum.java
 * @Desc
 * @author shansj
 * @date 2017年7月5日下午1:39:03
 * @version V1.0
 */

public enum UserCollectionTypeEnum {
    
	SAMPLE(0, " 样品"),
	CONTACT_USER(1, "联系人"),
	CONTACT_COMPANY(2, "联系公司"),
	USER_CARD(3, "纺织圈名片"), 
	CIRCLE_FACTORY(4, "纺织圈名片");
	
	private UserCollectionTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private Integer code;

	private String desc;

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
