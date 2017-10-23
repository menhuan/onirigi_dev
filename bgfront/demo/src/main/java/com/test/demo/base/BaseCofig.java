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
	
	
	/*****************************************正则匹配规则*********************************************************/
	/**
	 * 解析 首页的数据
	 */
	public static final String PATER_KR_HTML= "http://36kr.com/p/[0-9]+\\.html";
	/**
	 * 从脚本中找到属于公共的部分内容
	 */
	public static final String SCRIPT_KR_HTML = "<script.*?</script>";
	
	/**
	 * 爬虫  首页入口
	 */
	public static final String PATER_HTML_KR_INDEX = "http://36kr.com";
	
	/**
	 * 链接
	 */
	public static final String CONTAIN_KR_COTENT= "http://36kr.com/p/" ;
	
	/**
	 * 匹配里面的数字
	 */
	public static final String CONTAIN_KR_NUM="[0-9]+";
	
	
	
}
