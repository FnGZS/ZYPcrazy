package com.crazyBird.service.lost.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.affairs.dataobject.AffairsDO;
import com.crazyBird.dao.lost.LostArticleDao;
import com.crazyBird.dao.lost.LostArticleTypeDao;
import com.crazyBird.dao.lost.LostMessageDao;
import com.crazyBird.dao.lost.dataobject.LostArticleDO;

import com.crazyBird.dao.lost.dataobject.LostDTO;
import com.crazyBird.dao.lost.dataobject.LostPO;
import com.crazyBird.dao.lost.dataobject.LostTypeDO;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.lost.LostService;
@Component("LostService")
public class LostServiceImpl implements LostService{
	
	@Autowired
	private LostArticleTypeDao lostArticleTypeDao;
	@Autowired
	private LostArticleDao lostArticleDao;
	@Autowired
	private LostMessageDao lostMessageDao;
	
	@Override
	public List<LostTypeDO> getLostMessage() {
		return lostMessageDao.getLostMessage();
	}
	
	@Override
	public List<LostTypeDO> getLostType() {
		return lostArticleTypeDao.getLostType();
	}

	@Override
	public ResponsePageQueryDO<List<LostDTO>> getLostList(LostPO po) {
		ResponsePageQueryDO<List<LostDTO>> response = new ResponsePageQueryDO<>();
		response.setPageSize(po.getPageSize());
		response.setTotal((Integer)lostArticleDao.getLostCount(po));
		if ((response.getTotal() > 0) && (response.getTotalPage() > po.getPageIndex())) {
			List<LostDTO> dataResult = lostArticleDao.getLost(po);
			response.setDataResult(dataResult);
		}else {
			response.setMessage("到底了");
		}
		return response;
	}

	@Override
	public LostDTO getLostDetails(Long id) {
		LostDTO lost = lostArticleDao.getLostDetails(id);
		lostArticleDao.updateBrow(lost);
		return lost;
	}

	@Override
	public ResponseDO<LostDTO> lostInput(LostArticleDO DO) {
		ResponseDO<LostDTO> response=new ResponseDO<>();
		int status=lostArticleDao.insert(DO);
		if(status==1) {
			response.setCode(ResponseCode.SUCCESS);
			response.setMessage("录入成功");
		}else {
			response.setMessage("录入失败");
			response.setCode(ResponseCode.ERROR);
		}
		return response;
	}

	

	@Override
	public ResponseDO<LostDTO> lostDelete(Long id) {
		ResponseDO<LostDTO> response=new ResponseDO<>();
		int status=lostArticleDao.delete(id);
		if(status==1) {
			response.setCode(ResponseCode.SUCCESS);
			response.setMessage("删除成功");
		}else {
			response.setMessage("删除失败");
			response.setCode(ResponseCode.ERROR);
		}
		return response;
	}
	
}
