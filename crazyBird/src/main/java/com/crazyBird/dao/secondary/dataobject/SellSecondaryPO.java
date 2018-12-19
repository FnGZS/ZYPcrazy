package com.crazyBird.dao.secondary.dataobject;

import com.crazyBird.service.base.PageQueryDO;

public class SellSecondaryPO extends PageQueryDO{

	private Integer status;
	private Long sellId;



	public Long getSellId() {
		return sellId;
	}

	public void setSellId(Long sellId) {
		this.sellId = sellId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


}
