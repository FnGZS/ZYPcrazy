package com.admin.service.affairs;

import java.util.List;

import com.admin.dao.affairs.dataobject.AddAffairDO;
import com.admin.dao.affairs.dataobject.AffairsDO;
import com.admin.dao.affairs.dataobject.AffairsPO;
import com.admin.service.base.ResponseDO;
import com.admin.service.base.ResponsePageQueryDO;

public interface AffairsService {

	ResponsePageQueryDO<List<AffairsDO>> getAffairsList(AffairsPO po);

	ResponseDO<Long> addAffair(AddAffairDO affair);

	ResponseDO<Long> deleteAffair(Long id);

}
