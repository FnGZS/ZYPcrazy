package com.crazyBird.service.secondary.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.secondary.SecondaryDao;
import com.crazyBird.dao.secondary.SecondaryTypeDao;
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
import com.crazyBird.dao.secondary.dataobject.SecondarySlideDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryTypeDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryUserAddressDO;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.secondary.SecondaryService;
@Component("SecondaryService")
public class SecondaryServiceImpl implements SecondaryService{
	@Autowired
	private SecondaryDao secondaryDao;
	@Autowired
	private SecondaryTypeDao secondaryTypeDao;
	@Override
	public List<SecondarySlideDO> getSecondarySlide() {

		return secondaryTypeDao.getSecondarySlide();
	}

	@Override
	public List<SecondaryTypeDO> getSecondaryType() {

		return secondaryTypeDao.getSecondaryType();
	}

	@Override
	public List<SecondaryTypeDO> getSecondaryWay() {

		return secondaryTypeDao.getSecondaryWay();
	}

	@Override
	public List<SecondaryTypeDO> getSecondaryTradingWay() {

		return secondaryTypeDao.getSecondaryTradingWay();
	}

	@Override
	public ResponsePageQueryDO<List<SecondaryGoodsDTO>> getSecondaryGoodsList(SecondaryGoodsPO po) {
		ResponsePageQueryDO<List<SecondaryGoodsDTO>> response = new ResponsePageQueryDO<>();
		response.setPageIndex(po.getPageIndex());
		response.setPageSize(po.getPageSize());
		response.setTotal(secondaryDao.getSecondaryGoodsCount(po.getGoodsType()));

		if (response.getTotal() > 0 && response.getTotalPage() > po.getPageIndex()) {
			List<SecondaryGoodsDTO> list = secondaryDao.getSecondaryGoodsList(po);
			response.setDataResult(list);
			response.setCode(ResponseCode.SUCCESS);
		} else {
			response.setMessage("没有更多商品了");
		}
		return response;
	}

	@Override
	public List<SecondaryGoodsDTO> getSecondaryGoods(Long id) {
		
		return secondaryDao.getSecondaryGoods(id);
	}

	@Override
	public ResponsePageQueryDO<List<SecondaryGoodsDTO>> searchSecondaryGoods(SearchSecondaryGoodsPO po) {
		ResponsePageQueryDO<List<SecondaryGoodsDTO>> response = new ResponsePageQueryDO<>();
		response.setPageIndex(po.getPageIndex());
		response.setPageSize(po.getPageSize());
		response.setTotal(secondaryDao.searchSecondaryGoodsCount(po.getKeyWord()));
		if (response.getTotal() > 0 && response.getTotalPage() > po.getPageIndex()) {
			List<SecondaryGoodsDTO> list = secondaryDao.searchSecondaryGoods(po);
			response.setDataResult(list);
			response.setCode(ResponseCode.SUCCESS);
		} else {
			response.setMessage("没有更多商品了");
		}
		return response;
	}

	@Override
	public ResponsePageQueryDO<List<SecondaryGoodsDTO>> getSecondaryGoodsByUser(SecondaryGoodsByUserPO po) {
		ResponsePageQueryDO<List<SecondaryGoodsDTO>> response = new ResponsePageQueryDO<>();
		response.setPageIndex(po.getPageIndex());
		response.setPageSize(po.getPageSize());
		response.setTotal(secondaryDao.getSecondaryGoodsCountByUser(po));
		if (response.getTotal() > 0 && response.getTotalPage() > po.getPageIndex()) {
			List<SecondaryGoodsDTO> list = secondaryDao.getSecondaryGoodsByUser(po);
			response.setDataResult(list);
			response.setCode(ResponseCode.SUCCESS);
		} else {
			response.setMessage("到底了");
		}
		return response;
	
	}

