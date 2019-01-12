package com.crazyBird.controller.contacts.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class ContactsTypeListModel extends AbstractFlagModel{
	private List<ContactsTypeListItem> list;

	public List<ContactsTypeListItem> getList() {
		return list;
	}

	public void setList(List<ContactsTypeListItem> list) {
		this.list = list;
	}
}
