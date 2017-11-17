package com.test.demo.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
	 * 执行代理
	 * 1. 首先去 查看redis中的 查询 是否在列表中 有 数据  没有数据需要初始化代理ip   不使用代理 开启一个线程 异步操作
	 * 2. 从redis 中取出一个ip  根据时间去查询 是否要 更新
	 *    需要更新的话用 ip池里面的数据 去 代理页面抓取ip 然后 放入到池子中
	 * 3.     
	 * 
	 */
	public void runProxy() throws Exception{
		   
		
		
	}
	
}
