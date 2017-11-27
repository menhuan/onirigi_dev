package com.test.demo.service.proxy.impl;

import static com.test.demo.service.proxy.impl.ProxyPool.PROXY_MAP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.test.demo.bean.proxy.ProxyBean;
import com.test.demo.redis.RedisDao;
import com.test.demo.service.proxy.ProxyPageParser;
import com.test.demo.service.proxy.ProxyService;
import com.test.demo.util.RestRequestClient;


/**
 * 代理类 service
 * @author ASUS
 * 创建时间  2017年11月17日 下午9:21:41
 *
 */
@Service
public class ProxyServiceImpl implements ProxyService {

	/**
	 * 失败的次数
	 */
	private int FAIL_TIMES= 3 ; 
	
	/**
	 * 失败的概率
	 */
	private double PROBABILITY = 0.6 ;
	
	@Autowired
	private RedisDao  redisDao;
	
	/**
	 * 得到代理bean
	 * @author ASUS
	 * 创建时间  2017年11月19日 下午4:38:00
	 */
	@Override
	public ProxyBean getProxyBean(String key) throws Exception {

		String bean  = redisDao.brpop(key);
		ProxyBean proxy = null  ;
		if(StringUtils.isNotBlank(bean)) {
			proxy = JSON.parseObject(bean, ProxyBean.class);
			boolean isRemove = isRemove(proxy);
			if(!isRemove) {
				redisDao.lpush(key, JSON.toJSONString(proxy));
			}
		}
			
		return proxy;
	}

	
	/**
	 * 是否移除bean 从redis 中
	 * @author ASUS
	 * 创建时间  2017年11月17日 下午9:28:41
	 * @param proxy
	 * @return 
	 * @throws Exception
	 */
	private boolean isRemove(ProxyBean proxy) throws Exception{
		boolean result = true ;
		if (proxy.getFailureTimes() >= FAIL_TIMES && PROBABILITY <= (Double.valueOf(proxy.getFailureTimes()) /
				(Double.valueOf(proxy.getFailureTimes()+proxy.getSuccessFulTimes()))) ) {
			result = false ;
		}
		return  result;
	}

	/**
	 * 插入到redis中
	 * @author ASUS
	 * 创建时间  2017年11月19日 下午4:38:15
	 */
	@Override
	public void addProxyBeans(String key, List<String> values) throws Exception {
		redisDao.lpushList(key, values);
	}
	
	/**
	 * 插入到redis中
	 * @author ASUS
	 * 创建时间  2017年11月19日 下午4:38:15
	 */
	@Override
	public void addProxyBean(String key, String value) throws Exception {
		redisDao.lpush(key, value);
	}
	
	
	/**
	 * 根据代理或者直连的方式采集内容
	 * @author ASUS
	 * 创建时间  2017年11月26日 下午5:13:01
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Override
	public List<ProxyBean> getProxyBeans(ProxyBean bean) throws IllegalAccessException, InstantiationException ,IOException{
		List<ProxyBean>  beans = new ArrayList();
		RestRequestClient client = new RestRequestClient() ;
		if(bean == null) {
			//需要把 那个几个初始化的类初始化先 然后可以 并行得去获取  //如果内容过多 容易造成内存溢出的问题
			for(Entry<String, Class> entry : PROXY_MAP.entrySet() ) {
				String html = entry.getKey() ;
				String result = client.restSubmitObject(html, "");
				
				ProxyPageParser parse = (ProxyPageParser) entry.getValue().newInstance() ;
				List<ProxyBean> proxyBeans = parse.parse(result);
				beans.addAll(proxyBeans);
			}
			
		}else {
			
			for(Entry<String, Class> entry : PROXY_MAP.entrySet() ) {
				String html = entry.getKey() ;
				String result = client.restSubmitObject(entry.getKey(), bean.getIp(),String.valueOf(bean.getPort()) , "");
				ProxyPageParser parse = (ProxyPageParser) entry.getValue().newInstance() ;
				List<ProxyBean> proxyBeans = parse.parse(result);
				beans.addAll(proxyBeans);
			}
			
		}
		
		
		return beans ;
		
	}

	
}
