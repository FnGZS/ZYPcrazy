package com.crazyBird.dao.luck.dataobject;

import com.crazyBird.service.base.PageQueryDO;

public class LuckWinnersPO extends PageQueryDO {

	private Long status;
	private Long luckId;
	

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getLuckId() {
		return luckId;
	}

	public void setLuckId(Long luckId) {
		this.luckId = luckId;
	}
	
}
