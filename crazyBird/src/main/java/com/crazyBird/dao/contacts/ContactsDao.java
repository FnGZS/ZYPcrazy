package com.crazyBird.dao.contacts;

import java.util.List;

import com.crazyBird.dao.contacts.dataobject.ContactsDetailDO;
import com.crazyBird.dao.contacts.dataobject.ContactsTypeDO;

public abstract interface ContactsDao {

	List<ContactsTypeDO> getContactsType();

	ContactsDetailDO getContactsDetail(Long id);

}
