package com.test.demo.async;

import static org.mockito.Matchers.intThat;

/**
 * 事件类型
 * @author ASUS
 * 创建时间  2017年9月16日 下午3:50:18
 *
 */
public enum EventType {
	/**
	 * 喜欢事件
	 */
	LIKE(0),

	/**
	 * 评论
	 */
	COMMENT(1),
	/**
	 * 登陆
	 */
	LOGIN(2),
	
	/**
	 * 发邮件
	 */
	MAIL(3);
	
	private int value;
	
	public  int getValue() {
		return value;
	}
	
	EventType(int value) {
	        this.value = value;
	}
}
