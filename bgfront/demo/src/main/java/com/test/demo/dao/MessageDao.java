package com.test.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.test.demo.bean.MessageBean;


/**
 * 消息dao
 * @author ASUS
 * 创建时间  2017年9月8日 下午9:01:23
 *
 */
public interface MessageDao {

	String TABLE_NAME= "nk_message";
	
	String INSERT_FIELDS= " from_id, to_id ,content, has_read, conversation_id, created_date ";

	String SELECT_FIELDS=" id, " +" from_id as fromId, to_id as toId ,content, has_read as hasRead, conversation_id as conversationId, "
			+ "	DATE_FORMAT(created_date,'%Y-%m-%d %H:%i:%s') as createdDate";

	@Insert({
			"insert into ",TABLE_NAME," (",INSERT_FIELDS,") values(","#{formId},#{toId},#{content},#{hasRead},#{conversationId},#{createdDate})"
	})
	int addMessage(MessageBean bean);
	
	/**
	 * 得到查询消息列表
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午9:08:15
	 * @return
	 */
	@Select({
		"select ",SELECT_FIELDS,",count(id) as id from (select * from ",TABLE_NAME ,"  where from_id =#{userId} or to_id =#{userId} order by id desc )"
		,"tt group by conversation_id order by created_date desc limit #{offset},#{limit}"
	})
	List<MessageBean> getConversationbList(@Param("userId") int userId,@Param("offset") int offset,@Param("limit") int limit);
	
	/**
	 * 查询没有阅读数量
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午9:23:07
	 * @param userId
	 * @param conversationId
	 * @return
	 */
	@Select({
		"select count(id) from ",TABLE_NAME, " where has_read =0 and to_id=#{userId} and conversation_id=#{conversationId}"
	})
	int getConversationUnReadCount(@Param("userId") int userId,@Param("conversationId") String conversationId);
	
	/**
	 * 查询阅读数量
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午9:24:39
	 * @param userId
	 * @param conversationId
	 * @return
	 */
	@Select({
		"select count(id) from ",TABLE_NAME ," where has_read=0 and to_id =#{userId} "
	})
	int getConversationTotalCount(@Param("userId")int userId,@Param("conversationId")String conversationId);


	/**
	 * 查询所有的消息信息
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午9:27:42
	 * @param conversationId
	 * @param offset
	 * @param limit
	 * @return
	 */
    @Select({
    	"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where conversation_id=#{conversationId} order by id desc limit #{offset},#{limit}"
    	})
	List<MessageBean> getConversationDetail(@Param("conversationId")String conversationId,@Param("offset") int offset,@Param("limit")int limit);
}
