package com.crazyBird.service.affairs.impl;

import com.crazyBird.dao.affairs.AffairsDao;
import com.crazyBird.dao.affairs.dataobject.AddAffairDO;
import com.crazyBird.dao.affairs.dataobject.AffairsDO;
import com.crazyBird.dao.affairs.dataobject.AffairsPO;
import com.crazyBird.service.affairs.AffairsService;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("AffairsService")
public class AffairsServiceImpl implements AffairsService {
	@Autowired
	private AffairsDao affairsDao;

	public ResponsePageQueryDO<List<AffairsDO>> getAffairsList(AffairsPO po) {
		ResponsePageQueryDO<List<AffairsDO>> response = new ResponsePageQueryDO<>();
		response.setPageSize(po.getPageSize());
		response.setTotal((Integer)affairsDao.getAffairsCount(po));
		if ((response.getTotal() > 0) && (response.getTotalPage() > po.getPageIndex())) {
			List<AffairsDO> dataResult = affairsDao.getAffairs(po);
			response.setDataResult(dataResult);
		}
		return response;
	}

	@Override
	public ResponseDO<Long> addAffair(AddAffairDO affair) {
		ResponseDO<Long> result = new ResponseDO<>();
		if(affair!=null) {
			affairsDao.addAffairs(affair);
			result.setMessage("添加成功");
			result.setDataResult((long) 1);
			return result;
		}
		result.setDataResult((long) 0);
		return result;
	}

	
}
