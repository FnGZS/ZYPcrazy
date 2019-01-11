package com.crazyBird.controller.contacts.model;

import com.crazyBird.controller.base.AbstractPageFlagModel;

public class ContactsDetailModel extends AbstractPageFlagModel{
	private ContactsDetailItem details;

	public ContactsDetailItem getDetails() {
		return details;
	}

	public void setDetails(ContactsDetailItem details) {
		this.details = details;
	}
	
}
