package com.crazyBird.controller.secondary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.secondary.model.SecondaryCommetsMessageModel;
import com.crazyBird.controller.secondary.model.SecondaryGoodModel;
import com.crazyBird.controller.secondary.model.SecondaryGoodsCommentsModel;
import com.crazyBird.controller.secondary.model.SecondaryGoodsModel;
import com.crazyBird.controller.secondary.model.SecondaryMessageNumModel;
import com.crazyBird.controller.secondary.model.SecondarySlideModel;
import com.crazyBird.controller.secondary.model.SecondaryTypeModel;
import com.crazyBird.controller.secondary.model.SecondaryUserAddressModel;
import com.crazyBird.controller.secondary.param.SearchSecondaryListParam;
import com.crazyBird.controller.secondary.param.SecondaryCommetsParam;
import com.crazyBird.controller.secondary.param.SecondaryGoodsByUserListParam;
import com.crazyBird.controller.secondary.param.SecondaryGoodsCommentParam;
import com.crazyBird.controller.secondary.param.SecondaryGoodsGetCommetsParam;
import com.crazyBird.controller.secondary.param.SecondaryGoodsListParam;
import com.crazyBird.controller.secondary.param.SecondaryGoodsParam;
import com.crazyBird.controller.secondary.param.SecondaryUserAddressParam;


/**
 * 二手市场
 * @author zzc
 *
 */
@Controller
@RequestMapping("/secondary")
public class SecondaryController {
	@Autowired
	private SecondaryProcess secondaryProcess;
	
	/**
	 * 得到二手市场首页轮播图
	 */
	@ResponseBody
	@RequestMapping(value="/slide",method=RequestMethod.GET)
	public SecondarySlideModel getSecondarySlide(){
		return secondaryProcess.getSecondarySlide();
	}
	/**
	 * 得到商品分类
	 */
	@ResponseBody
	@RequestMapping(value="/type",method=RequestMethod.GET)
	public SecondaryTypeModel getSecondaryType(){
		return secondaryProcess.getSecondaryType();
	}
	/**
	 * 得到交易方式
	 */
	@ResponseBody
	@RequestMapping(value="/traydingWay",method=RequestMethod.GET)
	public SecondaryTypeModel getSecondaryTradingWay(){
		return secondaryProcess.getSecondaryTradingWay();
	}
	/**
	 * 得到售卖形式
	 */
	@ResponseBody
	@RequestMapping(value="/way",method=RequestMethod.GET)
	public SecondaryTypeModel getSecondaryWay(){
		return secondaryProcess.getSecondaryWay();
	}
	
	/**
	 * 搜索
	 */
	@ResponseBody
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public SecondaryGoodsModel searchSecondaryGoods(SearchSecondaryListParam param) {
		return secondaryProcess.searchSecondaryGoods(param);
		
	}
	/**
	 * 得到某分类的商品列表
	 */
	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public SecondaryGoodsModel getSecondaryGoodsList(SecondaryGoodsListParam param) {
		return secondaryProcess.getSecondaryGoodsList(param);
		
	}
	/**
	 * 得到某个商品详情
	 */
	@ResponseBody
	@RequestMapping(value="/goods/{id}",method=RequestMethod.GET)
	public SecondaryGoodModel getSecondaryGoods(@PathVariable Long id) {
		return secondaryProcess.getSecondaryGoods(id);
		
	}
	/**
	 * 删除商品（更改商品状态）
	 */
	@ResponseBody
	@RequestMapping(value="/goods/delete/{id}",method=RequestMethod.PUT)
	public SimpleFlagModel deleteSecondaryGoods(@PathVariable Long id) {
		return secondaryProcess.deleteSecondaryGoods(id);
		
	}
	
	/**
	 * 我发布的商品
	 */
	@ResponseBody
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public SecondaryGoodsModel getSecondaryGoodsByUser(SecondaryGoodsByUserListParam param) {
		return secondaryProcess.getSecondaryGoodsByUser(param);
	}
	
	/**
	 * 发布二手商品
	 */
	@ResponseBody
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public SimpleFlagModel createSecondaryGoods(@RequestBody SecondaryGoodsParam param) {
		return secondaryProcess.createSecondaryGoods(param);
	}
	/**
	 * 得到对应商品的评论回复
	 */
	@ResponseBody
	@RequestMapping(value="/comments",method=RequestMethod.GET)
	public SecondaryGoodsCommentsModel getSecondaryGoodsComments(SecondaryGoodsGetCommetsParam param) {
		return secondaryProcess.getSecondaryGoodsComments(param);
	}
	/**
	 * 获取用户未读的评论回复
	 *
	 */
	@ResponseBody
	@RequestMapping(value="/commentMessage",method=RequestMethod.GET)
	public SecondaryCommetsMessageModel getCommentMessage(SecondaryCommetsParam param){
		return secondaryProcess.getCommentMessage(param);
		
	}
	
	/**
	 * 获取系统通知消息
	 */
	/**
	 * 获取审核未通过信息
	 * 
	 */
	
	/**
	 * 获取用户未读信息的数量                 
	 */
	@ResponseBody
	@RequestMapping(value="/message/{id}",method=RequestMethod.GET)
	public SecondaryMessageNumModel getSecondaryGoodsCommentsCount(@PathVariable Long id) {
		return secondaryProcess.getSecondaryGoodsCommentsCount(id);
	}
	/**
	 * 评论
	 */
	@ResponseBody
	@RequestMapping(value="/comment",method=RequestMethod.POST)
	public SimpleFlagModel createSecondaryGoodsComment(@RequestBody SecondaryGoodsCommentParam param) {
		return secondaryProcess.createSecondaryGoodsComment(param);
	}
	/**
	 * 回复
	 */
	@ResponseBody
	@RequestMapping(value="/reply",method=RequestMethod.POST)
	public SimpleFlagModel createSecondaryGoodsReply(@RequestBody SecondaryGoodsCommentParam param) {
		return secondaryProcess.createSecondaryGoodsReply(param);
	}
	/**
	 * 得到用户地址列表
	 */
	@ResponseBody
	@RequestMapping(value="/userAddress/{id}",method=RequestMethod.GET)
	public SecondaryUserAddressModel getUserAddress(@PathVariable Long id) {
		return secondaryProcess.getUserAddress(id);
	}
	/**
	 * 修改用户地址
	 */
	@ResponseBody
	@RequestMapping(value="/userAddress/update",method=RequestMethod.PUT)
	public SimpleFlagModel updatetUserAddress(@RequestBody SecondaryUserAddressParam param) {
		return secondaryProcess.updatetUserAddress(param);
	}
	/**
	 * 添加用户地址
	 */
	@ResponseBody
	@RequestMapping(value="/userAddress/add",method=RequestMethod.POST)
	public SimpleFlagModel addUserAddress(@RequestBody SecondaryUserAddressParam param) {
		return secondaryProcess.addUserAddress(param);
	}
}
