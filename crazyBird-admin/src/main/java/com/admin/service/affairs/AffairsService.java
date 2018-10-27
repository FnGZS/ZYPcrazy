package com.admin.service.affairs;

import java.util.List;

import com.admin.dao.affairs.dataobject.AffairsDO;
import com.admin.dao.affairs.dataobject.AffairsPO;
import com.admin.service.base.ResponsePageQueryDO;

public interface AffairsService {

	ResponsePageQueryDO<List<AffairsDO>> getAffairsList(AffairsPO po);

}
