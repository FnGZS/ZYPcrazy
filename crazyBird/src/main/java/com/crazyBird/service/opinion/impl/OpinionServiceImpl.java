package com.crazyBird.service.opinion.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.opinion.OpinionDao;
import com.crazyBird.dao.opinion.dataobject.OpinionDO;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.opinion.OpinionService;

@Component("OpinionService")
public class OpinionServiceImpl implements OpinionService {

	@Autowired
	private OpinionDao opinionDao;
	
	@Override
	public ResponseDO<Long> creat(OpinionDO opinion) {
		ResponseDO<Long> result = new ResponseDO<>();
		if(opinion != null) {
			opinionDao.creat(opinion);
			result.setMessage("添加成功");
			result.setDataResult((long) 1);
			return result;
		}
		result.setDataResult((long) 0);
		return result;
	}

}
