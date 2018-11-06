package com.crazyBird.controller.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.calendar.model.CalendarModel;
import com.crazyBird.service.calendar.CalendarService;
@Component
public class CalendarProcess {
	@Autowired
	private CalendarService calendarService;
	public CalendarModel getCanlendarUrl() {
		
		CalendarModel model = new CalendarModel();
		model.setCalendar(calendarService.getCanlendarUrl());
		return model;
		
	}
	
}
