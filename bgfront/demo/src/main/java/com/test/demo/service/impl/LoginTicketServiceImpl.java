package com.test.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.bean.LoginTicketBean;
import com.test.demo.dao.LoginTicketDao;
import com.test.demo.service.LoginTicketService;

/**
 * 登陆Service 
 * @author ASUS
 * 创建时间  2017年9月23日 下午10:14:28
 */
@Service
public class LoginTicketServiceImpl implements LoginTicketService {

	@Autowired
	private LoginTicketDao  loginTicketDao;
	
	/**
	 * 新增记录
	 * @author ASUS 创建时间 2017年8月26日 下午1:03:40
	 * @param bean
	 * @return
	 */
	@Override
	public int addTicket(LoginTicketBean bean) {
		return loginTicketDao.addTicket(bean);
	}

	/**
	 * 根据ticket 查询登录信息
	 * @author ASUS 创建时间 2017年8月26日 下午1:11:39
	 * @param ticket
	 * @return
	 */
	@Override
	public LoginTicketBean selectByTicket(String ticket) {
		return loginTicketDao.selectByTicket(ticket);
	}

	
	/**
	 * 根据ticket 更新状态
	 * @author ASUS 创建时间 2017年8月26日 下午1:11:25
	 * @param ticket
	 * @param status
	 */
	@Override
	public void updateStatus(String ticket, int status) {
		loginTicketDao.updateStatus(ticket, status);
	}

}
