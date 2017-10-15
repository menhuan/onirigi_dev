package com.test.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.demo.bean.LoginTicketBean;

/**
 * 登录信息记录dao
 * 
 * @author ASUS 创建时间 2017年8月26日 下午12:46:38
 *
 */
@Mapper
public interface LoginTicketDao {

	String TABLE_NAME = "nk_login_ticket";

	String INSERT_FIELDS = "user_id ,expired , status ,ticket";
	
	
	String SELECT_FIELDS = " id,  " + "user_id as userId,expired as expireDate, status ,ticket";

	/**
	 * 新增记录
	 * 
	 * @author ASUS 创建时间 2017年8月26日 下午1:03:40
	 * @param bean
	 * @return
	 */
	@Insert({ "insert into ", TABLE_NAME, "(", INSERT_FIELDS ,") values( #{userId},#{expireDate},#{status},#{ticket})" })
	public int addTicket(LoginTicketBean bean);

	/**
	 * 根据ticket 查询登录信息
	 * 
	 * @author ASUS 创建时间 2017年8月26日 下午1:11:39
	 * @param ticket
	 * @return
	 */
	@Select({ "select ", SELECT_FIELDS, " from ", TABLE_NAME, " where ticket=#{ticket}" })
	public LoginTicketBean selectByTicket(String ticket);

	/**
	 * 根据ticket 更新状态
	 * 
	 * @author ASUS 创建时间 2017年8月26日 下午1:11:25
	 * @param ticket
	 * @param status
	 */
	@Update({ "update ", TABLE_NAME, "set status =#{status}  where ticket=#{ticket}" })
	public void updateStatus(@Param("ticket") String ticket, @Param("status") int status);

}
