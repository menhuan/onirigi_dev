package com.test.demo.async.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.test.demo.async.EventType;
import com.test.demo.async.IEventHandler;
import com.test.demo.bean.EventBean;
import com.test.demo.bean.MessageBean;
import com.test.demo.bean.UserBean;
import com.test.demo.service.MessageService;
import com.test.demo.service.UserService;

/**
 * 
 * @author ASUS
 * 创建时间  2017年9月17日 下午6:22:54
 *
 */
@Component
public class LikeHandler implements IEventHandler {

	@Autowired
	MessageService  messageService;

	@Autowired
	UserService  userService;
	
	/**
	 * 具体处理流程
	 *
	 * @author ASUS
	 * 创建时间  2017年9月17日 下午6:46:24
	 */
	@Override
	public void doHandle(EventBean bean) {
		MessageBean  messageBean=new MessageBean();
		UserBean  userBean=userService.getUser(bean.getActorId());
		messageBean.setToId(bean.getEntityOwnerId());
		messageBean.setContent("用户:"+userBean.getName()
								+"攒了你的咨询，http://127.0.0.1:8080/news/"
								+String.valueOf(bean.getEntityId()));
		
		/**
		 * 系统发送的
		 */
		messageBean.setFromId(3);
		messageBean.setCreatedDate(new Date());
		messageService.addMessage(messageBean);
	}

	@Override
	public List<EventType> getSupportEventTypes() {
		return  Arrays.asList(EventType.LIKE);
	}

}
