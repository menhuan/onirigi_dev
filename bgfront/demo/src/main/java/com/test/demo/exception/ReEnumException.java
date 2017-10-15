package com.test.demo.exception;

import com.test.demo.base.BaseCode;

/**
 * 自定义枚举返回异常
 * @author ASUS
 * 创建时间  2017年8月26日 下午5:46:25
 *
 */
public class ReEnumException extends Exception {

	/**序列化**/
	private static final long serialVersionUID = -448984715745533135L;
	
	private BaseCode  code;

	public BaseCode getCode() {
		return code;
	}

	public void setCode(BaseCode code) {
		this.code = code;
	}

	/**
	 * 构造器 得到需要的信息
	 * @param code
	 */
	public ReEnumException (String code ) {
		super(new StringBuffer("调用信息异常:").append(BaseCode.getReturnCodeMsg(code)).toString());
		this.code=BaseCode.getReturnCodeMsg(code);
	}
	
}
