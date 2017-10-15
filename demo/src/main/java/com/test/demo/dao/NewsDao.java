package com.test.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.demo.bean.NewsBean;

/**
 * 咨询dao
 * @author ASUS
 * 创建时间  2017年8月21日 下午10:04:56
 *
 */
@Mapper
public interface NewsDao {


    String TABLE_NAME = "nk_news";

    String INSERT_FIELDS = " title, link, image, like_count, comment_count,created_date,user_id ";

    String SELECT_FIELDS = " id, " + " title, link, image, like_count as likeCount,"
    		+ " comment_count as commentCount,created_date as createdDate,user_id as userId";

    @Insert({
            "insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") Values (#{title},#{link},#{image},#{likeCount}, #{commentCount},#{createdDate},#{userId})"
    })
    int addNews(NewsBean news);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    NewsBean selectById(int id);

    
    /**
     * 查询
     * @author ASUS
     * 创建时间  2017年9月24日 下午6:01:03
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    List<NewsBean> selectByUserIdAndOffset(@Param("userId") int userId, @Param("offset") int offset,
                                       @Param("limit") int limit);
    /**
     * 新增评论
     * @author ASUS
     * 创建时间  2017年9月10日 下午5:47:33
     * @param id
     * @param commentCount
     * @return
     */
    @Update({"update ", TABLE_NAME, " set comment_count = #{commentCount} where id=#{id}"})
    int updateCommentCount(@Param("id") int id, @Param("commentCount") int commentCount);
    
    /**
     * 更新喜欢的数量
     * @author ASUS
     * 创建时间  2017年9月26日 下午9:46:22
     * @param id
     * @param likeCount
     * @return
     */
    @Update({"update ", TABLE_NAME, " set like_count = #{likeCount} where id=#{id}"})
    int updateLikeCount(@Param("id") int id, @Param("likeCount") int likeCount);

}