	@Override
	public ResponseDO createSecondaryGoods(SecondaryGoodsDO goodsDO) {
		ResponseDO response = new ResponseDO<>();
		int flag = secondaryDao.createSecondaryGoods(goodsDO);
		if(flag>0) {
			response.setCode(ResponseCode.SUCCESS);
			response.setMessage("添加成功");
		}
		else {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("添加失败");
		}
		
		return response;
	}

	@Override
	public void createSecondaryViews(Long id) {
		secondaryDao.createSecondaryViews(id);
	}

	@Override
	public ResponsePageQueryDO<List<SecondaryGoodsCommentsDTO>> getSecondaryGoodsComment(SecondaryGoodsCommentsPO po) {
		ResponsePageQueryDO<List<SecondaryGoodsCommentsDTO>> response= new ResponsePageQueryDO<>();
		response.setPageIndex(po.getPageIndex());
		response.setPageSize(po.getPageSize());
		response.setTotal(secondaryDao.getSecondaryGoodsCommentCount(po.getId()));
		if (response.getTotal() > 0 && response.getTotalPage() > po.getPageIndex()) {
			List<SecondaryGoodsCommentsDTO> list = secondaryDao.getSecondaryGoodsComment(po);
			response.setDataResult(list);
			response.setCode(ResponseCode.SUCCESS);
		} else {
			response.setMessage("到底了");
		}
		return response;
	}

	@Override
	public List<SecondaryGoodsCommentsDTO> getSecondaryGoodsReply(Long commentsId) {
		
		return secondaryDao.getSecondaryGoodsReply(commentsId);
	}

	@Override
	public int createSecondaryGoodsComment(SecondaryGoodsCommentDO dto) {

		return secondaryDao.createSecondaryGoodsComment(dto);
	}

	@Override
	public int createSecondaryGoodsReply(SecondaryGoodsCommentDO dto) {

		return secondaryDao.createSecondaryGoodsComment(dto);
	}

	@Override
	public int getSecondaryGoodsCommentsNum(Long id) {

		return secondaryDao.getSecondaryGoodsCommentsNum(id);
	}

	@Override
	public List<SecondaryUserAddressDO> getUserAddress(Long userId) {
		return secondaryDao.getUserAddress(userId);
	}

	@Override
	public int updateUserAddress(SecondaryUserAddressDO addressDO) {
		return secondaryDao.updateUserAddress(addressDO);
	}

	@Override
	public int addUserAddress(SecondaryUserAddressDO addressDO) {

		return secondaryDao.addUserAddress(addressDO);
	}

	@Override
	public int updateSecondaryComments(SecondaryCommentViewDO viewDO) {
		
		return secondaryDao.updateSecondaryComments(viewDO);
	}

	@Override
	public int deleteSecondaryGoods(Long id) {
	
		return secondaryDao.deleteSecondaryGoods(id);
	}

	@Override
	public int getNewCommentCount(Long id) {

		return secondaryDao.getNewCommentCount(id);
	}

	@Override
	public int getNewSecondaryViolationCount(Long id) {

		return secondaryDao.getNewSecondaryViolationCount(id);
	}

	@Override
	public ResponsePageQueryDO<List<SecondaryCommetsMessageDTO>> getCommentMessage(SecondaryGoodsCommentsPO po) {
		 ResponsePageQueryDO<List<SecondaryCommetsMessageDTO>> response = new ResponsePageQueryDO<>();
			response.setPageIndex(po.getPageIndex());
			response.setPageSize(po.getPageSize());
			response.setTotal(secondaryDao.getCommentMessageCount(po.getId()));
			if (response.getTotal() > 0 && response.getTotalPage() > po.getPageIndex()) {
				List<SecondaryCommetsMessageDTO> list = secondaryDao.getCommentMessage(po);
				response.setDataResult(list);
				response.setCode(ResponseCode.SUCCESS);
			} else {
				response.setMessage("到底了");
			}
		return response;
	}

}
