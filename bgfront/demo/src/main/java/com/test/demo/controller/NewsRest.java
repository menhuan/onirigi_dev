package com.test.demo.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
import com.test.demo.base.BaseJson;
import com.test.demo.bean.CommentBean;
import com.test.demo.bean.NewsBean;
import com.test.demo.service.CommentService;
import com.test.demo.service.LikeService;
import com.test.demo.service.NewsService;
import com.test.demo.service.ToutiaoService;
import com.test.demo.service.UserService;
import com.test.demo.util.EntityTypeUtil;

/***
 * 新闻资讯rest
 * @author ASUS
 * 创建时间  2017年8月22日 下午9:31:59
 *
 */
@RestController
@RequestMapping("newsRest")
public class NewsRest extends BaseJson {

	private static final Logger logger = LoggerFactory.getLogger(NewsRest.class);

    @Autowired
    private ToutiaoService toutiaoService;

    @Autowired
    private UserService userService;
	
    @Autowired
    private NewsService  newsService;
    
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private LikeService  likeService;
    
    
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/newsDetail",method= {RequestMethod.POST,RequestMethod.GET},produces="application/json;charset=utf-8")
	public Map newsDetail(@RequestBody Map map) {
    	
    		LinkedHashMap linkedHashMap = new LinkedHashMap<>();
    	try {
    		Integer  id =(Integer) map.get("newsId");
    		NewsBean newsBean = newsService.selectById(id);

    		if(newsBean != null) {
    			int localUserId =map.get("userId") !=null ? (int)map.get("userId") : 0;
    			if(localUserId != 0) {
    				linkedHashMap.put("like", likeService.getLikeStatus(localUserId, EntityTypeUtil.ENTITY_NEWS,newsBean.getId() ));
    			}else {
    				linkedHashMap.put("like", 0);
    			}
    			List<CommentBean>  comments= commentService.selectByEntity(newsBean.getId(), EntityTypeUtil.ENTITY_NEWS);
    			
    			 List<Map> vos = new ArrayList<>();
		         for (CommentBean comment : comments) {
		            Map listmap=new HashMap<>();
		            listmap.put("comment", comment);
		            listmap.put("user", userService.getUser(comment.getUserId()));
		            vos.add(listmap);
		         }
		     	linkedHashMap.put("comments", vos );
    		}

    		linkedHashMap.put("news", newsBean);
    		linkedHashMap.put("owner", userService.getUser(newsBean.getUserId()));
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("----查询News出错-----" + JSON.toJSONString(map), e);
			return super.returnFailtrueInfo(e);
		}
        
        
	}
    
    @RequestMapping("getJson")
    public Map  getJson() {
    	Map map=new HashMap<>();
    	Map map1=new HashMap<>();
    	List list=new ArrayList<>();
    	list.add(1);
    	
    	map.put("key", list);
    	map1.put("key1", new Date());
    	return map1;
    }
    
    /**
     * 查询最新的信息
     * @author ASUS
     * 创建时间  2017年9月10日 下午5:52:16
     * @param map
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="getLastesNews" ,method=RequestMethod.POST)
    public Map getLastesNews (@RequestBody Map map) {
    	
    	try {
    		LinkedHashMap linkedHashMap = new LinkedHashMap<>();
	    	
	    	Integer  userId =(Integer) map.get("userId");
    		
    		List<NewsBean> newsBeans = newsService.selectByUserIdAndOffset(userId,BaseCofig.minPage,BaseCofig.maxPage);

    		if(newsBeans != null) {
    			List<Map> vos = new ArrayList<>();
    			for(NewsBean bean :newsBeans  ) {
    				int localUserId =map.get("userId") !=null ? (int)map.get("userId") : 0;
        			if(localUserId != 0) {
        				linkedHashMap.put("like", likeService.getLikeStatus(localUserId, EntityTypeUtil.ENTITY_NEWS,bean.getId() ));
        			}else {
        				linkedHashMap.put("like", 0);
        			}
        			
        			Map listmap=new HashMap<>();
        			listmap.put("news", bean);
 		            listmap.put("user", userService.getUser(bean.getUserId()));
        			
 		            vos.add(listmap);
    			}
    			linkedHashMap.put("result", vos );
    		}
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----查询NewsDetail出错-----" + JSON.toJSONString(map), e);
			return super.returnFailtrueInfo(e);
		}
    	
    }
    
    /**
     * 新增一个news
     * @author ASUS
     * 创建时间  2017年9月10日 下午6:14:05
     * @param map
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="addNews" ,method=RequestMethod.POST)
    public Map addNews (@RequestBody NewsBean news) {
    
    	try {
    		LinkedHashMap linkedHashMap =new LinkedHashMap<>();
        	linkedHashMap.put("result", newsService.addNews(news));
        	return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("------ 新增news------"+JSON.toJSONString(news),e);
			return super.returnFailtrueInfo(e);
		}
    
    }
    
    /**
     * 新增Comment
     * @author ASUS
     * 创建时间  2017年9月10日 下午6:19:59
     * @param news
     * @return
     */
    @RequestMapping(value="addComment" ,method=RequestMethod.POST)
    public Map addComment (@RequestBody CommentBean bean) {
    	try {
    		LinkedHashMap linkedHashMap =new LinkedHashMap<>();
    		commentService.addComment(bean);
    		int count =commentService.getCommentCount(bean.getEntityId(), bean.getEntityType());
    		newsService.updateCommentCount(bean.getEntityId(), count);
    		return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("------ 新增comment出错------"+JSON.toJSONString(bean),e);
			return super.returnFailtrueInfo(e);
		}
    }
    
	
}
