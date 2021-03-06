package com.crazyBird.dao.secondary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crazyBird.dao.secondary.dataobject.SearchSecondaryGoodsPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCommentViewDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCommetsMessageDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsByUserPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsCommentDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsCommentsDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsCommentsPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryMessageDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryUserAddressDO;

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
	int getSecondaryGoodsCountByUser(SecondaryGoodsByUserPO po);
	List<SecondaryGoodsDTO> getSecondaryGoodsByUser(SecondaryGoodsByUserPO po);
	//发布商品
	int createSecondaryGoods(SecondaryGoodsDO goodsDO);
	// 删除商品（更改商品状态）
	int deleteSecondaryGoods(Long id);
	
	int updateSecondaryGoods(SecondaryGoodsDO goodsDO);
	void createSecondaryViews(Long id);
	
	//得到评论
	int getSecondaryGoodsCommentCount(Long id);
	
	List<SecondaryGoodsCommentsDTO> getSecondaryGoodsComment(SecondaryGoodsCommentsPO po);
	int getSecondaryGoodsCommentsNum(Long id);
	//得到回复
	List<SecondaryGoodsCommentsDTO> getSecondaryGoodsReply(Long commentsId);
	//更新评论状态(变成已读)
	int updateSecondaryComments(SecondaryCommentViewDO viewDO);
	//更新评论状态(变成不再提醒)
	int updateSecondaryCommentsNoSee(Long id);
	//评论
	int createSecondaryGoodsComment(SecondaryGoodsCommentDO dto);
	//回复
	//int createSecondaryGoodsReply(SecondaryGoodsCommentDO dto);
	//得到用户地址
	List<SecondaryUserAddressDO> getUserAddress(Long userId);
	int setUserAddress(Long userId);
	
	int updateUserAddress(SecondaryUserAddressDO addressDO);
	
	int addUserAddress(SecondaryUserAddressDO addressDO);
	
	int deleteUserAddress(Long id);
	
	int getNewCommentCount(Long id);
	
	int getNewSecondaryViolationCount(Long id);
	int getCommentMessageCount(Long id);
	List<SecondaryCommetsMessageDTO> getCommentMessage(SecondaryGoodsCommentsPO po);
	
	List<SecondaryMessageDTO> getSecondaryMessage(Long userId);
	SecondaryMessageDTO getSecondaryMessageDetail(Long id);
	//变更审核信息状态（已读）
	void updateSecondaryMessage(Long id);
	
	//变更审核信息状态（不再提醒）
	void updateSecondaryMessageNoSee(Long id);
	
	//更新商品状态 （被买）
	int updateSecondaryGoodsPay(Long id);
	
	//更新商品状态 （审核未通过）
	int updateSecondaryGoodsViolation(Long id);
	//更新商品状态 （在卖）
	int updateSecondaryGoodsOnline(Long id);
	//获得订单号对应的商品id
	Long getSecondaryGoodsId(String orderId);
	
	
	
	
	
	
	
	
	
	
	
	
}
