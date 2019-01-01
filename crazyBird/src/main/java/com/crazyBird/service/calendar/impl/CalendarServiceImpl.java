package com.crazyBird.service.calendar.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.calendar.CalendarDao;
import com.crazyBird.service.calendar.CalendarService;

@Component("CalendarService")
public class CalendarServiceImpl implements CalendarService{
	@Autowired CalendarDao calendarDao;

	@Override
	public String getCanlendarUrl() {
		// TODO Auto-generated method stub
		return calendarDao.getCalendarUrl();
	}
	
}
