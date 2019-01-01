package com.crazyBird.controller.curriculum;

import java.io.IOException;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crazyBird.controller.base.SimpleFlagModel;



@Component
public class CurriculumProcess {
	
	//HttpDAO  httpDao;
	
	public SimpleFlagModel getCurriculum() {
		SimpleFlagModel model = new SimpleFlagModel();
	 /*	//httpDao=new HttpService();
		httpDao.init();
		httpDao.getCheckImg();
		//System.out.println(httpDao.getCheckImg());*/
		return model;
		
	
		
	}
	
}
