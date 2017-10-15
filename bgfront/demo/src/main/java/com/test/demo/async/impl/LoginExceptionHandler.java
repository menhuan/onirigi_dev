package com.test.demo.async.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.demo.async.EventType;
import com.test.demo.async.IEventHandler;
import com.test.demo.bean.EventBean;
import com.test.demo.service.MessageService;

/**
 * 登录的处理
 * @author ASUS
 * 创建时间  2017年9月17日 下午8:13:10
 *
 */
@Component
public class LoginExceptionHandler implements IEventHandler {

	@Autowired
	MessageService   messageService;
	
	/**
	 * 发送邮件
	 *
	 * @author ASUS
	 * 创建时间  2017年9月17日 下午8:13:52
	 */
	@Override
	public void doHandle(EventBean bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EventType> getSupportEventTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
