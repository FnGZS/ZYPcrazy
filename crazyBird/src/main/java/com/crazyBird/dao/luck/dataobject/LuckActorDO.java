package com.crazyBird.dao.luck.dataobject;

public class LuckActorDO {

	private Long id;
	private Long userId;
	private Long luckId;
	private Integer isWinning;
	private Long prizeId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getLuckId() {
		return luckId;
	}
	public void setLuckId(Long luckId) {
		this.luckId = luckId;
	}
	public Integer getIsWinning() {
		return isWinning;
	}
	public void setIsWinning(Integer isWinning) {
		this.isWinning = isWinning;
	}
	public Long getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(Long prizeId) {
		this.prizeId = prizeId;
	}
	
}
