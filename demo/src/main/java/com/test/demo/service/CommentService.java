package com.test.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.test.demo.bean.CommentBean;

/**
 * 评论service
 * @author ASUS
 * 创建时间  2017年9月8日 下午10:09:49
 *
 */
public interface CommentService {

	
	/**
	 * 增加评论
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午8:50:07
	 * @param bean
	 * @return
	 */
	int addComment(CommentBean bean);
	
	/**
	 * 根据实体id 和实体类型查找评论
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午8:53:31
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	List<CommentBean> selectByEntity(@Param("entityId") int entityId ,@Param("entityType") int entityType);
	
	/**
	 * 查找评论数
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午8:55:49
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	int getCommentCount(@Param("entityId")int entityId ,@Param("entityType") int entityType);
	
}
