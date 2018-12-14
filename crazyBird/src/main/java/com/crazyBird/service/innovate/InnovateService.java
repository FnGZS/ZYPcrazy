package com.crazyBird.service.innovate;

import java.util.List;

import com.crazyBird.dao.innovate.dataobject.InnovateEnterpriseDO;
import com.crazyBird.dao.innovate.dataobject.InnovateEnterpriseListDO;

public interface InnovateService {
	List<InnovateEnterpriseListDO> getInnovateEnterpriseList();
	InnovateEnterpriseDO getInnovateEnterprise(Integer id);
	String getInnovateBackground();
	
}
