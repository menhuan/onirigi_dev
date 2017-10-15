package com.test.demo.bean;

import java.util.Date;

/**
 * 评论实体bean
 * @author ASUS
 * 创建时间  2017年9月8日 下午8:29:44
 *
 */
public class CommentBean {
	/**
	 * 主键id
	 */
	private int id;
	
	/**
	 * 用户id
	 */
	private int userId;
	
	/**
	 * 实体id
	 */
	private int entityId;
	
	/**
	 * 实体类型
	 */
	private int entityType;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 创建日期
	 */
	private Date createdDate;

	/**
	 * 状态
	 */
	private int status;

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

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public int getEntityType() {
		return entityType;
	}

	public void setEntityType(int entityType) {
		this.entityType = entityType;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
}
