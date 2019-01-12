package com.crazyBird.controller.contacts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.base.BaseProcess;
import com.crazyBird.controller.contacts.model.ContactsDetailModel;
import com.crazyBird.controller.contacts.model.ContactsTypeItem;
import com.crazyBird.controller.contacts.model.ContactsTypeListItem;
import com.crazyBird.controller.contacts.model.ContactsTypeListModel;
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
			model.setId(detail.getId());
			model.setTypeName(detail.getTypeName());
			model.setName(detail.getName());
			model.setPhone(detail.getPhone());
			model.setPhone2(detail.getPhone2());
			model.setManger(detail.getManger());
			model.setPic(detail.getPic());
			return model;
		}
		model.setCode(HttpCodeEnum.ERROR.getCode());
		model.setMessage("无此项");
		return model;
	}

	public ContactsTypeListModel getContactsTypeList(Long id) {
		ContactsTypeListModel model = new ContactsTypeListModel();
		List<ContactsDetailDO> tags = contactsService.getContactsTypeList(id);
		if(CollectionUtil.isNotEmpty(tags)) {
			List<ContactsTypeListItem> items = new ArrayList<ContactsTypeListItem>();
			for(ContactsDetailDO tag : tags) {
				ContactsTypeListItem item = new ContactsTypeListItem();
				item.setId(tag.getId());
				item.setTypeName(tag.getTypeName());
				item.setName(tag.getName());
				item.setPhone(tag.getPhone());
				item.setPhone2(tag.getPhone2());
				item.setPic(tag.getPic());
				items.add(item);
			}
			model.setList(items);
		}
		return model;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	}

}
