package com.test.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.bean.NewsBean;
import com.test.demo.dao.NewsDao;
import com.test.demo.service.ToutiaoService;

/**
 * 头条service
 * @author ASUS
 * 创建时间  2017年8月21日 下午10:03:20
 *
 */
@Service
public class ToutiaoServiceImpl implements ToutiaoService{

	@Autowired
	private  NewsDao  newsDao;

	
	@Override
	public String say() {
		return " This is from ToutiaoService";
	}

	
	@Override
	public List<NewsBean> getLateNewsBeans(int userId, int offset, int limit) {
		return newsDao.selectByUserIdAndOffset(userId, offset, limit);
	}

	
}
