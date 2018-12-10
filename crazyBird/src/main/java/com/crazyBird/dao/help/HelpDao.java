package com.crazyBird.dao.help;

import java.util.List;

import com.crazyBird.dao.help.dataobject.HelpDo;

public abstract interface HelpDao {
	List<HelpDo> getHelp();
}
