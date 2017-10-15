package com.test.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.bean.MessageBean;
import com.test.demo.dao.MessageDao;
import com.test.demo.service.MessageService;

/**
 * 接口实现
 * @author ASUS
 * 创建时间  2017年9月8日 下午9:29:40
 *
 */
@Service
public class MessageServiceImpl implements MessageService {

	
	@Autowired
	private MessageDao  messageDao;
	
	@Override
	public int addMessage(MessageBean bean) {
		return messageDao.addMessage(bean);
	}

	@Override
	public List<MessageBean> getConversationbList(int userId, int offset, int limit) {
		return messageDao.getConversationbList(userId, offset, limit);
	}

	@Override
	public int getConversationUnReadCount(int userId, String conversationId) {
		// TODO Auto-generated method stub
		return messageDao.getConversationUnReadCount(userId, conversationId);
	}

	@Override
	public int getConversationTotalCount(int userId, String conversationId) {
		return messageDao.getConversationTotalCount(userId, conversationId);
	}

	@Override
	public List<MessageBean> getConversationDetail(String conversationId, int offset, int limit) {
		return messageDao.getConversationDetail(conversationId, offset, limit);
	}

}
