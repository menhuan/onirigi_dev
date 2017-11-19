package com.test.demo.bean;

import com.test.demo.bean.proxy.ProxyBean;

public class PageBean {
	
	/**
	 * 访问的链接
	 */
	private String url ;

	/**
	 * 访问的结果
	 */
	private String result ;

	/**
	 * 结果code
	 */
	private String code;
	
	/**
	 * 代理
	 */
	private ProxyBean  proxy;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ProxyBean getProxy() {
		return proxy;
	}

	public void setProxy(ProxyBean proxy) {
		this.proxy = proxy;
	}
	
	
}
