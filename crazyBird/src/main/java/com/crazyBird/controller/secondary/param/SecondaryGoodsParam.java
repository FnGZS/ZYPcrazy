package com.crazyBird.controller.secondary.param;

import java.math.BigDecimal;
import java.util.Date;

public class SecondaryGoodsParam {
	private Long userId;
	private Integer goodsNum;
	private String goodsTitle;
	private String goodsContent;
	private String goodsImag;
	private String postion;
	private String goodsType;
	private String goodsWay;
	private String tradingWay;
	private BigDecimal price;


	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	public String getGoodsTitle() {
		return goodsTitle;
	}
	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}
	public String getGoodsContent() {
		return goodsContent;
	}
	public void setGoodsContent(String goodsContent) {
		this.goodsContent = goodsContent;
	}
	public String getGoodsImag() {
		return goodsImag;
	}
	public void setGoodsImag(String goodsImag) {
		this.goodsImag = goodsImag;
	}
	public String getPostion() {
		return postion;
	}
	public void setPostion(String postion) {
		this.postion = postion;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsWay() {
		return goodsWay;
	}
	public void setGoodsWay(String goodsWay) {
		this.goodsWay = goodsWay;
	}
	public String getTradingWay() {
		return tradingWay;
	}
	public void setTradingWay(String tradingWay) {
		this.tradingWay = tradingWay;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
