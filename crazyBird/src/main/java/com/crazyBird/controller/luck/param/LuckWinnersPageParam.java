package com.crazyBird.controller.luck.param;

import com.crazyBird.controller.base.AbstractOrderPageParam;

public class LuckWinnersPageParam extends AbstractOrderPageParam {

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
