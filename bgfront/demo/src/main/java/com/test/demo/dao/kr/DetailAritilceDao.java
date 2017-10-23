package com.test.demo.dao.kr;

import java.util.concurrent.ExecutionException;

import com.test.demo.bean.reptile.DetailAriticleBean;

/**
 * 入库接口  ps： 突然感觉 这么多字段入库我就是傻
 * @author ASUS
 * 创建时间  2017年10月22日 下午10:14:44
 *
 */
public interface DetailAritilceDao {
	
	/**
	 * 插入文章详情
	 * @author ASUS
	 * 创建时间  2017年10月22日 下午10:16:41
	 * @param bean
	 * @throws Exception
	 */
	public void addDetailAritilces(DetailAriticleBean bean) throws Exception;

}
