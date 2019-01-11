package com.crazyBird.controller.contacts.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class ContactsDetailModel extends AbstractFlagModel{
	private List<ContactsDetailItem> details;
	
	public List<ContactsDetailItem> getDetails() {
		return details;
	}

	public void setDetails(List<ContactsDetailItem> details) {
		this.details = details;
	}

}
