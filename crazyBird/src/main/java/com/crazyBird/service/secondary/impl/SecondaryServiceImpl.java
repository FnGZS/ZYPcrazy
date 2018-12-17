package com.crazyBird.service.secondary.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.secondary.SecondaryDao;
import com.crazyBird.dao.secondary.SecondaryTypeDao;
import com.crazyBird.dao.secondary.dataobject.SearchSecondaryGoodsPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsByUserPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsPO;
import com.crazyBird.dao.secondary.dataobject.SecondarySlideDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryTypeDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDO;
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
		System.out.println(response.getTotal());
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
		response.setTotal(secondaryDao.getSecondaryGoodsCountByUser(po.getId()));
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

}
