package com.test.demo.job;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.test.demo.base.HuoBiUrl;
import com.test.demo.util.RestRequestClient;

/**
 * 火币的pro
 * @author ASUS
 * 创建时间  2018年3月17日 下午3:06:01
 *
 */
@Component
public class HuoBTestJob {

	
	
	/**
	 * 一个方法 用来执行   执行测试方案 是否能通过 火币的网api的访问
	 * 尝试来获取 火币网的 k线数据
	 * @author ASUS
	 * 创建时间  2018年3月17日 下午3:06:40
	 * @throws Exception
	 */
	public void exec() throws Exception {
		RestRequestClient  rest = new RestRequestClient();
		String  url = HuoBiUrl.BASE_URL_PRO_MARKET_CHINA + HuoBiUrl.MARKET_HISTORY_KLINE ;
		Map<String,String> map = new HashMap<String,String>();
		map.put("symbol", "ethbtc");
		map.put("period", "1min");
		map.put("size", "150");
//		url = url +"?" + "symbol=" + "cnneth&period=1min&size=150";
		System.out.println(url);
		String content = rest.restGetHuoBi(url, map);
		System.out.println("接收到的内容："+content);
	}
	
}
