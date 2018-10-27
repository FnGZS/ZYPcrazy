package com.crazyBird.model.enums;

/**
 * @Type WeixinAppCodeEnum.java
 * @Desc
 * @author luogm
 * @date 2017年9月25日下午1:20:19
 * @version V1.0
 */
public enum WeixinAppCodeEnum {
	
	MY_CARD(0, "我的名片"),
	EXPO_INVITE(1, "展会邀请"),
	EXPO_SELECT_FORM(2, "展会选样单"),
	SHOP_SELECT_FORM(3, "线下选样单");
	
	private WeixinAppCodeEnum(Integer type, String desc) {
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
	
	public static Boolean isWeixinAppCodeType(Integer type) {
		Boolean result = false;
		if(type == null) {
			return result;
		}
		for (WeixinAppCodeEnum typeEnum : WeixinAppCodeEnum.values()) {
			if(typeEnum.getType().equals(type)) {
				return true;
			}
		}
		return result;
	}
}
