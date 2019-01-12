package com.crazyBird.service.contacts.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.contacts.ContactsDao;
import com.crazyBird.dao.contacts.dataobject.ContactsDetailDO;
import com.crazyBird.dao.contacts.dataobject.ContactsTypeDO;
import com.crazyBird.service.contacts.ContactsService;

@Component("ContactsService")
public class ContactsServiceImpl implements ContactsService{
	
	@Autowired
	private ContactsDao contactsDao;
	
	@Override
	public List<ContactsTypeDO> getContactsType() {
		return contactsDao.getContactsType();
	}

	@Override
	public ContactsDetailDO getContactsDetail(Long id) {
		return contactsDao.getContactsDetail(id);
	}

	@Override
	public List<ContactsDetailDO> getContactsTypeList(Long id) {
		return contactsDao.getContactsTypeList(id);
	}

}
