package com.test.demo.service.proxy.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.test.demo.bean.proxy.Direct;
import com.test.demo.bean.proxy.Proxy;

import static com.test.demo.util.ConstantsUtil.*;

/**
 * 代理池
 * @author dell
 */
public class ProxyPool {

	public static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	public static final Set<Proxy> proxySet = new  HashSet<Proxy>();
	
	/**
	 * 代理池延迟队列
	 */
	public final static DelayQueue<Proxy> proxyQueue = new DelayQueue<>();
	public final static Map<String ,Class> proxyMap = new  HashMap<String , Class>();
	
	
	static {
		int pages =10 ;
		for(int i = 1 ; i < pages ; i++){
			proxyMap.put("http://www.xicidaili.com/wt/" + i + ".html", XiCiDailParser.class);
		}
		proxyQueue.add(new Direct(TIME_INTERVAL));
	}
}
