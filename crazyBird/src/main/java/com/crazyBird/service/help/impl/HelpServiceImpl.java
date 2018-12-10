package com.crazyBird.service.help.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.help.HelpDao;
import com.crazyBird.dao.help.dataobject.HelpDo;
import com.crazyBird.service.help.HelpService;
@Component("HelpService")
public class HelpServiceImpl implements HelpService{
	@Autowired
	private HelpDao helpDao;
	@Override
	public List<HelpDo> getHelp() {
		// TODO Auto-generated method stub
		return helpDao.getHelp();
	}
	
}
