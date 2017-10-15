package com.test.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.test.demo.base.BaseCofig;
import com.test.demo.base.BaseJson;
import com.test.demo.bean.MessageBean;
import com.test.demo.bean.UserBean;
import com.test.demo.service.MessageService;
import com.test.demo.service.UserService;

@RestController
@RequestMapping("messageRest")
public class MessageRest extends BaseJson{
	
	private static final Logger logger =LoggerFactory.getLogger(MessageRest.class);

	@Autowired
	private MessageService  messageService;
	
	@Autowired
	private UserService userService;
	/**
	 * 增加评论
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午10:15:13
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "addMessage", method = RequestMethod.POST)
	public Map addMessage(@RequestBody MessageBean  bean) {
		
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			messageService.addMessage(bean);
			linkedHashMap.put("result",bean.getId()  );
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----新增Comment出错-----" + JSON.toJSONString(bean), e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	/**
	 * 得到列表
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午10:58:51
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "msgList", method = RequestMethod.POST)
	public Map getConversationbList(@RequestBody Map  map) {
		
		try {
			int localUserId=(Integer) map.get("localUserId");
			List<HashMap>  hashMaps =new ArrayList<>();
			
			List<MessageBean> converList =messageService.getConversationbList(localUserId, BaseCofig.minPage, BaseCofig.maxPage);
			
			List<Map> messageBeans=new ArrayList<>();
			
			for(MessageBean message: converList) {
				Map  resultMap = new HashMap<>();
				resultMap.put("conversation", message);
				int targetId =message.getFromId() == localUserId ?message.getToId() :message.getFromId();
				UserBean userBean =userService.getUser(targetId);
				resultMap.put("user", userBean);
				resultMap.put("unreadCount", messageService.getConversationUnReadCount(localUserId, message.getConversationId()));
				messageBeans.add(resultMap);
			}
		
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result",messageBeans);
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----查询用户出错-----" + JSON.toJSONString(map), e);
			return super.returnFailtrueInfo(e);
		}
	}
	/**
	 * 查询消息汇总
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午11:07:14
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "getConversationUnReadCount", method = RequestMethod.POST)
	public Map getConversationUnReadCount(@RequestBody Map  map) {
		int userId =(Integer) map.get("userId");
		String conversationId = map.get("conversationId").toString();
		
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result", messageService.getConversationUnReadCount(userId,conversationId));
			return linkedHashMap;
		} catch (Exception e) {
			logger.error("-----查询消息汇总出错-----" + JSON.toJSONString(map), e);
			return new LinkedHashMap<>();
		}
	}
	
	
	/**
	 * 查询详细信息
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午11:11:08
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "getConversationDetail", method = RequestMethod.POST)
	public Map getConversationDetail(@RequestBody Map  map) {
		
		String conversationId = map.get("conversationId").toString();
		try {
			List message= new ArrayList<>();
			List<MessageBean> conversationList =messageService.getConversationDetail(conversationId,BaseCofig.minPage,BaseCofig.maxPage);
			for(MessageBean bean:conversationList) {
				
				Map resulMap =new HashMap();
				resulMap.put("message", bean);
				UserBean  userBean =userService.getUser(bean.getFromId());
				if(userBean == null) {
					continue;
				}
				
				resulMap.put("headUrl", userBean.getHeadUrl());
				resulMap.put("userName", userBean.getName());
				message.add(resulMap);
			}
			
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result",message);
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----查询消息详细-----" + JSON.toJSONString(map), e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	
}
