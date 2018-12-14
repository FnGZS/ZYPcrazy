package com.crazyBird.model.constant;

import java.util.ArrayList;
import java.util.List;

public class UrlRightConstant {
    // �??要登�??
    public static List<String> loginVerifyUrlList = new ArrayList<String>();
    // 非必须登�??
    public static List<String> loginNotNecessaryUrlList = new ArrayList<String>();
    // 忽略�??有验�??
    public static List<String> notVerifyUrlList = new ArrayList<String>();
    // 返回结果集数据需要加密的url,暂时没用
    public static List<String> dataSignUrlList = new ArrayList<String>();
    // 线上�??要打印返回结果体的日志的url
    public static List<String> logUrlList = new ArrayList<String>();
    
    static {
    	
    	notVerifyUrlList.add("/user/account/platform/login");
    	notVerifyUrlList.add("/user/binding");
    	notVerifyUrlList.add("/user/login");
    	notVerifyUrlList.add("/user/sms");
    	notVerifyUrlList.add("/user/cantBind");
    	notVerifyUrlList.add("/user/background");
    	notVerifyUrlList.add("/vote");
    	notVerifyUrlList.add("/affaris");
    	notVerifyUrlList.add("/opinion");
    	notVerifyUrlList.add("/lost");
    	notVerifyUrlList.add("/curriculum");
        
        loginNotNecessaryUrlList.add("/user/account/public");
        
        loginVerifyUrlList.add("/user/account");
        loginVerifyUrlList.add("/message");
        loginVerifyUrlList.add("/vote/record");
        loginVerifyUrlList.add("/upload");
        loginVerifyUrlList.add("/lost/lostInput");
        loginVerifyUrlList.add("/lost/lostDelete");
        
        
        dataSignUrlList.add("/");
        
        logUrlList.add("/user/account");
        logUrlList.add("/message");
        logUrlList.add("/upload");
        logUrlList.add("/circle");
        
    }

}