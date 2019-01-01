package com.crazyBird.controller.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.calendar.model.CalendarModel;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
	@Autowired
	private CalendarProcess calendarProcess;
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public CalendarModel getCanlendarUrl() {
		return calendarProcess.getCanlendarUrl();
		
	}
}
