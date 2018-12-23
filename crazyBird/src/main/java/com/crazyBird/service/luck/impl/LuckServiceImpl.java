package com.crazyBird.service.luck.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.luck.LuckDrawDao;
import com.crazyBird.dao.luck.LuckPrizeDao;
import com.crazyBird.dao.luck.LuckActorDao;
import com.crazyBird.dao.luck.dataobject.IsPartDO;
import com.crazyBird.dao.luck.dataobject.LuckDetailsDTO;
import com.crazyBird.dao.luck.dataobject.LuckDrawDO;
import com.crazyBird.dao.luck.dataobject.LuckListPO;
import com.crazyBird.dao.luck.dataobject.LuckPartakeDTO;
import com.crazyBird.dao.luck.dataobject.LuckPartakePO;
import com.crazyBird.dao.luck.dataobject.LuckPrizeDO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersDTO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersPO;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.luck.LuckService;

@Component("LuckService")
public class LuckServiceImpl implements LuckService{
	
	@Autowired
	private LuckDrawDao luckDrawDao;
	@Autowired
	private LuckActorDao luckActorDao;
	@Autowired
	private LuckPrizeDao luckPrizeDao;

	@Override
	public ResponsePageQueryDO<List<LuckDetailsDTO>> getLuckList(LuckListPO po) {
		ResponsePageQueryDO<List<LuckDetailsDTO>> response = new ResponsePageQueryDO<>();
		response.setPageSize(po.getPageSize());
		response.setTotal((Integer)luckDrawDao.getLuckListCount(po));
		if ((response.getTotal() > 0) && (response.getTotalPage() > po.getPageIndex())) {
			List<LuckDetailsDTO> dataResult = luckDrawDao.getLuckList(po);
			response.setDataResult(dataResult);
		}else {
			response.setMessage("到底了");
		}
		return response;
	}

	@Override
	public ResponsePageQueryDO<List<LuckWinnersDTO>> getLuckWinners(LuckWinnersPO po) {
		ResponsePageQueryDO<List<LuckWinnersDTO>> response = new ResponsePageQueryDO<>();
		response.setPageSize(po.getPageSize());
		response.setTotal((Integer)luckActorDao.getLuckWinnersCount(po));
		if ((response.getTotal() > 0) && (response.getTotalPage() > po.getPageIndex())) {
			List<LuckWinnersDTO> dataResult = luckActorDao.getLuckWinners(po);
			response.setDataResult(dataResult);
		}else {
			response.setMessage("到底了");
		}
		return response;
	}

	@Override
	public ResponsePageQueryDO<List<LuckPartakeDTO>> getLuckPartake(LuckPartakePO po) {
		ResponsePageQueryDO<List<LuckPartakeDTO>> response = new ResponsePageQueryDO<>();
		response.setPageSize(po.getPageSize());
		response.setTotal((Integer)luckActorDao.getLuckPartakeCount(po));
		if ((response.getTotal() > 0) && (response.getTotalPage() > po.getPageIndex())) {
			List<LuckPartakeDTO> dataResult = luckActorDao.getLuckPartake(po);
			response.setDataResult(dataResult);
		}else {
			response.setMessage("到底了");
		}
		return response;
	}

	@Override
	public LuckDetailsDTO getLuckDetails(Long luckId) {
		return luckDrawDao.getLuckDetails(luckId);
	}

	@Override
	public List<LuckPrizeDO> getLuckPrize(Long luckId) {
		return luckPrizeDao.getLuckPrize(luckId);
	}

	@Override
	public ResponseDO<String> isPart(IsPartDO isPart) {
		ResponseDO<String> response = new ResponseDO<String>();
		LuckPartakeDTO luckPart = luckActorDao.seletPart(isPart);
		if(luckPart!=null) {
			response.setMessage("已参与");
			return response;
		}
		response.setMessage("未参与");
		return response;
	}

	@Override
	public ResponseDO<String> addPrize(LuckPrizeDO luckPrize) {
		ResponseDO<String> response = new ResponseDO<String>();
		if(luckPrizeDao.addPrize(luckPrize)) {
			response.setMessage("添加成功");
			return response;
		}
		response.setCode(ResponseCode.ERROR);
		response.setMessage("添加失败");
		return response;
	}

	@Override
	public ResponseDO<String> AddLuck(LuckDrawDO luckDraw) {
		ResponseDO<String> response = new ResponseDO<String>();
		if(luckDrawDao.addLuck(luckDraw)) {
			response.setMessage("添加成功");
			return response;
		}
		response.setCode(ResponseCode.ERROR);
		response.setMessage("添加失败");
		return response;
	}
}
