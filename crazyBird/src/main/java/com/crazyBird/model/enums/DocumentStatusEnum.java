package com.crazyBird.model.enums;

/**
 * @Type DocumentStatusEnum.java
 * @Desc 
 * @date 2018�??10�??5�??
 */
public enum DocumentStatusEnum {

	NORMAL(0, "正常"),
	DELETED(1, "删除"),
	NOT_BIND(2, "上传未绑�??");
	
	private DocumentStatusEnum(Integer code, String desc) {
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
