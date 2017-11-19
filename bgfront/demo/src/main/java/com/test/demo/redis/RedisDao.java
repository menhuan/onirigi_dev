package com.test.demo.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;


@Repository
public class RedisDao {

	 	
	@Autowired
	RedisTemplate<String,String> redisTemplate;
	
	
	/**
	 * 根据key 得到值  
	 * @author ASUS
	 * 创建时间  2017年9月12日 下午9:30:00
	 * @param key
	 * @return
	 */
	public String get (String key) {
		 return redisTemplate.opsForValue().get(key) ;
	}
	
	/**
	 * 给对应的key 设置值
	 * @author ASUS
	 * 创建时间  2017年9月12日 下午9:41:07
	 * @param key
	 * @param value
	 */
	public void set(String key,String value ) {
		redisTemplate.opsForValue().set(key, value);
	}
	
	/**
	 * set集合数据设置值
	 * @author ASUS
	 * 创建时间  2017年9月12日 下午9:43:39
	 * @param key
	 * @param value
	 * @return
	 */
	public long sadd(String key,String values) {
		return redisTemplate.opsForSet().add(key, values);
	}
	
	/**
	 * 移除指定set中的数据  返回移除的条数
	 * @author ASUS
	 * 创建时间  2017年9月12日 下午9:47:12
	 * @param key
	 * @param value
	 * @return
	 */
	public long srem(String key ,String value) {
		return redisTemplate.opsForSet().remove(key, value);
	}
	
	/**
	 * 判断 key 是否存在
	 * @author ASUS
	 * 创建时间  2017年9月12日 下午9:49:34
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean sismember(String key,String value) {
		return redisTemplate.opsForSet().isMember(key, value);
	}
	
	/**
	 * 根据key 从set中拿出来集合  检查是否存在的 
	 * @author ASUS
	 * 创建时间  2017年10月20日 下午10:06:13
	 * @param key
	 */
	public void  smember(String key) {
		redisTemplate.opsForSet().members(key);
	}
	
	/**
	 * 返回集合总数
	 * @author ASUS
	 * 创建时间  2017年9月12日 下午9:54:43
	 * @param key
	 * @return
	 */
	public long scard(String key) {
		return redisTemplate.opsForSet().size(key);
	}
	
	
	/**
	 * 设置过期时间
	 * @author ASUS
	 * 创建时间  2017年9月12日 下午10:04:28
	 * @param key
	 * @param value
	 */
	public void setex(String key,String value) {
		TimeUnit  unit =TimeUnit.SECONDS;
		redisTemplate.expire(key, 10,unit );
	}
	
	/**
	 * 从左侧插入到list队列中
	 * @author ASUS
	 * 创建时间  2017年9月12日 下午10:23:57
	 * @param key
	 * @param value
	 * @return
	 */
	public long lpush(String key,String value) {
		return redisTemplate.opsForList().leftPush(key, value);
	}
	
	
	/**
	 * 向redis中插入集合数据
	 * @author ASUS
	 * 创建时间  2017年11月19日 下午4:40:16
	 * @param key
	 * @param values
	 * @return
	 */
	public long lpushList(String key,List<String> values) {
		return redisTemplate.opsForList().rightPushAll(key, values);
	}
	
	/**
	 * 从队列中取出数据
	 * @author ASUS
	 * 创建时间  2017年9月12日 下午10:30:58
	 * @param timeOut
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String brpop(String key){
		return redisTemplate.opsForList().rightPop(key);
	}
	
	/**
	 * 将list 中的数据都拿出来
	 * @author ASUS
	 * 创建时间  2017年10月23日 下午9:30:50
	 * @param key
	 * @return
	 */
	public List<String> brpopList(String key){
		Long end =redisTemplate.opsForList().size(key);
		return redisTemplate.opsForList().range(key, 0, end);
	}
	
	/**
	 * 取出再放进去
	 * @author ASUS
	 * 创建时间  2017年9月27日 下午9:58:32
	 * @param key
	 * @param value
	 * @return
	 */

	
	/**
	 * 将数据转换为json保存到redis中
	 * @author ASUS
	 * 创建时间  2017年9月12日 下午10:34:47
	 * @param key
	 * @param obj
	 */
	public void setObject(String key,Object obj) {
		set(key, JSON.toJSONString(obj));
	}
	
	/**
	 * 按照对应的class 样式转换bean
	 * @author ASUS
	 * 创建时间  2017年9月12日 下午10:36:33
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T>T getObject(String key,Class<T> clazz){
		String value=get(key);
		if(value!=null) {
			return JSON.parseObject(value, clazz);
		}
		return null;
	}
	
	
	
}
