package com.admin.dao.affairs;

import java.util.List;

import com.admin.dao.affairs.dataobject.AffairsDO;
import com.admin.dao.affairs.dataobject.AffairsPO;
import com.admin.dao.affairs.dataobject.AddAffairDO;

public interface AffairsDao {

	int getAffairsCount(AffairsPO po);

	List<AffairsDO> getAffairs(AffairsPO po);

	AffairsDO getAffairsDetails(Long id);

	void deleteAffair(Long id);
	
	void update(AffairsDO affairs);
	
	void addAffairs(AddAffairDO affair);

}
