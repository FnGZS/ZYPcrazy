 package com.crazyBird.controller.user;

import com.crazyBird.controller.user.model.BackgroundModel;
/**
 * 登录
 * @author zjw
 *
 */
 import com.crazyBird.controller.user.model.BindingModel;
import com.crazyBird.controller.user.model.GetPhoneModel;
import com.crazyBird.controller.user.model.LoginModel;
import com.crazyBird.controller.user.model.MessageModel;
import com.crazyBird.controller.user.param.BindParam;
import com.crazyBird.controller.user.param.BindingParam;
 import com.crazyBird.controller.user.param.LoginParam;
import com.crazyBird.controller.user.param.MessageParam;
import com.crazyBird.service.weixin.WeixinAppService;
import com.lowagie.text.pdf.codec.Base64;

import javax.servlet.http.HttpServletRequest;

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
   
   /**
    * 解密并且获取用户手机号码
	* @param encrypdata
	* @param ivdata
	* @param sessionkey
	* @param request
	* @return
	* @throws Exception 
    * */
   @RequestMapping(value = "/deciphering", method = RequestMethod.GET)
   @ResponseBody
	public GetPhoneModel deciphering(String encrypdata,String ivdata, String sessionkey,HttpServletRequest request) {
		byte[] encrypData = Base64.decode(encrypdata);
		byte[] ivData = Base64.decode(ivdata);
		byte[] sessionKey = Base64.decode(sessionkey);
		String str="";
		try {
			str = WeixinAppService.decrypt(sessionKey,ivData,encrypData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userLoginProcess.getPhone(str);
   	}
   
 
 }