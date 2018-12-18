package com.crazyBird.dao.secondary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crazyBird.dao.secondary.dataobject.SearchSecondaryGoodsPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsByUserPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsPO;

public interface SecondaryDao {
	//获得商品列表
	int getSecondaryGoodsCount(@Param("goodsType") Integer goodsType);
	List<SecondaryGoodsDTO> getSecondaryGoodsList(SecondaryGoodsPO po);
	//获得商品详情
	List<SecondaryGoodsDTO> getSecondaryGoods(@Param("id") Long id);
	//搜索
	int searchSecondaryGoodsCount(String keyWord);
	List<SecondaryGoodsDTO> searchSecondaryGoods(SearchSecondaryGoodsPO po);
	//我发布的商品
	int getSecondaryGoodsCountByUser(Long userId);
	List<SecondaryGoodsDTO> getSecondaryGoodsByUser(SecondaryGoodsByUserPO po);
	//发布商品
	int createSecondaryGoods(SecondaryGoodsDO goodsDO);
	void createSecondaryViews(Long id);
	
	
	
}