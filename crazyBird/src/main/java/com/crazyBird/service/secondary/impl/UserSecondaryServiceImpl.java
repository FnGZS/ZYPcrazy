package com.crazyBird.service.secondary.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.secondary.SecondaryUserDao;
import com.crazyBird.dao.secondary.dataobject.SellSecondaryDTO;
import com.crazyBird.dao.secondary.dataobject.SellSecondaryPO;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.secondary.UserSecondaryService;

@Component("UserSecondaryService")
public class UserSecondaryServiceImpl implements UserSecondaryService{

	@Autowired
	private SecondaryUserDao secondaryUserDao;
	@Override
	public ResponsePageQueryDO<List<SellSecondaryDTO>> getSellList(SellSecondaryPO po) {
		ResponsePageQueryDO<List<SellSecondaryDTO>> response = new ResponsePageQueryDO<>();
		response.setPageIndex(po.getPageIndex());
		response.setPageSize(po.getPageSize());
		response.setTotal(secondaryUserDao.getSellSecondaryCountByUser(po.getUserId()));
		if (response.getTotal() > 0 && response.getTotalPage() > po.getPageIndex()) {
			List<SellSecondaryDTO> list = secondaryUserDao.getSellSecondaryByUser(po);
			response.setDataResult(list);
			response.setCode(ResponseCode.SUCCESS);
		} else {
			response.setMessage("没有更多了");
		}
		return response;
	}

}
