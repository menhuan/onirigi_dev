package com.test.demo.controller;

import java.util.LinkedHashMap;
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
import com.test.demo.base.BaseJson;
import com.test.demo.service.UserService;

/**
 * 用户rest
 * 
 * @author ASUS 创建时间 2017年8月21日 下午10:20:18
 *
 */
@RestController
@RequestMapping("userRest")
public class UserRest extends BaseJson {

	private Logger logger = LoggerFactory.getLogger(UserRest.class);

	@Autowired
	private UserService userService;


	/**
	 * 
	 * @author ASUS 创建时间 2017年8月21日 下午10:21:23
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map selectByUserIdAndOffset(int id) {
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result", userService.getUser(id));
			return linkedHashMap;
		} catch (Exception e) {
			logger.error("-----查询用户出错-----" + JSON.toJSONString(id), e);
			return new LinkedHashMap<>();
		}
	}

	/**
	 * 登录
	 * 
	 * @author ASUS 创建时间 2017年8月26日 下午5:28:55
	 * @param map
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public Map login(@RequestBody Map map) {
		String userName = ObjectUtils.toString(map.get("userName"));
		String password = ObjectUtils.toString(map.get("password"));
		LinkedHashMap linkedHashMap = new LinkedHashMap<>();
		try {
			linkedHashMap .put("result", userService.login(userName, password)) ;
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.info("登录出现异常....."+JSON.toJSONString(map),e);
			return super.returnFailtrueInfo(e);
		}
	}

	/**
	 * 注册实现
	 * @author ASUS
	 * 创建时间  2017年8月26日 下午8:57:45
	 * @return
	 */
	@RequestMapping(value="regist",method=RequestMethod.POST)
	public Map regist(@RequestBody Map map) {
		String userName = ObjectUtils.toString(map.get("userName"));
		String password = ObjectUtils.toString(map.get("password"));
		LinkedHashMap linkedHashMap = new LinkedHashMap<>();
		try {
			linkedHashMap .put("result", userService.register(userName, password)) ;
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.info("注册出现异常....."+JSON.toJSONString(map),e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	/**
	 * 登录退出
	 * @author ASUS
	 * 创建时间  2017年8月26日 下午8:59:40
	 * @param map
	 * @return
	 */
	@RequestMapping(value="logout",method=RequestMethod.POST)
	public Map logout(@RequestBody Map map) {
		String ticket = ObjectUtils.toString(map.get("ticket"));
		LinkedHashMap linkedHashMap = new LinkedHashMap<>();
		try {
			userService.logout(ticket);
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.info("登录退出出现异常....."+JSON.toJSONString(map),e);
			return super.returnFailtrueInfo(e);
		}
	}
	
}
