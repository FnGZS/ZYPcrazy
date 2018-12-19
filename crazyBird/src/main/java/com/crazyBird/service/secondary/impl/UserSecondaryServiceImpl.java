package com.crazyBird.service.secondary.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.secondary.SecondaryCollectionDao;
import com.crazyBird.dao.secondary.SecondaryOrderDao;
import com.crazyBird.dao.secondary.dataobject.CollectionDO;
import com.crazyBird.dao.secondary.dataobject.CollectionSecondaryListPO;
import com.crazyBird.dao.secondary.dataobject.PurchasePO;
import com.crazyBird.dao.secondary.dataobject.UserSecondaryDTO;
import com.crazyBird.dao.secondary.dataobject.SellSecondaryPO;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.secondary.UserSecondaryService;

@Component("UserSecondaryService")
public class UserSecondaryServiceImpl implements UserSecondaryService{

	@Autowired
	private SecondaryOrderDao secondaryOrderDao;
	@Autowired
	private SecondaryCollectionDao secondaryCollectionDao;
	@Override
	public ResponsePageQueryDO<List<UserSecondaryDTO>> getSellList(SellSecondaryPO po) {
		ResponsePageQueryDO<List<UserSecondaryDTO>> response = new ResponsePageQueryDO<>();
		if(po.getSellId() == null) {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("未登录");
			return response;
		}
		response.setPageIndex(po.getPageIndex());
		response.setPageSize(po.getPageSize());
		response.setTotal(secondaryOrderDao.getSellSecondaryCountByUser(po));
		if (response.getTotal() > 0 && response.getTotalPage() > po.getPageIndex()) {
			List<UserSecondaryDTO> list = secondaryOrderDao.getSellSecondaryByUser(po);
			response.setDataResult(list);
		} else {
			response.setMessage("没有更多了");
		}
		return response;
	}
	@Override
	public ResponsePageQueryDO<List<UserSecondaryDTO>> getPurchaseSecondary(PurchasePO po) {
		ResponsePageQueryDO<List<UserSecondaryDTO>> response = new ResponsePageQueryDO<>();
		if(po.getUserId() == null) {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("未登录");
			return response;
		}
		response.setPageIndex(po.getPageIndex());
		response.setPageSize(po.getPageSize());
		response.setTotal(secondaryOrderDao.getPurchaseCountByUser(po));
		if(response.getTotal() > 0 && response.getTotalPage() > po.getPageIndex()) {
			List<UserSecondaryDTO> list = secondaryOrderDao.getPurchaseByUser(po);
			response.setDataResult(list);
		}else {
			response.setMessage("没有更多了");
		}
		return response;
	}
	
	@Override
	public ResponseDO<String> collection(CollectionDO collection) {
		ResponseDO<String> responseDO = new ResponseDO<>();
		if(collection.getUserId() == null) {
			responseDO.setCode(ResponseCode.ERROR);
			return responseDO;
		}
		secondaryCollectionDao.collectionSecondary(collection);
		return responseDO;
	}
	@Override
	public ResponsePageQueryDO<List<UserSecondaryDTO>> getCollectionSecondaryList(CollectionSecondaryListPO po) {
		ResponsePageQueryDO<List<UserSecondaryDTO>> response = new ResponsePageQueryDO<>();
		if(po.getUserId() == null) {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("未登录");
			return response;
		}
		response.setPageIndex(po.getPageIndex());
		response.setPageSize(po.getPageSize());
		response.setTotal(secondaryCollectionDao.getCollectionSecondaryCount(po));
		if(response.getTotal() > 0 && response.getTotalPage() > po.getPageIndex()) {
			List<UserSecondaryDTO> list = secondaryCollectionDao.getCollectionSecondaryList(po);
			response.setDataResult(list);
		}else {
			response.setMessage("没有更多了");
		}
		return response;
	}
	@Override
	public ResponseDO<String> isCollection(CollectionDO collection) {
		ResponseDO<String> responseDO = new ResponseDO<>();
		if(collection.getUserId() == null) {
			responseDO.setCode(ResponseCode.ERROR);
			return responseDO;
		}
		CollectionDO newCollection = secondaryCollectionDao.isCollection(collection);
		if(newCollection == null) {
			responseDO.setCode(ResponseCode.ERROR);
			return responseDO;
		}
		responseDO.setMessage("已想要");
		return responseDO;
	}
	

}
