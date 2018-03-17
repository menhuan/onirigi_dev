package com.test.demo.service.imooc;

import java.util.List;

import com.test.demo.dto.imooc.DicDto;

public interface DicService {

	
	/**
	 * 查询 字典表信息
	 * @author ASUS
	 * 创建时间  2017年12月23日 上午10:56:44
	 * @param bean
	 * @return
	 */
	public List<DicDto> selectDics(DicDto bean);
}
