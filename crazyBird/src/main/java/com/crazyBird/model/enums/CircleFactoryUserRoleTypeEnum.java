package com.crazyBird.model.enums;

/**
 * @Type CircleFactoryUserRoleTypeEnum.java
 * @Desc 
 * @author 
 * @date 2017年12月09日 上午9:13:00
 * @Version V1.0
 */
public enum CircleFactoryUserRoleTypeEnum {
    
	EMPLOYEE(0, "普通角色"),
	MANAGER(1, "管理员");
	
	private CircleFactoryUserRoleTypeEnum(Integer code, String desc) {
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
