package com.test.demo.base;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

/**
 * 各个异常信息处理统一code 与消息内容
 * 
 * @author ASUS 创建时间 2017年8月26日 下午2:02:02
 *
 */
public enum BaseCode {
	
	ERRORCODE0001("0001","错误code不存在","错误code不存在"),
	
	/**************************************** 用户名与密码**************************************/
	/** 登录用户名 为空 **/
	USERNAME1001("1001", "登录用户名 为空","登录用户名 为空"),
	/** 登录名已经存在 **/
	USERNAME1002("1002", "登录名已经存在","登录名已经存在"),
	/** 用户名不存在 **/
	USERNAME1003("1003", "登录名不存在","登录名不存在"),
	/** 密码不能为空 **/
	USERPWD2001("2001", "密码不能为空","密码不能为空"),
	/** 密码不正确 **/
	USERPWD2002("2002", "密码不正确","密码不正确"),
	/** 密码 长度不符合 **/
	USERPWD2003("2003", "密码 长度不符合", "密码 长度不符合");

	String code = "";

	String msg = "";
	
	String msgType="";

	private BaseCode(String code, String msg,String msgType) {
		this.code = code;
		this.msg = msg;
		this.msgType=msgType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	private static Map<String,BaseCode>  enumMap =new HashMap<String, BaseCode>();
	
	static {
		EnumSet<BaseCode> enumSet =EnumSet.allOf(BaseCode.class);
		for(BaseCode code:enumSet) {
			enumMap.put(code.getCode(), code);
		}
		
	}
	
	/**
	 *根据code得到枚举内容
	 * @author ASUS
	 * 创建时间  2017年8月26日 下午6:02:49
	 * @param code
	 * @return
	 */
	public static BaseCode  getReturnCodeMsg(String code) {
		if(enumMap.containsKey(code)) {
			return enumMap.get(code);
		}else {
			LoggerFactory.getLogger(BaseCode.class).error(new StringBuffer("需要的code不存在:").append(code).toString());
			return BaseCode.ERRORCODE0001;
		}
	}
	
	
	@Override
	public String toString() {
		return new StringBuffer("{code:").append(code).append(";msg:").append(msg)
				.append(";msgType:").append(msgType).append("}").toString();
	}
}
