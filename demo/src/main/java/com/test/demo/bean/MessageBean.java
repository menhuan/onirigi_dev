package com.test.demo.bean;

import java.util.Date;

/**
 * messageBean
 * @author ASUS
 * 创建时间  2017年9月8日 下午8:36:19
 *
 */
public class MessageBean {

	/**
	 * 主键id
	 */
	private int id;
	
	/**
	 * 来自哪个用户
	 */
	private int fromId;

	/**
	 * 发给谁
	 */
	private int toId;

	/**
	 * 评论内容
	 */
	private String content;

	/**
	 * 创建日期
	 */
	private Date createdDate;
	
	/**
	 * 是否阅读  
	 */
	private int hasRead;
	
	/**
	 * 
	 */
	private String conversationId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getHasRead() {
		return hasRead;
	}

	public void setHasRead(int hasRead) {
		this.hasRead = hasRead;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}
	
}
