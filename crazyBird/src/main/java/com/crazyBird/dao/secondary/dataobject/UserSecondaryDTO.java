package com.crazyBird.dao.secondary.dataobject;

import java.math.BigDecimal;
import java.util.Date;

public class UserSecondaryDTO {

	private Long id;
	private Long userId;
	private Integer views;
	private Integer goodsNum;
	private String goodsTitle;
	private String goodsContent;
	private String goodsImag;
	private String postion;
	private String goodsType;
	private String goodsWay;
	private String tradingWay;
	private BigDecimal price;
	private BigDecimal realPrice;
	private Date gmtCreated;
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
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
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
	public BigDecimal getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}
	public Date getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
}
