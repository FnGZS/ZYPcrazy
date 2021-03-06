package com.crazyBird.dao.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.crazyBird.controller.user.param.JYZParam;
import com.crazyBird.dao.user.dataobject.BindingChangeDO;
import com.crazyBird.dao.user.dataobject.BindingDO;
import com.crazyBird.dao.user.dataobject.BingDO;
import com.crazyBird.dao.user.dataobject.UserDO;
import com.crazyBird.dao.user.dataobject.UserFormDO;

public abstract interface UserDao {
	 UserDO seletUser(String paramString);

	 void updateBinding(BindingDO paramBindingDO);

	 void updateBinding(BingDO paramBingDO);

	 UserDO seletUserBySnum(@Param("schoolNum") Long paramLong);

	UserDO seletUserByPhone(String phone);

	void updateUser(UserDO phoneUser);

	UserDO seletUserByOpenId(@Param("openId") String openId);

	void changePhone(BindingChangeDO bindingChange);
	
	void insertFormId(UserFormDO fromDO);
	
	UserFormDO getFormId(Long userId);
	
	void autoDeleteFormId();
	void deleteFormId(Long id);

	@Insert(" INSERT INTO jyz(name,area,areaname,address,brandname,type,discount,exhaust,position,lat,lon,price,gastprice,fwlsmc)"
			+ " VALUS(#{name},#{area},#{areaname},#{address},#{brandname},#{type},#{discount},#{exhaust},#{position},#{lat},#{lon},#{price},#{gastprice},#{fwlsmc})")
	void addjyz(JYZParam param);


}
