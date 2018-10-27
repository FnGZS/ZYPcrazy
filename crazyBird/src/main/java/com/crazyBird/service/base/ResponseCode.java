package com.crazyBird.service.base;

/**
 * @Type ResponseCode
 * @Desc 返回代码定义
 * @author luogm
 * @date 2014年2月11日
 * @Version V1.0
 */
public class ResponseCode {

    /**
     * 返回code 0：成功
     */
    public static final String SUCCESS = "0";

    /**
     * 返回code 2：业务错误
     */
    public static final String ERROR = "-1";

    /**
     * 判断是否成功
     * 
     * @param code
     * @return
     */
    public static boolean isSuccess(String code) {
        return SUCCESS.equals(code);
    }
}
