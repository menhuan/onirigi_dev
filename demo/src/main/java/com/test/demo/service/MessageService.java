package com.test.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.test.demo.bean.MessageBean;

/**
 * message 接口
 * @author ASUS
 * 创建时间  2017年9月8日 下午9:28:23
 *
 */
public interface MessageService {

		int addMessage(MessageBean bean);

		/**
		 * 得到查询消息列表
		 * @author ASUS
		 * 创建时间  2017年9月8日 下午9:08:15
		 * @return
		 */
		
		List<MessageBean> getConversationbList(@Param("userId") int userId,@Param("offset") int offset,@Param("limit") int limit);

		/**
		 * 查询没有阅读数量
		 * @author ASUS
		 * 创建时间  2017年9月8日 下午9:23:07
		 * @param userId
		 * @param conversationId
		 * @return
		 */
		int getConversationUnReadCount(@Param("userId") int userId,@Param("conversationId") String conversationId);

		/**
		 * 查询阅读数量
		 * @author ASUS
		 * 创建时间  2017年9月8日 下午9:24:39
		 * @param userId
		 * @param conversationId
		 * @return
		 */
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
		List<MessageBean> getConversationDetail(@Param("conversationId")String conversationId,@Param("offset") int offset,@Param("limit")int limit);
}
