package com.test.demo.service.imooc;

import java.util.List;

import com.test.demo.bean.imooc.AdBean;
import com.test.demo.dto.imooc.AdDto;

public interface AdService {

	
	/**
	 * 增加广告
	 * @author ASUS
	 * 创建时间  2017年12月7日 下午10:09:27
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public int insert (AdDto bean) throws Exception ;
	
	
	/**
	 * 根据条件 分页查询
	 * @author ASUS
	 * 创建时间  2017年12月7日 下午10:10:12
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public List<AdDto> selectByPage(AdDto bean) throws Exception ;
	
	/**
	 * 根据bean  修改对象
	 * @author ASUS
	 * 创建时间  2017年12月7日 下午10:11:16
	 * @param bean  待修改的广告对象
	 * @return   影响的行数
	 * @throws Exception
	 */
	public int update(AdDto bean )  throws Exception ;

	/**
	 * 根据主键删除对象
	 * @author ASUS
	 * 创建时间  2017年12月7日 下午10:12:22
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Long id) throws Exception ;

}
