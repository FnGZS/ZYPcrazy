package com.crazyBird.controller.luck.param;

public class AddLuckParam {

	private Long userId;
	private String luckName;
	private String luckPic;
	private String luckExplain;
	private String lotteryTime;
	private String luckPrizeExplain;
	private Integer luckMode;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLuckName() {
		return luckName;
	}
	public void setLuckName(String luckName) {
		this.luckName = luckName;
	}
	public String getLuckPic() {
		return luckPic;
	}
	public void setLuckPic(String luckPic) {
		this.luckPic = luckPic;
	}
	public String getLuckExplain() {
		return luckExplain;
	}
	public void setLuckExplain(String luckExplain) {
		this.luckExplain = luckExplain;
	}
	public String getLotteryTime() {
		return lotteryTime;
	}
	public void setLotteryTime(String lotteryTime) {
		this.lotteryTime = lotteryTime;
	}
	public String getLuckPrizeExplain() {
		return luckPrizeExplain;
	}
	public void setLuckPrizeExplain(String luckPrizeExplain) {
		this.luckPrizeExplain = luckPrizeExplain;
	}
	public Integer getLuckMode() {
		return luckMode;
	}
	public void setLuckMode(Integer luckMode) {
		this.luckMode = luckMode;
	}
}
