package com.test.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.base.BaseCofig;
import com.test.demo.bean.kr.HtmlBean;
import com.test.demo.bean.reptile.BossJobBean;
import com.test.demo.bean.reptile.ColumnBean;
import com.test.demo.bean.reptile.Counters;
import com.test.demo.bean.reptile.DBCounters;
import com.test.demo.bean.reptile.DetailAriticleBean;
import com.test.demo.bean.reptile.HotPostsBean;
import com.test.demo.bean.reptile.InternalLinks;
import com.test.demo.bean.reptile.RelatedPosts;
import com.test.demo.bean.reptile.Role;
import com.test.demo.bean.reptile.abTestValue;
import com.test.demo.dao.kr.HtmlDao;
import com.test.demo.mongodb.MongoDao;
import com.test.demo.mongodb.MongoKrService;
import com.test.demo.redis.RedisDao;
import com.test.demo.redis.RedisKeyUtil;
import com.test.demo.service.ReptileService;
import com.test.demo.util.PatternUtil;
import com.test.demo.util.ReadUrlUtil;

/**
 * 
 * @author ASUS
 * 创建时间  2017年10月22日 上午11:22:24
 *
 */
@Service
public class ReptileServiceImpl implements ReptileService {

	
	private static final Logger logger =LoggerFactory.getLogger(ReptileServiceImpl.class);
	
	@Autowired
	private RedisDao  redisDao;
	
	@Autowired
	private HtmlDao  htmlDao;
	
	@Autowired
	private MongoKrService  mongoKrService;
	
	/**
	 * 1 首先爬取首页
	 * 2 根据爬取到首页数据 根据具体的正则表达式获取内容
	 * 3 将这些存入到redis 中
	 * 4 如果
	 * @author ASUS
	 * 创建时间  2017年10月19日 下午8:52:27
	 */
	@Override
	public void reptileIndex() throws Exception {
	 
		String result =ReadUrlUtil.readUrl(BaseCofig.PATER_HTML_KR_INDEX);

		if(StringUtils.isNotBlank(result)) {
			 List<HtmlBean> htmlBeans = new  ArrayList<>();	
			
			 if(result.contains(BaseCofig.CONTAIN_KR_COTENT)) {
				 Pattern  pattern = Pattern.compile(BaseCofig.PATER_KR_HTML,Pattern.DOTALL);
		         Matcher  matcher = pattern.matcher(result);
		         List<HtmlBean>  beans = new ArrayList<>(); 
		         while(matcher.find()) {
		            String html =matcher.group(0);
		            redisDao.lpush(RedisKeyUtil.REDIS_LIST_SUCCESS_HTML_KEY, html);
		            
		            Matcher  matcherNum = PatternUtil.patternMatcher(BaseCofig.CONTAIN_KR_NUM, html);
		            Integer  htmlId = 0 ;
		            while(matcherNum.find()) {
		            	htmlId =Integer.parseInt(matcherNum.group());
		            }
		            
		            HtmlBean  bean =new HtmlBean();
		            bean.setCreateDate(new Date());
		            bean.setHtml(html);
		            bean.setHtmlId(htmlId);
		            
		            htmlBeans.add(bean);
		         }
		         
		         htmlDao.addHtml(htmlBeans);
		         
		     }{
		    	 logger.error("这次爬虫 没有爬到需要的数据");
		     }
		}else {
			logger.error("读取Url 出错 内容为空");
		}
       
       
		
	}

	/**
	 * 1  从 队列中取出来数据 
	 * 2  然后使用读取的内容去读取 
	 * 3  解析需要的数据 然后获得 
	 * 4  分为4个类型 存入到 mongdb中 做下  这个先预留下 读取到内容之后再
	 * 5  放到数据库中
	 * @author ASUS
	 * 创建时间  2017年10月20日 下午9:50:55
	 */
	@Override
	public void reptileHtml() throws Exception {
		
		List<String> value = redisDao.brpopList(RedisKeyUtil.REDIS_LIST_SUCCESS_HTML_KEY);
		
		for(String va : value) {
			
			String result =ReadUrlUtil.readUrl(va);
			List<Object> list = new ArrayList<Object>();
			ReadUrlUtil.parseJsonDate(result, list);
			mongoKrService.saveKrObject(list);
		}
		
		
		
	}
	
	/**
	 * 根据不同的数据入数据库  暂时不入库
	 * @author ASUS
	 * 创建时间  2017年10月22日 下午2:30:21
	 * @param list
	 */
	public void saveHtml(List<Object> list) {
		DetailAriticleBean  detailBean = (DetailAriticleBean) list.get(0);
		List<abTestValue>  abTest= (List<abTestValue>) list.get(1) ; 
		List<BossJobBean> bossJob = (List<BossJobBean>) list.get(2);
		List<HotPostsBean> hots = (List<HotPostsBean>) list.get(3);
		
		
		List<DBCounters>  dbCounters  = detailBean.getDbCounters() ; 
		
		List<Role>  roles  = detailBean.getRolse() ;
		roles.add(detailBean.getRole());
		
		ColumnBean columnBean = detailBean.getColumn() ;
		List<InternalLinks>  internalLinks=detailBean.getInternal_links();
		List<RelatedPosts> relatedPosts = detailBean.getRelatedPosts();
		Counters counters=detailBean.getCounters();
		
		
		
		
	}
	
	

}
