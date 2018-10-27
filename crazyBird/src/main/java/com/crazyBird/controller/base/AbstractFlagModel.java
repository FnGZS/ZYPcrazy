package com.crazyBird.controller.base;

/**
 * @author
 *
 */
public abstract class AbstractFlagModel {
	
    public static String SUCCESS = "200";
    
    /**
     * 系统接口状�?�编�??,包括权限,异常等异常提�??
     */
    private String code = SUCCESS;

    /**
     * 输出信息
     */
    private String message = "";
    
    /**
     * 成功标识
     * 
     * @return
     */
    public boolean successCode() {
    	return SUCCESS.equals(getCode());
    }
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

