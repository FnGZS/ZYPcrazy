 package com.crazyBird.controller.user;
 
 import com.crazyBird.controller.user.model.BindingModel;
 import com.crazyBird.controller.user.model.LoginModel;
 import com.crazyBird.controller.user.param.BindingParam;
 import com.crazyBird.controller.user.param.LoginParam;
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
   
   @RequestMapping(value={"/login"}, method = RequestMethod.POST)
   @ResponseBody
   public LoginModel userLogin(@RequestBody LoginParam param){
	     return this.userLoginProcess.doLogin(param);
   }
   
   @RequestMapping(value={"/binding"}, method = RequestMethod.POST)
   @ResponseBody
   public BindingModel userBinding(@RequestBody BindingParam param) {
	     return this.userLoginProcess.binding(param);
   }
 }

