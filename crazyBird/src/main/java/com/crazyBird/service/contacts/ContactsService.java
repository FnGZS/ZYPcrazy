package com.crazyBird.service.contacts;

import java.util.List;

import com.crazyBird.dao.contacts.dataobject.ContactsDetailDO;
import com.crazyBird.dao.contacts.dataobject.ContactsTypeDO;

public abstract interface ContactsService {

	List<ContactsTypeDO> getContactsType();

	ContactsDetailDO getContactsDetail(Long id);

	List<ContactsDetailDO> getContactsTypeList(Long id);

}
