package com.crazyBird.controller.secondary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 二手市场 用户
 * @author zjw
 *
 */
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.secondary.model.CollectionSecondaryListModel;
import com.crazyBird.controller.secondary.model.CollectionSecondaryModel;
import com.crazyBird.controller.secondary.model.IsCollectionModel;
import com.crazyBird.controller.secondary.model.PurchaseSecondaryListModel;
import com.crazyBird.controller.secondary.model.SellSecondaryListModel;
import com.crazyBird.controller.secondary.param.CollectionParam;
import com.crazyBird.controller.secondary.param.CollectionSecondaryParam;
import com.crazyBird.controller.secondary.param.PurchaseSecondaryParam;
import com.crazyBird.controller.secondary.param.SellSecondaryParam;

@Controller
@RequestMapping("/secondary")
public class SecondaryUserController {
	
	@Autowired
	private SecondaryUserProcess secondaryUserProcess;
	
	/**
	 * 我卖出的
	 * @param id
	 * **/
	@ResponseBody
	@RequestMapping(value="/sellList",method=RequestMethod.GET)
	public SellSecondaryListModel getSecondarySellListByUser(SellSecondaryParam Param) {
		return secondaryUserProcess.getSellList(Param);
	}
	
	/**
	 * 我买入的
	 * @param 
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/purchaseList",method=RequestMethod.GET)
	public PurchaseSecondaryListModel getSecondaryPurchaseListByUser(PurchaseSecondaryParam Param) {
		return secondaryUserProcess.getPurchase(Param);
	}
	
	/**
	 * 收藏商品
	 * @param
	 * */
	@ResponseBody
	@RequestMapping(value="/collection",method=RequestMethod.POST)
	public CollectionSecondaryModel collectionSecondary(@RequestBody CollectionSecondaryParam Param) {
		return secondaryUserProcess.collection(Param);
	}
	
	/**
	 * 收藏的商品列表
	 * */
	@ResponseBody
	@RequestMapping(value="/collectionList",method=RequestMethod.GET)
	public CollectionSecondaryListModel collectionSecondaryList(CollectionParam param) {
		return secondaryUserProcess.collectionList(param);
	}
	
	/**
	 * 是否收藏
	 * */
	@ResponseBody
	@RequestMapping(value="/isCollection",method=RequestMethod.GET)
	public IsCollectionModel isCollection(CollectionParam param) {
		return secondaryUserProcess.isCollection(param);
	}
}
