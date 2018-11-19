package com.crazyBird.dao.user;

import com.crazyBird.dao.user.dataobject.VerificationDO;

public interface VerificationDao {

	void saveVerCode(VerificationDO verification);

	VerificationDO seletVerification(String phone);


}
