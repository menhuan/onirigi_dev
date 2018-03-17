package manage.thy.service.imooc;

import java.util.List;

import manage.thy.dto.imooc.DicDto;
import manage.thy.model.imooc.DicBean;

/**
 * 城市列表
 * @author ASUS
 * 创建时间  2017年12月24日 下午9:36:01
 *
 */
public interface DisService {

	/**
	 * 根据类型获取字典表列表
	 * @author ASUS
	 * 创建时间  2017年12月24日 下午9:48:54
	 * @param type  根据类型插叙
	 * @return
	 * @throws Exception
	 */
	public List<DicDto> getListByType(String type) throws Exception;
}
