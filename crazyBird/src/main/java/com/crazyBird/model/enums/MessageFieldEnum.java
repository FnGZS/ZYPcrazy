package com.crazyBird.model.enums;

/**
 * @Type MessageFieldEnum.java
 * @Desc 
 * @author luogm
 * @date 2017年12月20日 下午4:04:17
 * @Version V1.0
 */
public enum MessageFieldEnum {

	BUGUANJIA(0, "布管家"),
	CIRCLE(1, "纺织圈");
	
	private MessageFieldEnum(Integer code, String desc) {
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
