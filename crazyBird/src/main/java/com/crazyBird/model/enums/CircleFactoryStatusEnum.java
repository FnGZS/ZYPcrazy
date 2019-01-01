package com.crazyBird.model.enums;

/**
 * @Type CircleFactoryStatusEnum.java
 * @Desc 
 * @author 
 * @date 2017年11月24日 上午9:53:51
 * @Version V1.0
 */
public enum CircleFactoryStatusEnum {
    
	NORMAL(0, "正常"),
	DELETED(1, "下架"),
	CHECK_PENDING(2, "待审核"),
	CHECK_FAIL(3, "审核不通过");
	
	private CircleFactoryStatusEnum(Integer code, String desc) {
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
