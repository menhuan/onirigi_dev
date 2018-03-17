package com.test.demo.bean.common;

/**
 * 短信bean
 * @author ASUS
 * 创建时间  2018年3月10日 下午9:09:10
 *
 */
public class SmsBean {

	
	/**
	 * 发送的手机号
	 */
	private String phone ;
	
	/**
	 * 短信模板code
	 */
	private String  templateCode;

	/**
	 * 短信签名 
	 */
	private String signName ;
	
	/**
	 * 短信发送的内容 模板替代内容"{\"name\":\"Tom\", \"code\":\"123\"}"
	 */
	private String content;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
