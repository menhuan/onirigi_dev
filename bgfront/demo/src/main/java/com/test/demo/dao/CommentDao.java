package com.test.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.test.demo.bean.CommentBean;

/**
 * 评论dao
 * @author ASUS
 * 创建时间  2017年9月8日 下午8:24:56
 *
 */
@Mapper
public interface CommentDao {
		
	String TABLE_NAME=" nk_comment";
	
	String INSERT_FIELDS=" user_id, content, created_date, entity_id, entity_type, status";
	
	String SELECT_FILEDS=" id, user_id as userId, content, created_date as createdDate,"
			+ " entity_id as entityId, entity_type as entityType, status";
	
	/**
	 * 增加评论
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午8:50:07
	 * @param bean
	 * @return
	 */
	@Insert(value = { "insert into ",TABLE_NAME,"(",INSERT_FIELDS,") values ( #{userId},#{content},#{createdDate},#{entityId},#{entityType}"
			+ ",#{status})" })
	int addComment(CommentBean bean);
	
	/**
	 * 根据实体id 和实体类型查找评论
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午8:53:31
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	@Select({"select ",SELECT_FILEDS," from ",TABLE_NAME, " where entity_id=#{entityId} and entity_type=#{entityType} order by id desc"})
	List<CommentBean> selectByEntity(@Param("entityId") int entityId ,@Param("entityType") int entityType);
	
	/**
	 * 查找评论数
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午8:55:49
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	@Select({"select count(id) from ",TABLE_NAME," where entity_id=#{entityId} and entity_type=#{entityType}"})
	int getCommentCount(@Param("entityId")int entityId ,@Param("entityType") int entityType);
	
}
