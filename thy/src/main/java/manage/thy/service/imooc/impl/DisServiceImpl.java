package manage.thy.service.imooc.impl;

import java.util.LinkedHashMap;
import java.util.List;

import manage.thy.dto.imooc.DicDto;
import manage.thy.util.ResultUtil;
import org.springframework.stereotype.Service;

import manage.thy.model.imooc.DicBean;
import manage.thy.service.imooc.DisService;
import manage.thy.util.RestRequestClient;
import  static  manage.thy.util.BaseUtil.*;

@Service
public class DisServiceImpl implements DisService {


	/**
	 * 根据类型查询字典
	 * @param type  字典类型
	 * @return 查询的结果 list :null 为空
	 * @throws Exception
	 */
	@Override
	public List<DicDto> getListByType(String type) throws Exception {
		DicDto  dicDto =  new DicDto();
		dicDto.setType(type);
		List<DicDto> list = null ;

		RestRequestClient  rest = new RestRequestClient();
		LinkedHashMap  linkedHashMap = rest.restSubmitBean(IMOOC_DIS_SELECT,dicDto);
		if(ResultUtil.isSuccess(linkedHashMap)){
			list = ResultUtil.getResultList(linkedHashMap);
		}

		return  list;
	}

}
