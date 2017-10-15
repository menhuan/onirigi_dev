package com.test.demo.service;


/**
 * 喜欢service 
 * @author ASUS
 * 创建时间  2017年9月12日 下午10:57:42
 *
 */
public interface LikeService {

	/**
	 * 得到喜欢的状态
	 * @author ASUS
	 * 创建时间  2017年9月12日 下午10:59:09
	 * @param userId  用户id 
	 * @param entityType  类型
	 * @param entityId 实体id
	 * @return
	 */
	 public int getLikeStatus (int userId,int entityType,int entityId);
	 
	 /**
	  *添加到喜欢的集合中
	  * @author ASUS
	  * 创建时间  2017年9月12日 下午11:00:47
	  * @param userId
	  * @param entityType
	  * @param entityId
	  * @return  喜欢的数量
	  */
	 public long like(int userId,int entityType,int entityId );

	 /**
	  * 不喜欢集合 
	  * @author ASUS
	  * 创建时间  2017年9月12日 下午11:01:32
	  * @param userId
	  * @param entityType
	  * @param entityId
	  * @return 不喜欢的数量
	  */
	 public long disLike(int userId,int entityType,int entityId );
}
