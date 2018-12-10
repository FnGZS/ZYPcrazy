package com.crazyBird.service.help;

import java.util.List;

import com.crazyBird.dao.help.dataobject.HelpDo;

public abstract interface HelpService {
	List<HelpDo> getHelp();
}
