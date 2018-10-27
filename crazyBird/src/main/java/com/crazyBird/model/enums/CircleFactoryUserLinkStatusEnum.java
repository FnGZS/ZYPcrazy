package com.crazyBird.model.enums;

/**
 * @Type CircleFactoryUserLinkStatusEnum.java
 * @Desc 
 * @author 
 * @date 2017年12月09日 上午9:13:00
 * @Version V1.0
 */
public enum CircleFactoryUserLinkStatusEnum {
    
	LINK_NONE(0, "未关联"),
	LINK_CHECKING(1, "关联审核中"),
	LINK_YES(2, "已关联"),
	LINK_REFUSE(3, "关联审核不通过");
	
	private CircleFactoryUserLinkStatusEnum(Integer code, String desc) {
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
