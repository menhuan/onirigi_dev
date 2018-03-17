package manage.thy.service.imooc;

import java.util.List;

import manage.thy.dto.imooc.AdDto;

/**
 * 广告接口
 * @author ASUS
 * 创建时间  2017年12月2日 下午7:25:35
 *
 */
public interface AdService {

	
	/**
	 * 新增 广告链接
	 * @author ASUS
	 * 创建时间  2017年12月2日 下午10:10:06
	 * @param adDto 
	 * @return   true : 新增成功 false : 新增失败
	 * @throws Exception
	 */
	public boolean addAd(AdDto adDto) throws Exception;
	
	/**
	 * 查询
	 * @author ASUS
	 * 创建时间  2017年12月6日 下午10:49:25
	 * @param adDto
	 * @return
	 * @throws Exception
	 */
	public List<AdDto> select(AdDto adDto) throws Exception ; 

	/**
	 * 根据id 删除
	 * @author ASUS
	 * 创建时间  2017年12月6日 下午10:55:36
	 * @param id  广告id
	 * @return  true ： 删除成功  false: 删除失败
	 * @throws Exception
	 */
	public boolean remove(Long id) throws Exception ;
	
	/**
	 * 修改
	 * @author ASUS
	 * 创建时间  2017年12月6日 下午11:00:25
	 * @param adDto
	 * @return
	 * @throws Exception
	 */
	public boolean modify(AdDto adDto) throws Exception ;
	
	

}
