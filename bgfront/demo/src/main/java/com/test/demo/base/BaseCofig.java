package com.test.demo.base;

import java.util.Random;

/**
 * 公共的配置
 * 
 * @author dell
 *
 */
public class BaseCofig {

	/**
	 * 查询彩票的组数
	 */
	public static final Integer LOTTER_GROUP_NUM = 10;

	/** 用户生成随机密码的开始位置 */
	public static final Integer USER_SALT_START = 0;

	/** 用户生成随机密码结束的位置 **/
	public static final Integer USER_SALT_END = 5;

	/** 随机生成用户的图片 */
	public static final String USER_HEAD_URL = String.format("http://images.nowcoder.com/head/%dt.png",
			new Random().nextInt(1000));

	/** session过期短时间设置 **/
	public static final Long SESSION_EXPIRED_SHORT_LONG = (long) (1000 * 3600 * 24);

	/** 登录保存有效 */
	public static final Integer LOGIN_TICKEN_TRUE_STATUS = 0;

	/** 登录保存无效 */
	public static final Integer LOGIN_TICKEN_FALSE_STATUS = 1;

	/**api接口返回的成功编码*/
	public static final String YT_SUCCESS_CODE="200";
	
	/**
	 * 分页小
	 */
	public static final int  minPage=0;
	
	/**
	 * 分页大
	 */
	public static final int maxPage=10;

	/**
	 * 实体类型
	 */
	public static final int ENTITY_NEWS =1;
	
}
