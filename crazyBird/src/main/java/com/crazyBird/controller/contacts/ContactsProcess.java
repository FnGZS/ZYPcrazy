package com.crazyBird.controller.contacts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.base.BaseProcess;
import com.crazyBird.controller.contacts.model.ContactsDetailItem;
import com.crazyBird.controller.contacts.model.ContactsDetailModel;
import com.crazyBird.controller.contacts.model.ContactsTypeItem;
import com.crazyBird.controller.contacts.model.ContactsTypeModel;
import com.crazyBird.dao.contacts.dataobject.ContactsDetailDO;
import com.crazyBird.dao.contacts.dataobject.ContactsTypeDO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.contacts.ContactsService;
import com.crazyBird.utils.CollectionUtil;

@Component
public class ContactsProcess extends BaseProcess{
	
	@Autowired
	private ContactsService contactsService;
	
	public ContactsTypeModel getContactsType() {
		ContactsTypeModel model = new ContactsTypeModel();
		List<ContactsTypeDO> tags = contactsService.getContactsType(); 
		if(CollectionUtil.isNotEmpty(tags)) {
			List<ContactsTypeItem> items = new ArrayList<ContactsTypeItem>();
			for(ContactsTypeDO tag : tags) {
				ContactsTypeItem item = new ContactsTypeItem();
				item.setId(tag.getId());
				item.setTypeName(tag.getTypeName());
				items.add(item);
			}
			model.setContactsTypeList(items);
		}
		return model;
	}

	public ContactsDetailModel getContactsDetail(Long id) {
		ContactsDetailModel model = new ContactsDetailModel();
		ContactsDetailDO detail = contactsService.getContactsDetail(id);
		if(detail != null) {
			ContactsDetailItem item = new ContactsDetailItem();
			item.setId(detail.getId());
			item.setName(detail.getName());
			item.setPhone(detail.getPhone());
			model.setDetails(item);
			return model;
		}
		model.setCode(HttpCodeEnum.ERROR.getCode());
		model.setMessage("无此项");
		return model;
	}

}
