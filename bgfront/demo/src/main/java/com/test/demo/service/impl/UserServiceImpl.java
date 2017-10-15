package com.test.demo.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.base.BaseCode;
import com.test.demo.base.BaseCofig;
import com.test.demo.bean.LoginTicketBean;
import com.test.demo.bean.UserBean;
import com.test.demo.dao.LoginTicketDao;
import com.test.demo.dao.UserDao;
import com.test.demo.exception.ReEnumException;
import com.test.demo.service.UserService;
import com.test.demo.util.EncrypUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	private LoginTicketDao loginTicketDao;

	@Override
	public UserBean getUser(int id) {
		return userDao.selectById(id);
	}

	/**
	 * 将其设置为失效
	 * 
	 * @author ASUS 创建时间 2017年8月26日 下午4:58:09
	 */
	@Override
	public void logout(String ticket) {
		loginTicketDao.updateStatus(ticket, 1);
	}

	/**
	 * 增加登录情况
	 * 
	 * @author ASUS 创建时间 2017年8月26日 下午4:26:58
	 */
	@Override
	public String addLoginTicket(int userId) {
		LoginTicketBean bean = new LoginTicketBean();
		bean.setUserId(userId);

		Date date = new Date();
		date.setTime(date.getTime() + BaseCofig.SESSION_EXPIRED_SHORT_LONG);
		bean.setExpireDate(date);
		bean.setStatus(BaseCofig.LOGIN_TICKEN_TRUE_STATUS);
		bean.setTicket(UUID.randomUUID().toString().replace("-", "")); // 相当于cook的值
		loginTicketDao.addTicket(bean);

		// 返回ticket 的内容
		return bean.getTicket();
	}

	/**
	 * 登录
	 *
	 * @author ASUS 创建时间 2017年8月26日 下午4:59:05
	 * @throws ReEnumException 
	 */
	@Override
	public Map<String, Object> login(String userName, String password) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		if (StringUtils.isBlank(userName)) {
			throw new ReEnumException(BaseCode.USERNAME1001.getCode());
		}

		if (StringUtils.isBlank(password)) {
			throw new ReEnumException(BaseCode.USERPWD2001.getCode());
		}

		UserBean user = userDao.selectByName(userName);

		if (user == null) {
			throw new ReEnumException(BaseCode.USERNAME1003.getCode());
		}

		password = password + user.getSalt();

		if (!EncrypUtil.MD5encrpy(password).equals(user.getPassword())) {
			throw new ReEnumException(BaseCode.USERPWD2002.getCode());
		}
		// 登录
		String ticket = addLoginTicket(user.getId());
		map.put("ticket", ticket);
		map.put("userId", user.getId());

		return map;
	}

	/**
	 * 注册的流程 1. 首先校验 用户名 密码是否有空的 有空的直接返回 密码的长度时候符合规矩 2. 检验完毕后 需要跟数据库检查用户名是否已经被注册了 3.
	 * 密码加密 规则 在原先的密码基础上加上一个随机码 定位6位 可以用 UUID生成 然后使用 sd5加密 存放到数据库中 4. 登录加入
	 * 
	 * @author ASUS 创建时间 2017年8月26日 下午1:45:13
	 */
	@Override
	public Map<String, Object> register(String userName, String password) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		if (StringUtils.isBlank(userName)) {
			throw new ReEnumException(BaseCode.USERNAME1001.getCode());
		}

		if (StringUtils.isBlank(password)) {
			throw new ReEnumException(BaseCode.USERPWD2001.getCode());
		}

		UserBean user = userDao.selectByName(userName);

		if (user != null) {
			throw new ReEnumException(BaseCode.USERNAME1002.getCode());
		}

		user = new UserBean();
		user.setName(userName);
		
		String salt =UUID.randomUUID().toString().substring(BaseCofig.USER_SALT_START, BaseCofig.USER_SALT_END);
		user.setSalt(salt);
		user.setHeadUrl(BaseCofig.USER_HEAD_URL);
		user.setPassword(EncrypUtil.MD5encrpy(password+salt));
		userDao.addUser(user);
		
		user = userDao.selectByName(userName);
		// 登录
		String ticket = addLoginTicket(user.getId());
		map.put("ticket", ticket);

		return map;

	}

}
