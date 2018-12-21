package com.crazyBird.service.secondary;

import java.util.List;

import com.crazyBird.dao.secondary.dataobject.SearchSecondaryGoodsPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCommentViewDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsByUserPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsCommentDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsCommentsDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsCommentsPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsPO;
import com.crazyBird.dao.secondary.dataobject.SecondarySlideDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryTypeDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryUserAddressDO;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;

public interface SecondaryService {
	List<SecondarySlideDO> getSecondarySlide();
	List<SecondaryTypeDO>  getSecondaryType();
	List<SecondaryTypeDO>  getSecondaryWay();
	List<SecondaryTypeDO>  getSecondaryTradingWay();
	
	//获得商品列表
	ResponsePageQueryDO<List<SecondaryGoodsDTO>> getSecondaryGoodsList(SecondaryGoodsPO po);
	//获得商品详情
	List<SecondaryGoodsDTO> getSecondaryGoods(Long id);
	//搜索
	ResponsePageQueryDO<List<SecondaryGoodsDTO>> searchSecondaryGoods(SearchSecondaryGoodsPO po);
	//我发布的商品
	ResponsePageQueryDO<List<SecondaryGoodsDTO>> getSecondaryGoodsByUser(SecondaryGoodsByUserPO po);
	//发布商品
	ResponseDO createSecondaryGoods(SecondaryGoodsDO goodsDO);
	
	void createSecondaryViews(Long id);
	
	int getSecondaryGoodsCommentsNum(Long id);
	//得到评论
	ResponsePageQueryDO<List<SecondaryGoodsCommentsDTO>> getSecondaryGoodsComment(SecondaryGoodsCommentsPO po);
	//得到回复
	List<SecondaryGoodsCommentsDTO> getSecondaryGoodsReply(Long commentsId);
	int updateSecondaryComments(SecondaryCommentViewDO viewDO);
	//评论
	int createSecondaryGoodsComment(SecondaryGoodsCommentDO dto);
	//回复
	int createSecondaryGoodsReply(SecondaryGoodsCommentDO dto);
	int updateUserAddress(SecondaryUserAddressDO addressDO);
	int addUserAddress(SecondaryUserAddressDO addressDO);
	List<SecondaryUserAddressDO> getUserAddress(Long userId);
}
