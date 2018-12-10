package com.crazyBird.dao.innovate;

import java.util.List;

import com.crazyBird.dao.innovate.dataobject.InnovateEnterpriseDO;
import com.crazyBird.dao.innovate.dataobject.InnovateEnterpriseListDO;

public interface InnovateDao {
	List<InnovateEnterpriseListDO> getInnovateEnterpriseList();
	InnovateEnterpriseDO getInnovateEnterprise(Integer id);
	
}
