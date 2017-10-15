package com.test.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.demo.bean.UserBean;

/**
 * 用户dao
 * @author ASUS
 * 创建时间  2017年8月21日 下午9:06:12
 *
 */
@Mapper
public interface UserDao {
	
	
	    String TABLE_NAME = "nk_user";

	    String INSERT_FIELDS = " name, password, salt, head_url ";

	    String SELECT_FIELDS = " id, name, password, salt, head_url as headUrl";

	    @Insert({
	            "insert into ", TABLE_NAME, "(", INSERT_FIELDS,
	            ") Values (#{name}, #{password}, #{salt}, #{headUrl})"
	    })
	    int addUser(UserBean user);
	    
	    /**
	     * 根据id 查询用户信息
	     * @author ASUS
	     * 创建时间  2017年8月26日 下午2:13:30
	     * @param id
	     * @return
	     */
	    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
	    UserBean selectById(int id);
	    
	    /**
	     * 根据用户名查询用户信息
	     * @author ASUS
	     * 创建时间  2017年8月26日 下午2:13:44
	     * @param userName
	     * @return
	     */
	    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{userName}"})
	    UserBean selectByName(String userName);

	    
	    /**
	     * 根据id 更新用户信息
	     * @author ASUS
	     * 创建时间  2017年8月26日 下午2:14:06
	     * @param user
	     */
	    @Update({"update ", TABLE_NAME, " set password = #{password} where id=#{id}"})
	    void updatePassword(UserBean user);

	    /**
	     * 删除用户信息
	     * @author ASUS
	     * 创建时间  2017年8月26日 下午2:14:23
	     * @param id
	     */
	    @Delete({"delete from ", TABLE_NAME, " where id = #{id}"})
	    void deleteById(int id);
}
