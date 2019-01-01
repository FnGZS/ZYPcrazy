package com.crazyBird.model.enums;

/**
 * @Type DocumentTypeEnum.java
 * @Desc
 * @author
 * @date 2017年9月22日下午1:56:06
 * @version V1.0
 */

public enum DocumentTypeEnum {
    
	PICTURE(0, "图片"),
	VOICE(1, "语音"),
	DOCUMENT(2, "文档");
	
	private DocumentTypeEnum(Integer code, String desc) {
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
