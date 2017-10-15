package com.test.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.redis.RedisDao;
import com.test.demo.redis.RedisKeyUtil;
import com.test.demo.service.LikeService;

/**
 * 喜欢时间Service
 * @author ASUS
 * 创建时间  2017年9月12日 下午11:02:22
 *
 */
@Service
public class LikeServiceImpl implements LikeService{

	@Autowired
	RedisDao  redisDao;
	
	/**
	 * 得到喜欢的状态
	 * @author ASUS
	 * 创建时间  2017年9月12日 下午10:59:09
	 * @param userId  用户id 
	 * @param entityType  类型
	 * @param entityId 实体id
	 * @return
	 */
	@Override
	public int getLikeStatus(int userId, int entityType, int entityId) {

		 String likeKey=RedisKeyUtil.getLikeKey(entityId, entityType);
		 if(redisDao.sismember(likeKey, String.valueOf(userId))) {
			 return 1;
		 }
		 String disLikeKey=RedisKeyUtil.getDisLikeKey(entityId, entityType);
		 return redisDao.sismember(disLikeKey, String.valueOf(disLikeKey)) ? -1 : 0;
	}

	 /**
	  *添加到喜欢的集合中
	  * @author ASUS
	  * 创建时间  2017年9月12日 下午11:00:47
	  * @param userId
	  * @param entityType
	  * @param entityId
	  * @return  喜欢的数量
	  */
	@Override
	public long like(int userId, int entityType, int entityId) {
		String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
		redisDao.sadd(likeKey, String.valueOf(userId));
		
		String disLikeKey=RedisKeyUtil.getDisLikeKey(entityId, entityType);
		redisDao.srem(disLikeKey, String.valueOf(userId));
		
		return redisDao.scard(likeKey);
	}

	/**
	  * 不喜欢集合 
	  * @author ASUS
	  * 创建时间  2017年9月12日 下午11:01:32
	  * @param userId
	  * @param entityType
	  * @param entityId
	  * @return 不喜欢的数量
	  */
	@Override
	public long disLike(int userId, int entityType, int entityId) {
		String disLikeKey=RedisKeyUtil.getDisLikeKey(entityId, entityType);
		redisDao.sadd(disLikeKey, String.valueOf(userId));
		
		String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
		redisDao.srem(likeKey, String.valueOf(userId));
		
		return redisDao.scard(likeKey);
	}

	
	
}
