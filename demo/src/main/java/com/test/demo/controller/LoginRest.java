package com.test.demo.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.test.demo.async.EventType;
import com.test.demo.async.impl.EventProducer;
import com.test.demo.base.BaseJson;
import com.test.demo.bean.EventBean;
import com.test.demo.bean.LoginTicketBean;
import com.test.demo.bean.UserBean;
import com.test.demo.service.LoginTicketService;
import com.test.demo.service.UserService;

/**
 * 登录的远程服务
 * @author ASUS
 * 创建时间  2017年9月17日 下午8:42:48
 *
 */
@RestController
@RequestMapping("loginRest")
public class LoginRest  extends BaseJson{

	/**
	 * 日志
	 */
	private static final Logger logger=LoggerFactory.getLogger(LoginRest.class);
	
	@Autowired
	private UserService  userService;
	
	@Autowired
	private EventProducer  eventProducer;
	
	@Autowired
	private LoginTicketService   loginTicketService; 
	/**
	 * 登录
	 * @author ASUS
	 * 创建时间  2017年9月17日 下午8:43:45
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public Map login(@RequestBody Map map) {
		
		try {
			String userName =ObjectUtils.toString(map.get("userName"));
			String password =ObjectUtils.toString(map.get("password"));
			int rememberMe =(int) map.get("rememberMe");
			
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			Map<String,Object> userMap=userService.login(userName, password);
//	                eventProducer.fireEvent(new
//	                        EventBean(EventType.LOGIN).setActorId((int) userMap.get("userId"))
//	                        .setExt("username", "牛客").setExt("to", "zjuyxy@qq.com"));
	        linkedHashMap.put("result", userMap);
			
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----登录出错-----" + JSON.toJSONString(map), e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	/**
	 * 注册
	 * @author ASUS
	 * 创建时间  2017年9月17日 下午8:45:35
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	@RequestMapping(value = "registered", method = RequestMethod.POST)
	public Map registered(@RequestBody Map map) {
		
		try {
			String userName =ObjectUtils.toString(map.get("userName"));
			String password =ObjectUtils.toString(map.get("password"));
			int rememberMe =(int) map.get("rememberMe");
		
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();

			Map<String,Object> userMap=userService.register(userName, password);
			if(userMap.containsKey("ticket")) {
				linkedHashMap.put("result",  userMap.get("ticket").toString());
			}
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----注册出错-----" + JSON.toJSONString(map), e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	/**
	 * 登录退出
	 * @author ASUS
	 * 创建时间  2017年9月17日 下午8:46:17
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public Map logout(@RequestBody Map map) {
		
		try {
			String ticket =ObjectUtils.toString(map.get("ticket"));
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			userService.logout(ticket);
			return linkedHashMap;
		} catch (Exception e) {
			logger.error("-----登录出错-----" + JSON.toJSONString(map), e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	/**
	 * 查询拦截器 需要的信息  
	 * 1 根据ticket 查询登陆信息
	 * 2 根据 loginTicke 信息查询用户信息
	 * @author ASUS
	 * 创建时间  2017年9月23日 下午10:06:07
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "loginUser", method = RequestMethod.POST)
	public Map loginUser(@RequestBody String ticket) {
		
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			LoginTicketBean  bean  =loginTicketService.selectByTicket(ticket);
			
			Map map=new HashMap();
			if(bean != null) {
				map.put("loginTicket", bean);
				UserBean  userBean=userService.getUser(bean.getUserId());
				if(userBean != null) {
					map.put("userBean", userBean);
				}
			}
			
			linkedHashMap.put("result", map);
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----拦截器查询出错-----" + JSON.toJSONString(ticket), e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	
	
	
	
	
	
}
