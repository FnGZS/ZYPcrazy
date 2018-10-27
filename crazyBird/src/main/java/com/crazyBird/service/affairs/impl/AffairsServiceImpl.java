package com.crazyBird.service.affairs.impl;

import com.crazyBird.dao.affairs.AffairsDao;
import com.crazyBird.dao.affairs.dataobject.AffairsDO;
import com.crazyBird.dao.affairs.dataobject.AffairsPO;
import com.crazyBird.service.affairs.AffairsService;
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
		response.setTotal(affairsDao.getAffairsCount(po));
		if ((response.getTotal() > 0) && (response.getTotalPage() > po.getPageIndex())) {
			List<AffairsDO> dataResult = affairsDao.getAffairs(po);
			response.setDataResult(dataResult);
		}
		return response;
	}
}
