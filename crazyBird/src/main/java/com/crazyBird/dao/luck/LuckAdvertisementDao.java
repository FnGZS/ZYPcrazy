package com.crazyBird.dao.luck;

import java.util.List;

import com.crazyBird.dao.luck.dataobject.AdvertisementDO;

public abstract interface LuckAdvertisementDao {

	List<AdvertisementDO> getAdvertisement();

}
