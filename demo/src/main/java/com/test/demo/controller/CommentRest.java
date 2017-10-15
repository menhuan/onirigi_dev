package com.test.demo.controller;

import static org.mockito.Matchers.intThat;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.test.demo.base.BaseCofig;
import com.test.demo.bean.CommentBean;
import com.test.demo.service.CommentService;

/**
 * 评论的rest
 * @author ASUS
 * 创建时间  2017年9月8日 下午11:27:40
 *
 */
@RestController
@RequestMapping("commentRest")
public class CommentRest {
	
	private static final Logger lgger =LoggerFactory.getLogger(CommentRest.class);
	
	@Autowired
	private CommentService  commentService;
	
	/**
	 * 增加评论
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午11:27:50
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "getConversationDetail", method = RequestMethod.POST)
	public Map addComment(@RequestBody CommentBean  bean) {
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result", commentService.addComment(bean));
			return linkedHashMap;
		} catch (Exception e) {
			lgger.error("-----新增评论-----" + JSON.toJSONString(bean), e);
			return new LinkedHashMap<>();
		}
	}
	/**
	 * 查询评论列表
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午11:31:11
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "selectByEntity", method = RequestMethod.POST)
	public Map selectByEntity(@RequestBody CommentBean  bean) {
		int entityId =bean.getEntityId();
		int entityType=bean.getEntityType();
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result", commentService.selectByEntity(entityId,entityType));
			return linkedHashMap;
		} catch (Exception e) {
			lgger.error("-----查询实体列表-----" + JSON.toJSONString(bean), e);
			return new LinkedHashMap<>();
		}
	}

	/**
	 * 得到评论的数量
	 * @author ASUS
	 * 创建时间  2017年9月8日 下午11:30:51
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "getCommentCount", method = RequestMethod.POST)
	public Map getCommentCount(@RequestBody CommentBean  bean) {
		int entityId =bean.getEntityId();
		int entityType=bean.getEntityType();
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result", commentService.getCommentCount(entityId,entityType));
			return linkedHashMap;
		} catch (Exception e) {
			lgger.error("-----查询评论数量-----" + JSON.toJSONString(bean), e);
			return new LinkedHashMap<>();
		}
	}


}
