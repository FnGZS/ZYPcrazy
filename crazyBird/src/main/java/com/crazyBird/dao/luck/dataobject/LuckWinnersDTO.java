package com.crazyBird.dao.luck.dataobject;

public class LuckWinnersDTO {

	private Long id;
	private Long userId;
	private Long luckId;
	private Integer isWinning;
	private Long priceId;
	private String luckPrize;
	private Integer num;
	private String luckPic;
	private String userName;
	private String headImgUrl;
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
	public Long getPriceId() {
		return priceId;
	}
	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}
	public String getLuckPrize() {
		return luckPrize;
	}
	public void setLuckPrize(String luckPrize) {
		this.luckPrize = luckPrize;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getLuckPic() {
		return luckPic;
	}
	public void setLuckPic(String luckPic) {
		this.luckPic = luckPic;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	
	
}
