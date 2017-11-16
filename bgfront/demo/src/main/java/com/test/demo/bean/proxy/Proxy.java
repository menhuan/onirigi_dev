package com.test.demo.bean.proxy;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 任务 代理 
 * @author dell
 *
 */
public class Proxy implements Delayed   {

	/**
	 * 任务时间间隔 ， 单位 ms  
	 */
	private long timeInterval ;

	/**
	 * id 地址
	 */
	private String ip ;
	
	/**
	 * 端口号
	 */
	private int port ;

	
	private boolean availableFlag;

	
	private boolean annoymousFlag;

	/**
	 * 最后一次请求 成功时间
	 */
	private long lastSuccessFulTime ; 
	
	/**
	 * 请求成功 总耗时
	 */
	private long successFulTotalTime ;

	/**
	 * 请求失败次数
	 */
	private int failureTimes ;
	
	/**
	 * 请求成功次数
	 */
	private int successFulTimes ;

	/**
	 * 请求成功 平均耗时
	 */
	private double successFulAverageTime ;
	
	public Proxy(String ip , int port , long timeInterval){
		this.ip = ip ;
		this.port = port ;
		this.timeInterval = timeInterval ;
		this.timeInterval = TimeUnit.NANOSECONDS.convert(timeInterval, TimeUnit.MILLISECONDS) 
				+ System.nanoTime() ;
	}
	

	public long getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(long timeInterval) {
		this.timeInterval = TimeUnit.NANOSECONDS.convert(timeInterval,
				TimeUnit.MILLISECONDS) + System.nanoTime();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isAvailableFlag() {
		return availableFlag;
	}

	public void setAvailableFlag(boolean availableFlag) {
		this.availableFlag = availableFlag;
	}

	public boolean isAnnoymousFlag() {
		return annoymousFlag;
	}

	public void setAnnoymousFlag(boolean annoymousFlag) {
		this.annoymousFlag = annoymousFlag;
	}

	public long getLastSuccessFulTime() {
		return lastSuccessFulTime;
	}

	public void setLastSuccessFulTime(long lastSuccessFulTime) {
		this.lastSuccessFulTime = lastSuccessFulTime;
	}

	public long getSuccessFulTotalTime() {
		return successFulTotalTime;
	}

	public void setSuccessFulTotalTime(long successFulTotalTime) {
		this.successFulTotalTime = successFulTotalTime;
	}

	public int getFailureTimes() {
		return failureTimes;
	}

	public void setFailureTimes(int failureTimes) {
		this.failureTimes = failureTimes;
	}

	public int getSuccessFulTimes() {
		return successFulTimes;
	}

	public void setSuccessFulTimes(int successFulTimes) {
		this.successFulTimes = successFulTimes;
	}

	public double getSuccessFulAverageTime() {
		return successFulAverageTime;
	}

	public void setSuccessFulAverageTime(double successFulAverageTime) {
		this.successFulAverageTime = successFulAverageTime;
	}

	/**
	 * 进行对比 延迟
	 */
	@Override
	public int compareTo(Delayed o) {
		
		Proxy element = (Proxy) o;
		if(successFulAverageTime == 0.0d ||
				element.successFulAverageTime == 0.0d){
			return 0;
		}
		return successFulAverageTime > element.successFulAverageTime ? 1 
				: (successFulAverageTime < element.successFulAverageTime ? -1 : 0);
	}
	
	/**
	 * 获得时间差  单位ns 貌似
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(timeInterval-System.nanoTime(),TimeUnit.NANOSECONDS );
	} 

	
}
