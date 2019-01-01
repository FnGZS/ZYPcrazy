package com.crazyBird.controller.opinion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.opinion.model.OpinionCreatModel;
import com.crazyBird.controller.opinion.param.OpinionParam;
import com.crazyBird.dao.opinion.dataobject.OpinionDO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.opinion.OpinionService;

@Component
public class OpinionProcess {

	@Autowired
	private OpinionService opinionService;
	
	public OpinionCreatModel creat(OpinionParam param) {
		OpinionCreatModel model = new OpinionCreatModel();
		OpinionDO opinion = new OpinionDO();
		opinion.setProposal(param.getProposal());
		opinion.setPhone(param.getPhone());
		opinion.setWxid(param.getWxid());
		ResponseDO<Long> response = opinionService.creat(opinion);
		if(!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
			model.setResult(response.getDataResult());
			return model;
		}
		model.setMessage(response.getMessage());
		model.setResult(response.getDataResult());
		return model;
	}

}
