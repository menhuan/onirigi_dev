package com.test.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.bean.CommentBean;
import com.test.demo.dao.CommentDao;
import com.test.demo.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao  commentDao;
	
	@Override
	public int addComment(CommentBean bean) {
		return commentDao.addComment(bean);
	}

	@Override
	public List<CommentBean> selectByEntity(int entityId, int entityType) {
		// TODO Auto-generated method stub
		return commentDao.selectByEntity(entityId, entityType);
	}

	@Override
	public int getCommentCount(int entityId, int entityType) {
		return commentDao.getCommentCount(entityId, entityType);
	}

}
