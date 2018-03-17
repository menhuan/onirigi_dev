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
	public static final int  MINPAGE=0;
	
	/**
	 * 分页大
	 */
	public static final int MAXPAGE=10;

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
	
	
	/**************************************秘钥***********************************************/
	
	/**
	 * 华鸿家园 ip 访问秘钥  
	 */
	public static final String ACCESS_KEY_ID =  "5281dcd6-cd6cedad-140aec98-715b4" ;
	
	/**
	 * 华鸿家园 ip  签名秘钥
	 */
	public static final String SECRET_KEY = "207afff0-d2e19905-46be0423-87a4f";
	
	/**
	 * 大米云 ip 访问秘钥
	 */
//	public static final String accessKeyId = "dc6b7ed8-0fe5acf8-89e93aca-2b31a";
	
	/**
	 * 大米云 ip 签名秘钥
	 */
//	public static final String secretKey  = "a1a43494-d69212d6-f9deda6b-f99dc" ;
	
	
	
}
