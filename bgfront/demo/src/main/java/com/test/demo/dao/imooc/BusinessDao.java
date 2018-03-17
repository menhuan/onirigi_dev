package com.test.demo.dao.imooc;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.test.demo.bean.imooc.BusinessBean;

/**
 * 商户 dao
 * @author ASUS
 * 创建时间  2017年12月23日 下午12:40:03
 *
 */
public interface BusinessDao {

	
	
	/**
	 * 插入
	 * @author ASUS
	 * 创建时间  2017年12月23日 下午1:42:15
	 * @param bean
	 * @return
	 * @throws Execution
	 */
	public int  insert(BusinessBean  bean)throws Exception ;
	
	
	/**
	 * 根据主键id查询 商店
	 * @author ASUS
	 * 创建时间  2017年12月23日 下午1:56:53
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public BusinessBean selectBeanById(BusinessBean bean) throws Exception ;
	
	/**
	 * 根据条件查询 商户列表
	 *  标题、副标题、描述 三个过滤条件为模糊查询
	 *  并且三个过滤条件之间为或者的关系  用or链接
	 *  这三个过滤条件与其他过滤条件依然是并且关系 用and链接
	 * @author ASUS
	 * 创建时间  2017年12月23日 下午1:57:47
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	
	public List<BusinessBean> selectLikeByPage(BusinessBean bean) throws Exception ;
	
	/**
	 * 根据条件分页查询商户列表
	 * @author ASUS
	 * 创建时间  2017年12月23日 下午2:15:03
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public List<BusinessBean> selectByPage(BusinessBean bean) throws Exception ;
	
	/**
	 * 更新商户的【统计评论星星总数】、【统计评论总次数】、商户的【星级】用这两个字段数据计算得出
	 * @author ASUS
	 * 创建时间  2017年12月23日 下午2:00:00
	 * @param map 传入的数据
	 * @return
	 * @throws Exception
	 */
	public int updateStar(Map<String,Date> map) throws Exception ;
}
