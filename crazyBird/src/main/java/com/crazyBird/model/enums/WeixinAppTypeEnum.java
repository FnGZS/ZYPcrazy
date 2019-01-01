package com.crazyBird.model.enums;

/**
 * @Type WeixinAppCodeEnum.java
 * @Desc
 * @author luogm
 * @date 2017年9月25日下午1:20:19
 * @version V1.0
 */
public enum WeixinAppTypeEnum {
	
	BUGUANJIA(1, "布管家"),
	BUGUANJIA_TOOLS(2, "纺织工具箱"),
	BUGUANJIA_CIRCLE(3, "纺织圈"),
	BUGUANJIA_EXPO(4, "展会助手"),
	BUGUANJIA_SAMPLING(5, "纺织采样");
	
	private WeixinAppTypeEnum(Integer type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	private Integer type;

	private String desc;

	public Integer getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}
}
