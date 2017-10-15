package com.test.demo.service;

import java.util.Map;

import com.test.demo.bean.UserBean;
import com.test.demo.exception.ReEnumException;

/**
 * userService
 * @author ASUS
 * 创建时间  2017年8月21日 下午10:11:20
 *
 */
public interface UserService {

	
	/**
	 * 查询用户
	 * @author ASUS
	 * 创建时间  2017年8月21日 下午10:11:55
	 * @return
	 */
	public UserBean getUser(int id);
	
	/**
	 * 登录退出
	 * @author ASUS
	 * 创建时间  2017年8月26日 下午1:14:24
	 * @param ticket
	 */
	public void logout(String ticket);
	
	/**
	 * 添加登录信息
	 * @author ASUS
	 * 创建时间  2017年8月26日 下午1:15:03
	 * @param userId
	 * @return
	 */
	public String addLoginTicket(int userId);

	/**
	 * 登陆
	 * @author ASUS
	 * 创建时间  2017年8月26日 下午1:16:12
	 * @param userName
	 * @param password
	 * @return
	 * @throws ReEnumException 
	 */
	public Map login(String userName,String password) throws Exception;

	/**
	 * 注册
	 * @author ASUS
	 * 创建时间  2017年8月26日 下午1:16:57
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception 
	 */
	public Map register(String userName,String password) throws Exception;
	
}
