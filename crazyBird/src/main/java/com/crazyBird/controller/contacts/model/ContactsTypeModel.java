package com.crazyBird.controller.contacts.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class ContactsTypeModel extends AbstractFlagModel{
	
	private List<ContactsTypeItem> contactsTypeList;

	public List<ContactsTypeItem> getContactsTypeList() {
		return contactsTypeList;
	}

	public void setContactsTypeList(List<ContactsTypeItem> contactsTypeList) {
		this.contactsTypeList = contactsTypeList;
	}
	
}
