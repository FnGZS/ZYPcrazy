package com.crazyBird.service.secondary;

import java.util.List;

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
	int deleteSecondaryGoods(Long id);
	void createSecondaryViews(Long id);
	
	int getSecondaryGoodsCommentsNum(Long id);
	//得到评论
	ResponsePageQueryDO<List<SecondaryGoodsCommentsDTO>> getSecondaryGoodsComment(SecondaryGoodsCommentsPO po);
	//得到回复
	List<SecondaryGoodsCommentsDTO> getSecondaryGoodsReply(Long commentsId);
	int updateSecondaryComments(SecondaryCommentViewDO viewDO);
	//更新评论状态(变成不再提醒)
	int updateSecondaryCommentsNoSee(SecondaryCommentViewDO viewDO);
	//评论
	int createSecondaryGoodsComment(SecondaryGoodsCommentDO dto);
	//回复
	int createSecondaryGoodsReply(SecondaryGoodsCommentDO dto);
	int updateUserAddress(SecondaryUserAddressDO addressDO);
	int addUserAddress(SecondaryUserAddressDO addressDO);
	List<SecondaryUserAddressDO> getUserAddress(Long userId);
	int setUserAddress(Long userId);
	int deleteUserAddress(Long id);
	int getNewCommentCount(Long id);
	int getNewSecondaryViolationCount(Long id);
	ResponsePageQueryDO<List<SecondaryCommetsMessageDTO>> getCommentMessage(SecondaryGoodsCommentsPO po);
	List<SecondaryMessageDTO> getSecondaryMessage(Long userId);
	void updateSecondaryMessage(Long id);
	//变更审核信息状态（不再提醒）
	void updateSecondaryMessageNoSee(Long id);
	SecondaryMessageDTO getSecondaryMessageDetail(Long id);
	//更新商品状态 （被买）
	int updateSecondaryGoodsPay(Long id);
	
	//更新商品状态 （审核未通过）
	int updateSecondaryGoodsViolation(Long id);
	//更新商品状态 （在卖）
	int updateSecondaryGoodsOnline(Long id);
	//获得订单号对应的商品id
	Long getSecondaryGoodsId(String orderId);
}
