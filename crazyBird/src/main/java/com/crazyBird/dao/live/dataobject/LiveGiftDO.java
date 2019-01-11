package com.crazyBird.dao.live.dataobject;

import java.math.BigDecimal;

public class LiveGiftDO {
	private Integer id;
	private String giftName;
	private String giftImage;
	private BigDecimal giftPrice;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGiftName() {
		return giftName;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	public String getGiftImage() {
		return giftImage;
	}
	public void setGiftImage(String giftImage) {
		this.giftImage = giftImage;
	}
	public BigDecimal getGiftPrice() {
		return giftPrice;
	}
	public void setGiftPrice(BigDecimal giftPrice) {
		this.giftPrice = giftPrice;
	}
}
