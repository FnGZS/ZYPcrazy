package com.crazyBird.model.enums;

/**
 * @Type CircleFactoryValidStatusEnum.java
 * @Desc 
 * @author 
 * @date 2017年11月25日 上午9:13:00
 * @Version V1.0
 */
public enum CircleFactoryValidStatusEnum {
    
	NO_CHECK(0, "未认证"),
	CHECK_PENDING(1, "待认证"),
	CHECK_SUCCESS(2, "认证成功");
	
	private CircleFactoryValidStatusEnum(Integer code, String desc) {
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
