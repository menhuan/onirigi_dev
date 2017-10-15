package com.test.demo.bean;

import java.util.Date;

/**
 * 登录记录bean
 * 
 * @author ASUS 创建时间 2017年8月26日 下午12:49:00
 *
 */
public class LoginTicketBean {

	private int id;

	private int userId;

	/** 过期时间 **/
	private Date expireDate;

	/** 0 有效 1 无效 */
	private int status;

	/** 登录的ticket */
	private String ticket;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
