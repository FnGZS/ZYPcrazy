package com.crazyBird.controller.secondary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.secondary.model.SecondaryGoodModel;
import com.crazyBird.controller.secondary.model.SecondaryGoodsModel;
import com.crazyBird.controller.secondary.model.SecondarySlideModel;
import com.crazyBird.controller.secondary.model.SecondaryTypeModel;
import com.crazyBird.controller.secondary.param.SearchSecondaryListParam;
import com.crazyBird.controller.secondary.param.SecondaryGoodsByUserListParam;
import com.crazyBird.controller.secondary.param.SecondaryGoodsListParam;
import com.crazyBird.controller.secondary.param.SecondaryGoodsParam;


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
	
	
}