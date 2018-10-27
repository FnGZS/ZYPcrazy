 package com.crazyBird.interceptor;
 
 import com.crazyBird.dao.user.dataobject.UserLoginDO;
 import com.crazyBird.exception.CertificateException;
 import com.crazyBird.model.constant.UrlRightConstant;
 import com.crazyBird.model.reqinfo.ReqHead;
 import com.crazyBird.model.reqinfo.ReqParam;
 import com.crazyBird.service.base.ResponseDO;
 import com.crazyBird.service.user.UserLoginService;
 import com.crazyBird.utils.IPUtils;
 import com.crazyBird.utils.TokenUtils;
 import eu.bitwalker.useragentutils.Browser;
 import eu.bitwalker.useragentutils.OperatingSystem;
 import eu.bitwalker.useragentutils.UserAgent;
 import java.util.Date;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.servlet.HandlerInterceptor;
 import org.springframework.web.servlet.ModelAndView;
 
 
 
 
 public class MvcTokenInterceptor
   implements HandlerInterceptor
 {
  private final String ACCESS_TOKEN = "authorization";
   private final String APP_VERSION = "app-version";
   private final String OS = "os";
   private final String OS_VERSION = "os-version";
   @Autowired
   private UserLoginService userLoginService;
   
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg)
     throws Exception
   {
     request.setAttribute("req_time", new Date());
     
     String url = request.getRequestURI();
     
     ReqHead reqHead = new ReqHead();
     reqHead.setAccessToken(request.getHeader("authorization"));
     if (StringUtils.isNotBlank(request.getHeader("app-version"))) {
       reqHead.setVersion(request.getHeader("app-version"));
       reqHead.setOs(request.getHeader("os") + "_APP");
       reqHead.setOsVersion(request.getHeader("os-version"));
     } else {
       UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
       reqHead.setBrowser(userAgent.getBrowser().getName());
       reqHead.setOs(userAgent.getOperatingSystem().getName());
     }
     
     ReqParam reqParam = new ReqParam();
     reqParam.setReqHead(reqHead);
     reqParam.setIp(IPUtils.getIp(request));
     reqParam.setUrl(url);
    request.setAttribute("ReqParam", reqParam);
     
 
     for (String ignoreLoginUrl : UrlRightConstant.notVerifyUrlList) {
      if (url.indexOf(ignoreLoginUrl) != -1) {
         return true;
       }
     }
     
 
    checkAccessToken(reqParam);
     return true;
   }
   
 
 
 
 
   private void checkAccessToken(ReqParam reqParam)
     throws Exception
   {
     String url = reqParam.getUrl().replaceAll("\\d+", "");
     String accessToken = reqParam.getReqHead().getAccessToken();
    if (StringUtils.isBlank(accessToken))
     {
       for (String loginNotNecessaryUrl : UrlRightConstant.loginNotNecessaryUrlList) {
        if (url.indexOf(loginNotNecessaryUrl) != -1) {
          return;
         }
       }
       
       for (String secureUrl : UrlRightConstant.loginVerifyUrlList) {
         if (url.indexOf(secureUrl) != -1) {
          throw new CertificateException("请先登录");
         }
       }
     }
     else
     {
       try
       {
         Long shoolNum = TokenUtils.getIdFromAesStr(accessToken);
        if (shoolNum == null) {
          throw new CertificateException("登录信息无效！请重新登录");
         }
        Object responeDO = this.userLoginService.getUserLogin(shoolNum);
         
         if ((!((ResponseDO)responeDO).isSuccess()) || (((ResponseDO)responeDO).getDataResult() == null)) {
          throw new CertificateException("登录信息无效！请重新登录");
         }
         UserLoginDO userLoginDO = (UserLoginDO)((ResponseDO)responeDO).getDataResult();
         if (userLoginDO.getIsBound().intValue() == 2) {
          throw new CertificateException("无权限，请绑定");
         }
         reqParam.setLoginAccount(userLoginDO.getLoginAccount());
         return;
       } catch (Exception e) {
        throw new CertificateException("登录信息无效！请重新登录");
       }
     }
   }
   
   public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
     throws Exception
   {}
   
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView model)
     throws Exception
   {}
 }


