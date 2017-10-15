package com.test.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.test.demo.bean.NewsBean;

/**
 * 新闻的service
 * @author ASUS
 * 创建时间  2017年9月10日 下午5:44:19
 *
 */
public interface NewsService {

	
		/**
		 * 增加新闻	
		 * @author ASUS
		 * 创建时间  2017年9月10日 下午5:45:00
		 * @param news
		 * @return
		 */
		int addNews(NewsBean news);

		/**
		 * 根据id 查询
		 * @author ASUS
		 * 创建时间  2017年9月10日 下午5:45:09
		 * @param id
		 * @return
		 */
	    NewsBean selectById(int id);

	    
	    /**
	     * 查询列表
	     * @author ASUS
	     * 创建时间  2017年9月10日 下午5:45:20
	     * @param userId
	     * @param offset
	     * @param limit
	     * @return
	     */
	    List<NewsBean> selectByUserIdAndOffset(@Param("userId") int userId, @Param("offset") int offset,
	                                       @Param("limit") int limit);
	    /**
	     * 更新评论数量
	     * @author ASUS
	     * 创建时间  2017年9月10日 下午5:47:05
	     * @param id
	     * @param commentCount
	     * @return
	     */
	    int updateCommentCount(@Param("id") int id, @Param("commentCount") int commentCount);
	    
	    
	    /**
	     * 更新喜欢的数量
	     * @author ASUS
	     * 创建时间  2017年9月26日 下午9:50:47
	     * @param id
	     * @param likeCount
	     * @return
	     */
	    int updateLikeCount(@Param("id") int id, @Param("likeCount") int likeCount);
	    
}
