 package com.crazyBird.controller.user;

import com.crazyBird.controller.user.model.BackgroundModel;
/**
 * 登录
 * @author zjw
 *
 */
 import com.crazyBird.controller.user.model.BindingModel;
 import com.crazyBird.controller.user.model.LoginModel;
import com.crazyBird.controller.user.model.MessageModel;
import com.crazyBird.controller.user.param.BindParam;
import com.crazyBird.controller.user.param.BindingParam;
 import com.crazyBird.controller.user.param.LoginParam;
import com.crazyBird.controller.user.param.MessageParam;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

 
 @Controller
 @RequestMapping({"/user"})
 public class UserLoginController
 {
   @Autowired
   private UserLoginProcess userLoginProcess;
   
   /**
    * 微信第三方登录
    * */
   @RequestMapping(value={"/login"}, method = RequestMethod.POST)
   @ResponseBody
   public LoginModel userLogin(@RequestBody LoginParam param){
	     return this.userLoginProcess.doLogin(param);
   }
   /**
    * 绑定学号，电话
    * */
   @RequestMapping(value={"/binding"}, method = RequestMethod.POST)
   @ResponseBody
   public BindingModel userBinding(@RequestBody BindingParam param) {
	     return this.userLoginProcess.binding(param);
   }
   /**
    * 发送短信
    * */
   @RequestMapping(value={"/sms"}, method = RequestMethod.POST)
   @ResponseBody
   public MessageModel smsget(@RequestBody MessageParam param) {
	     return this.userLoginProcess.smsget(param);
   }
   
   /**
    * 无法绑定的绑定
    * */
   @RequestMapping(value={"/cantBind"}, method = RequestMethod.POST)
   @ResponseBody
   public BindingModel bind(@RequestBody BindParam param) {
	   return userLoginProcess.bind(param);
   }
   
   /**
    * 用户背景图
    * */
   @RequestMapping(value={"/background"}, method = RequestMethod.GET)
   @ResponseBody
   public BackgroundModel bind() {
	   return userLoginProcess.getBackgroud();
   }
   
 }

