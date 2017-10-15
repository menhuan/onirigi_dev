package com.test.demo.service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.demo.bean.LoginTicketBean;

/**
 * loginService
 * @author ASUS
 * 创建时间  2017年9月23日 下午10:13:18
 *
 */
public interface LoginTicketService {

	
	/**
	 * 新增记录
	 * @author ASUS 创建时间 2017年8月26日 下午1:03:40
	 * @param bean
	 * @return
	 */
	public int addTicket(LoginTicketBean bean);

	/**
	 * 根据ticket 查询登录信息
	 * @author ASUS 创建时间 2017年8月26日 下午1:11:39
	 * @param ticket
	 * @return
	 */
	public LoginTicketBean selectByTicket(String ticket);

	/**
	 * 根据ticket 更新状态
	 * @author ASUS 创建时间 2017年8月26日 下午1:11:25
	 * @param ticket
	 * @param status
	 */
	public void updateStatus(@Param("ticket") String ticket, @Param("status") int status);
}
