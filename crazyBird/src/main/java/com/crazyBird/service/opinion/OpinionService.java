package com.crazyBird.service.opinion;

import com.crazyBird.dao.opinion.dataobject.OpinionDO;
import com.crazyBird.service.base.ResponseDO;

public interface OpinionService {

	ResponseDO<Long> creat(OpinionDO opinion);

}
