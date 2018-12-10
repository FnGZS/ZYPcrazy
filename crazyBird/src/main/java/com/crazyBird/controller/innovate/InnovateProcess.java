package com.crazyBird.controller.innovate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crazyBird.controller.innovate.model.InnovateEnterpriseItem;
import com.crazyBird.controller.innovate.model.InnovateEnterpriseListModel;
import com.crazyBird.controller.innovate.model.InnovateEnterpriseModel;
import com.crazyBird.dao.innovate.dataobject.InnovateEnterpriseDO;
import com.crazyBird.dao.innovate.dataobject.InnovateEnterpriseListDO;
import com.crazyBird.service.innovate.InnovateService;

@Component
public class InnovateProcess {
	@Autowired 
	private InnovateService innovateService;
	
	public InnovateEnterpriseListModel getInnovateEnterpriseList() {
		InnovateEnterpriseListModel model = new InnovateEnterpriseListModel();
		List<InnovateEnterpriseItem> items = new ArrayList<>();
		List<InnovateEnterpriseListDO> tags = innovateService.getInnovateEnterpriseList();
		for (InnovateEnterpriseListDO tag : tags) {
			InnovateEnterpriseItem item = new InnovateEnterpriseItem();
			item.setId(tag.getId());
			item.setLogo(tag.getLogo());
			items.add(item);
		}
		model.setItems(items);
		return model;
	}
	public InnovateEnterpriseModel getInnovateEnterprise(Integer id) {

		InnovateEnterpriseModel model = new InnovateEnterpriseModel();
		InnovateEnterpriseDO innovateDO = innovateService.getInnovateEnterprise(id);
		model.setId(innovateDO.getId());
		model.setLogo(innovateDO.getLogo());
		model.setName(innovateDO.getName());
		model.setContent(innovateDO.getContent());
		return model;
	}

}
