package com.test.demo.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.test.demo.base.BaseCofig;
import com.test.demo.bean.reptile.BossJobBean;
import com.test.demo.bean.reptile.DetailAriticleBean;
import com.test.demo.bean.reptile.HotPostsBean;
import com.test.demo.bean.reptile.abTestValue;


/**
 * 读取 根据 链接读取url
 * @author ASUS
 * 创建时间  2017年10月19日 下午9:18:17
 *
 */
public class ReadUrlUtil {
	
	private static final Logger  LOGGER = LoggerFactory.getLogger(ReadUrlUtil.class);
	
	/**
	 * 根据 url 读取网页链接
	 * @author ASUS
	 * 创建时间  2017年10月19日 下午9:19:06
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String readUrl(String url) throws Exception {
		  BufferedReader in = null;    
		  StringBuilder result = new StringBuilder(); 
	        try {    
	            URL realUrl = new URL(url);    
	            URLConnection connection = realUrl.openConnection();    
	            connection.connect();    
	            in = new BufferedReader(new InputStreamReader(    
	                    connection.getInputStream(), "UTF-8"));    
	            String line;    
	            while ((line = in.readLine()) != null) {    
	                result.append(line) ;    
	            }    
	        } catch (Exception e) {    
	        	LOGGER.error("发送GET请求出现异常！" + e);
	            e.printStackTrace();    
	        }finally {    
	            try {    
	                if (in != null) {    
	                    in.close();    
	                }    
	            } catch (Exception e) {    
	            	LOGGER.error("读取36kr首页数据 关闭流失败。。。。。。",e);
	            }    
	        }  
	        
	       return result.toString(); 
	}

	
	/**
	 * 按照格式读取需要的json 数据
	 * @author ASUS
	 * 创建时间  2017年10月20日 下午11:40:36
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public static void parseJsonDate(String result,List<Object> objects)throws Exception {
		
	        Pattern  pattern = Pattern.compile(BaseCofig.SCRIPT_KR_HTML,Pattern.DOTALL);
	        Matcher  matcher = pattern.matcher(result);
	        String   value  = null ;
	        while(matcher.find()) {
	        	value = matcher.group(0);
	        	
	        	if(value.contains("<script>var props=")) {
	        		value = value.replaceAll("<script>var props=", "");
	        		value = value.replaceAll("</script>", "");
	        		value = value.replace("},locationnal=", ",locationnal:");
	        		value = value + "}";
	        		
	        		try {
	        			System.out.println(value);
	            		JSONObject  o =JSON.parseObject(value, JSONObject.class);
	            		JSONObject  detailAriticle = o.getJSONObject("detailArticle|post");
	            		JSONArray   abTest = o.getJSONArray("abTest|abtest");
	            		JSONArray   bossJobs = o.getJSONArray("bossJobs|job");
	            		JSONArray   hotPost = o.getJSONArray("hotPostsOf30|hotPost");
	            		
	            		DetailAriticleBean  detailBean =JSON.parseObject(detailAriticle.toString(), DetailAriticleBean.class);
	            		List<abTestValue>  list= JSON.parseArray(abTest.toString(), abTestValue.class) ; 
	            	
	            		List<BossJobBean> bossJob = JSON.parseArray(bossJobs.toString(), BossJobBean.class);
	            		List<HotPostsBean> hots = JSON.parseArray(hotPost.toString(), HotPostsBean.class);
	            		
	            		objects.add(detailBean);
	            		objects.add(list);
	            		objects.add(bossJob);
	            		objects.add(hots);
		
					} catch (Exception e) {
						LOGGER.error("转换json数据出错。。。",e);
						e.printStackTrace();
					}
	        	}
	        	
	        }
	}
	
	
}
