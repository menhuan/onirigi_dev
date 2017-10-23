package com.test.demo.redis;

/**
 * redis 的工具类
 * @author ASUS
 * 创建时间  2017年9月12日 下午10:41:30
 *
 */
public class RedisKeyUtil {
	
	/**
	 * 36kr 首页里面符合正则的html页面
	 */
	public static final String REDIS_LIST_SUCCESS_HTML_KEY = "redis_list_success_kr_html_key";
	
	/**
	 * 36kr 首页里面符合正则的html页面 入库失败后的内容插入到对应的队列中去
	 */
	public static final String REDIS_LIST_FAILED_HTML_KEY = "redis_list_success_kr_html_key";
	
	
	/**
	 * 分隔符
	 */
	private static String SPLIT=":";
	
	/**
	 * like 这个事件
	 */
	private static String BIZ_LIKE="LIKE";
	
	/**
	 * 不喜欢
	 */
	private static String BIZ_DISLIKE="DISLIKE";
	
	/**
	 * 事件
	 */
	private static String BIZ_EVENT="EVENT";
	
	
	
	/**
	 * 返回事件类型
	 * @author ASUS
	 * 创建时间  2017年9月12日 下午10:49:12
	 * @return
	 */
	public static String getEventQueueKey() {
		return BIZ_EVENT;
	}
	
	/**
	 * 返回like的key
	 * @author ASUS
	 * 创建时间  2017年9月12日 下午10:49:57
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	public static String getLikeKey(int entityId ,int entityType) {
		return BIZ_LIKE+SPLIT+String.valueOf(entityType)+SPLIT+String.valueOf(entityId);
	}
	
	/**
	 * 得到不喜欢的key
	 * @author ASUS
	 * 创建时间  2017年9月12日 下午10:51:46
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	public static String getDisLikeKey(int entityId ,int entityType) {
		return BIZ_DISLIKE+SPLIT+String.valueOf(entityType)+SPLIT+String.valueOf(entityId);
	}
}
