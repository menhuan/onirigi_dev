package com.test.demo.bean.proxy;

public class Direct extends ProxyBean {

	public Direct(String ip, int port, long timeInterval) {
		super(ip, port, timeInterval);
	}

	public Direct(long delayTime){
		this("", 0 , delayTime );
	}
}
