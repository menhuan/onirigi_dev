package manage.thy.service.imooc;

import java.util.List;

import manage.thy.dto.imooc.BusinessDto;

/**
 * 商户接口列表
 * @author ASUS
 * 创建时间  2017年12月24日 下午8:55:08
 *
 */
public interface BusinessService {


	/**
	 * 新增商户
	 * @author ASUS
	 * 创建时间  2017年12月24日 下午8:56:16
	 * @param dto  商户dto对象
	 * @return  是否增加成功 true 增加成功 ;false - 失败
	 * @throws Exception
	 */
	public  boolean add(BusinessDto dto) throws Exception ;

	
	/**
	 * 根据主键id 查询 商户dto
	 * @author ASUS
	 * 创建时间  2017年12月24日 下午8:57:25
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BusinessDto  selectById(Long id) throws Exception ;

	/**
	 * 分页搜索商户列表
	 * @author ASUS
	 * 创建时间  2017年12月24日 下午8:58:12
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public List<BusinessDto> searchByPage (BusinessDto dto) throws Exception ;
	
	/**
	 * 删除某个商户列表
	 * @author ASUS
	 * 创建时间  2017年12月25日 下午10:25:21
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public  boolean remove(Long id) throws Exception ;
	
}
