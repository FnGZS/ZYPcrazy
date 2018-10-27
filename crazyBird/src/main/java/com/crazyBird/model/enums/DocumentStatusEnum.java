package com.crazyBird.model.enums;

/**
 * @Type DocumentStatusEnum.java
 * @Desc 
 * @author luogm
 * @date 2016年10月14日 下午5:13:12
 * @Version V1.0
 */
public enum DocumentStatusEnum {

	NORMAL(0, "正常"),
	DELETED(1, "删除"),
	NOT_BIND(2, "上传未绑定");
	
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
