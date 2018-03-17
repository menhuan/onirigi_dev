package com.test.demo.bean.common;

/**
 * 邮件内容bean
 * @author ASUS
 * 创建时间  2018年3月4日 下午10:05:48
 *
 */
public class EmailContentBean {

	/**
	 * 收件人邮箱地址
	 */
	private String toEmail;

	/**
	 * 主题
	 */
	private String subject;
	
	
	/**
	 * 
	 */
	private String content ;


	public String getToEmail() {
		return toEmail;
	}


	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	

}
