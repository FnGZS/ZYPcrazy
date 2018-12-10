package com.crazyBird.controller.help;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.help.model.HelpModel;



@Controller
@RequestMapping("/help")
public class HelpController {
	@Autowired
	private HelpProcess helpProcess;
	@RequestMapping(value = "/gethelp",method = RequestMethod.GET)
	@ResponseBody
	public HelpModel getHelp() {
		return helpProcess.getHelp();
	}
}
