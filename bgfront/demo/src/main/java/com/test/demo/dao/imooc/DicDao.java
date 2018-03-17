package com.test.demo.dao.imooc;

import java.util.List;

import com.test.demo.bean.imooc.DicBean;

public interface DicDao {

	/**
	 * 查询 字典表信息
	 * @author ASUS
	 * 创建时间  2017年12月23日 上午10:56:44
	 * @param bean
	 * @return
	 */
	public List<DicBean> selectDics(DicBean bean);
	 
}
