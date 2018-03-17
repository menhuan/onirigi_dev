package com.test.demo.bean.common;


/**
 * 通用的邮件bean
 * @author ASUS
 * 创建时间  2018年3月4日 下午9:01:12
 *
 */
public class EmailBean {

	/**
	 * 邮箱账号
	 */
	private String  account ;
	
	/**
	 * 邮箱密码
	 */
	private String  password;

	/**
	 * 失败次数
	 */
	private Integer  failNum;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getFailNum() {
		return failNum;
	}

	public void setFailNum(Integer failNum) {
		this.failNum = failNum;
	}

	
	
	
}
