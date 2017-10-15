package com.test.demo.async;


import java.util.List;

import com.test.demo.bean.EventBean;

/**
 * 接口
 * @author ASUS
 * 创建时间  2017年9月16日 下午3:58:06
 *
 */
public interface IEventHandler {

	/**
	 * 具体执行的方法
	 * @author ASUS
	 * 创建时间  2017年9月16日 下午3:58:28
	 * @param bean
	 */
	void doHandle(EventBean  bean);

	/**
	 * 得到类型
	 * @author ASUS
	 * 创建时间  2017年9月16日 下午4:00:05
	 * @return
	 */
	List<EventType> getSupportEventTypes();
}
