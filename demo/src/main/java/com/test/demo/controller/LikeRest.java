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
import com.test.demo.async.EventType;
import com.test.demo.async.impl.EventProducer;
import com.test.demo.base.BaseJson;
import com.test.demo.bean.EventBean;
import com.test.demo.bean.MessageBean;
import com.test.demo.bean.NewsBean;
import com.test.demo.service.LikeService;
import com.test.demo.service.NewsService;
import com.test.demo.util.EntityTypeUtil;

/**
 * 喜欢的事件访问
 * @author ASUS
 * 创建时间  2017年9月17日 下午8:57:41
 *
 */
@RestController
@RequestMapping("likeRest")
public class LikeRest extends BaseJson {

	/**
	 * 日志
	 */
	private static final Logger logger=LoggerFactory.getLogger(LoginRest.class);
	
	@Autowired
	private LikeService  likeService;
	
	@Autowired
	private NewsService  newsService;
	
	@Autowired
	private EventProducer  eventProducer;
	
	/**
	 * 更新喜欢数
	 * @author ASUS
	 * 创建时间  2017年9月17日 下午8:58:48
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "like", method = RequestMethod.POST)
	public Map like(@RequestBody Map  map) {

		int  userId =(int)map.get("userId");
		int  newsId =(int)map.get("newsId");
		
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			long likeCount =likeService.like(userId, EntityTypeUtil.ENTITY_NEWS, newsId );
			
			NewsBean  newsBean=newsService.selectById(newsId);
			newsService.updateLikeCount(newsId, (int)likeCount);

			eventProducer.fireEvent(new EventBean(EventType.LIKE)
						 .setEntityOwnerId(newsBean.getUserId())
						 .setActorId(userId)
						 .setEntityId(newsId)
					);
			
			linkedHashMap.put("result", likeCount);
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----喜欢事件-----" + JSON.toJSONString(map), e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	/**
	 * 不喜欢事件
	 * @author ASUS
	 * 创建时间  2017年9月17日 下午9:01:47
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "dislike", method = RequestMethod.POST)
	public Map dislike(@RequestBody Map  map) {
		
		int  userId =(int)map.get("userId");
		int  newsId =(int)map.get("newsId");
		
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			long disLikeCount =likeService.disLike(userId, EntityTypeUtil.ENTITY_NEWS, newsId );
			newsService.updateLikeCount(newsId, (int)disLikeCount);
			linkedHashMap.put("result", disLikeCount);
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----不喜欢事件-----" + JSON.toJSONString(map), e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	
	
}
