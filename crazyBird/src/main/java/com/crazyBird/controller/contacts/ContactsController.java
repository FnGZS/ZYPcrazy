package com.crazyBird.controller.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.contacts.model.ContactsDetailModel;
import com.crazyBird.controller.contacts.model.ContactsTypeModel;

@Controller
@RequestMapping("/contacts")
public class ContactsController {
	
	@Autowired
	private  ContactsProcess contactsProcess;
	
	/**
	 * 获取通讯类型
	 * @param param
	 * @return
	 * **/
	
	@RequestMapping(value = "/getContactsType",method = RequestMethod.GET)
	@ResponseBody
	public ContactsTypeModel getContactsType() {
		return contactsProcess.getContactsType();
	}
	
	/**
	 * 获取通讯详情
	 * @param param
	 * @return
	 * **/
	
	@RequestMapping(value = "/getContactsDetail/{id}",method = RequestMethod.GET)
	@ResponseBody
	public ContactsDetailModel getContactsDetail(@PathVariable Long id) {
		return contactsProcess.getContactsDetail(id);
	}
}
