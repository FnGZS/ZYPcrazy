package com.crazyBird.controller.help;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.help.model.HelpItem;
import com.crazyBird.controller.help.model.HelpModel;
import com.crazyBird.dao.help.dataobject.HelpDo;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.help.HelpService;
import com.crazyBird.utils.CollectionUtil;

@Component
public class HelpProcess{
	@Autowired
	private HelpService helpService;
	public HelpModel getHelp() {
		HelpModel model = new HelpModel();
		List<HelpDo> helps = helpService.getHelp();
		if(CollectionUtil.isNotEmpty(helps)) {
			List<HelpItem> items = new ArrayList<HelpItem>();
			for(HelpDo help : helps) {
				HelpItem item = new HelpItem();
				item.setTitle(help.getTitle());
				item.setContent(help.getContent());
				items.add(item);
			}
			model.setItems(items);
		}
		else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("无此项");
		}
		return model;
	}
}
