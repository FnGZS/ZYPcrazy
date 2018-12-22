package com.crazyBird.service.luck.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.luck.LuckDrawDao;
import com.crazyBird.dao.luck.LuckActorDao;
import com.crazyBird.dao.luck.dataobject.LuckDetailsDO;
import com.crazyBird.dao.luck.dataobject.LuckListDO;
import com.crazyBird.dao.luck.dataobject.LuckListPO;
import com.crazyBird.dao.luck.dataobject.LuckLuckPartakeDO;
import com.crazyBird.dao.luck.dataobject.LuckPartakePO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersDO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersPO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.luck.LuckService;

@Component("LuckService")
public class LuckServiceImpl implements LuckService{
	
	@Autowired
	private LuckDrawDao luckDrawDao;
	@Autowired
	private LuckActorDao luckActorDao;

	@Override
	public ResponsePageQueryDO<List<LuckListDO>> getLuckList(LuckListPO po) {
		ResponsePageQueryDO<List<LuckListDO>> response = new ResponsePageQueryDO<>();
		response.setPageSize(po.getPageSize());
		response.setTotal((Integer)luckDrawDao.getLuckListCount(po));
		if ((response.getTotal() > 0) && (response.getTotalPage() > po.getPageIndex())) {
			List<LuckListDO> dataResult = luckDrawDao.getLuckList(po);
			response.setDataResult(dataResult);
		}else {
			response.setMessage("到底了");
		}
		return response;
	}

	@Override
	public ResponsePageQueryDO<List<LuckWinnersDO>> getLuckWinners(LuckWinnersPO po) {
		ResponsePageQueryDO<List<LuckWinnersDO>> response = new ResponsePageQueryDO<>();
		response.setPageSize(po.getPageSize());
		response.setTotal((Integer)luckActorDao.getLuckWinnersCount(po));
		if ((response.getTotal() > 0) && (response.getTotalPage() > po.getPageIndex())) {
			List<LuckWinnersDO> dataResult = luckActorDao.getLuckWinners(po);
			response.setDataResult(dataResult);
		}else {
			response.setMessage("到底了");
		}
		return response;
	}

	@Override
	public ResponsePageQueryDO<List<LuckLuckPartakeDO>> getLuckPartake(LuckPartakePO po) {
		ResponsePageQueryDO<List<LuckLuckPartakeDO>> response = new ResponsePageQueryDO<>();
		response.setPageSize(po.getPageSize());
		response.setTotal((Integer)luckActorDao.getLuckPartakeCount(po));
		if ((response.getTotal() > 0) && (response.getTotalPage() > po.getPageIndex())) {
			List<LuckLuckPartakeDO> dataResult = luckActorDao.getLuckPartake(po);
			response.setDataResult(dataResult);
		}else {
			response.setMessage("到底了");
		}
		return response;
	}

	@Override
	public LuckDetailsDO getLuckDetails(Long luckId) {
		return luckDrawDao.getLuckDetails(luckId);
	}
}
