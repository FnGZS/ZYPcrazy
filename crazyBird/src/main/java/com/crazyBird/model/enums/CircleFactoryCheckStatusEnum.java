package com.crazyBird.model.enums;

/**
 * @Type CircleFactoryCheckStatusEnum.java
 * @Desc 
 * @author 
 * @date 2017年11月24日 上午11:07:29
 * @Version V1.0
 */
public enum CircleFactoryCheckStatusEnum {
    
	CHECK_PENDING(0, "待认证"),
	CHECK_SUCCESS(1, "认证成功"),
	CHECK_FAIL(2, "认证失败");
	
	private CircleFactoryCheckStatusEnum(Integer code, String desc) {
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
