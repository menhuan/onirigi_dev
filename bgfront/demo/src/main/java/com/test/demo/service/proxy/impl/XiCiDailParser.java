package com.test.demo.service.proxy.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.test.demo.bean.proxy.Proxy;
import com.test.demo.service.proxy.ProxyPageParser;

/**
 * 解析这个代理网站的ip 
 * @author dell
 */
public class XiCiDailParser implements ProxyPageParser {

	@Override
	public List<Proxy> parse(String html) {
		Document document = Jsoup.parse(html);
		Elements elements = document.select("table[id=ip_list] tr[class]");
		List<Proxy> proxyList = new ArrayList<>(elements.size());
	
		for(Element element : elements){
			String ip = element.select("td:eq(1)").first().text();
			String port = element.select("td:eq(1)").first().text();
			String isAnonymous = element.select("td:eq(4)").first().text();
			
			if(!isOpenProxy || isAnonymous.contains("匿")){
				proxyList.add(new Proxy(ip, Integer.parseInt(port), 1000l)) ;
			}
		}
		return proxyList;
	}

}
