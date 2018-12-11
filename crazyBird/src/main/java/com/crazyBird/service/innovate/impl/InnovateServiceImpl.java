package com.crazyBird.service.innovate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.innovate.InnovateDao;
import com.crazyBird.dao.innovate.dataobject.InnovateEnterpriseDO;
import com.crazyBird.dao.innovate.dataobject.InnovateEnterpriseListDO;
import com.crazyBird.service.innovate.InnovateService;
@Component("InnovateService")
public class InnovateServiceImpl implements InnovateService{
	@Autowired
	private InnovateDao innovateDao;
	@Override
	public List<InnovateEnterpriseListDO> getInnovateEnterpriseList() {

		return innovateDao.getInnovateEnterpriseList();
	}

	@Override
	public InnovateEnterpriseDO getInnovateEnterprise(Integer id) {

		return innovateDao.getInnovateEnterprise(id);
	}

	@Override
	public String getInnovateBackground() {
		return innovateDao.getInnovateBackground();
	}
	
}
