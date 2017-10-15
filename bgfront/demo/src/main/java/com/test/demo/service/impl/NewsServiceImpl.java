package com.test.demo.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.bean.NewsBean;
import com.test.demo.dao.NewsDao;
import com.test.demo.service.NewsService;

/**
 * service
 * @author ASUS
 * 创建时间  2017年9月10日 下午5:48:48
 *
 */
@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDao newsDAO;
	
	@Override
	public int addNews(NewsBean news) {
		return newsDAO.addNews(news);
	}

	@Override
	public NewsBean selectById(int id) {
		return  newsDAO.selectById(id);
	}

	@Override
	public List<NewsBean> selectByUserIdAndOffset(int userId, int offset, int limit) {
		return newsDAO.selectByUserIdAndOffset(userId, offset, limit);
	}

	@Override
	public int updateCommentCount(int id, int commentCount) {
		return  newsDAO.updateCommentCount(id, commentCount);
	}

	@Override
	public int updateLikeCount(int id, int likeCount) {
		return newsDAO.updateLikeCount(id, likeCount);
	}

	
}
