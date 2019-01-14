package com.crazyBird.controller.user;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.user.model.UserPayModel;
import com.crazyBird.controller.user.param.UserAgainPayParam;
import com.crazyBird.controller.user.param.UserRefundParam;
import com.crazyBird.service.user.dataobject.RefundInfo;
import com.crazyBird.utils.XmlToMapUtils;

@Controller
@RequestMapping("/pay")
public class UserPayController {
	@Autowired
	private UserPayProcess payProcess;
	/**
	 * 重新支付订单
	 * @param param
	 * @return
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value="/recharge", method = RequestMethod.POST)
	@ResponseBody
	public UserPayModel userPay(@RequestBody UserAgainPayParam param) throws IllegalAccessException {
		return payProcess.userPay(param);
	}
	/**
	 *二手退款
	 */
	@RequestMapping(value="/refund", method = RequestMethod.POST)
	@ResponseBody
	public SimpleFlagModel secondaryRefund(@RequestBody UserRefundParam param) {
		
		return payProcess.secondaryRefund(param);
	}
	/**
	 * 二手支付回调
	 * @throws IOException 
	 */
	@RequestMapping(value="/wxNotify")
	public void wxNotify(HttpServletRequest request,HttpServletResponse response) throws IOException {
		/*double cost = Double.parseDouble("122");
		double bei = 100.00;
		double fee = ArithUtils.mul(cost, bei);

		int fee3 = (int)fee;
		System.out.println(fee3);*/
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        Map<String, Object> resultMap = XmlToMapUtils.getResult(notityXml);
        String returnCode=(String) resultMap.get("return_code");
        System.out.println(returnCode);
        if(returnCode.equals("SUCCESS")) {
    	    //校验签名
        	Map<String, Object> checkMap= new HashMap<>();
  	
        	//	/变更订单状态 业务逻辑 保存微信支付记录
        	
        	boolean flag = payProcess.wxNotify(resultMap);
        	if(flag) {
        	resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
        	}
        	else {
        		resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			}
        	

        }
        else {
        	resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
		}
        

        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
        
	}
	/**
	 * 直播支付回调
	 * @throws IOException 
	 */
	@RequestMapping(value="/wxNotify/gift")
	public void wxNotifyGift(HttpServletRequest request,HttpServletResponse response) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        br.close();
        String notityXml = sb.toString();
        String resXml = "";
        Map<String, Object> resultMap = XmlToMapUtils.getResult(notityXml);
        String returnCode=(String) resultMap.get("return_code");
        
        if(returnCode.equals("SUCCESS")) {
    	    //校验签名
        	Map<String, Object> checkMap= new HashMap<>();
        	
           
        	
        	
        	//	/变更订单状态 业务逻辑 保存微信支付记录
        	
        	boolean flag = payProcess.wxNotifyGift(resultMap);
        	System.out.println("aa");
        	if(flag) {
        	resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
        	}
        	else {
        		resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			}
        }
        else {
        	resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
		}
        
        System.out.println("接收到的报文：" + notityXml);
        System.out.println(resXml);
        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
       
	}
	
}
