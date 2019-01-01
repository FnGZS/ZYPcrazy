package com.crazyBird.dao.secondary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crazyBird.dao.secondary.dataobject.CollectionDO;
import com.crazyBird.dao.secondary.dataobject.CollectionSecondaryListPO;
import com.crazyBird.dao.secondary.dataobject.UserSecondaryDTO;

public interface SecondaryCollectionDao {

	void collectionSecondary(CollectionDO collection);

	List<UserSecondaryDTO> getCollectionSecondaryList(CollectionSecondaryListPO po);

	int getCollectionSecondaryCount(CollectionSecondaryListPO po);

	CollectionDO isCollection(CollectionDO collection);

	Integer seletCountByCollection(@Param("goodsId") Long goodsId);

	void delete(Long id);


}
