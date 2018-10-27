package com.crazyBird.model.enums;

/**
 * @Type UserCollectionStatusEnum.java
 * @Desc
 * @author shansj
 * @date 2017年7月12日上午11:53:41
 * @version V1.0
 */

public enum UserCollectionStatusEnum {
    
	NO_COLLECT(0, "未收藏"),
	COLLECT(1, "已收藏");
	
	private UserCollectionStatusEnum(Integer code, String desc) {
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
