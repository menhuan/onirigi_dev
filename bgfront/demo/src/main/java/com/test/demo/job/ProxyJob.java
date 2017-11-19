package com.test.demo.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.test.demo.bean.proxy.ProxyBean;
import com.test.demo.service.proxy.ProxyPageParser;
import com.test.demo.service.proxy.ProxyService;
import com.test.demo.util.RestRequestClient;

import static com.test.demo.service.proxy.impl.ProxyPool.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import static com.test.demo.redis.RedisKeyUtil.*;
import static com.test.demo.service.proxy.impl.ProxyPool.*;

/**
 * 代理job
 * @author dell
 */
@Component
public class ProxyJob {
	
	/**
	 * 日志文件
	 */
	private static final Logger logger =LoggerFactory.getLogger(ProxyJob.class);
	
	/**
	 * 代理service
	 */
	@Autowired
	private ProxyService   proxyService;
	
	
	/**
	 * 执行代理
	 * 1. 首先去 查看redis中的 查询 是否在列表中 有 数据  没有数据需要初始化代理ip   不使用代理 开启一个线程 异步操作
	 * 2. 从redis 中取出一个ip  根据时间去查询 是否要 更新
	 *    需要更新的话用 ip池里面的数据 去 代理页面抓取ip 然后 放入到池子中
	 * 3.   如果是空的话 那么 是用本地ip 去采集
	 * 4. 否则 应用代理ip 去采集  
	 */
	public void runProxy() throws Exception{
		
		
		
		ProxyBean  proxy   =proxyService.getProxyBean(REDIS_PROXY_IP_LIST);
		List<ProxyBean>  beans = new ArrayList();
		RestRequestClient client = new RestRequestClient() ;
		if(proxy == null) {
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
				String result = client.restSubmitObject(entry.getKey(), proxy.getIp(),String.valueOf(proxy.getPort()) , "");
				ProxyPageParser parse = (ProxyPageParser) entry.getValue().newInstance() ;
				List<ProxyBean> proxyBeans = parse.parse(result);
				beans.addAll(proxyBeans);
			}
			
		}

		//执行入库redis中
		List<String> values  =	JSON.parseArray(JSON.toJSONString(beans), String.class);
		proxyService.addProxyBeans(REDIS_PROXY_IP_LIST, values);
		
		
	}
	
}
