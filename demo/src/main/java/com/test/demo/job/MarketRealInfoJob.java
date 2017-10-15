package com.test.demo.job;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.test.demo.base.BaseCofig;
import com.test.demo.base.BaseUrl;
import com.test.demo.bean.MarketRealInfoBean;
import com.test.demo.service.MarketRealInfoService;
import com.test.demo.util.DateUtil;
import com.test.demo.util.RestHttpClient;

/**
 * 任务导入近六小时内的数据
 * @author ASUS
 * 创建时间  2017年8月27日 下午8:09:08
 *
 */
@Component
public class MarketRealInfoJob {

	private Logger Logger =LoggerFactory.getLogger(MarketRealInfoJob.class);
	
	@Autowired
	private MarketRealInfoService  marketRealInfoService;
	
	
	/**
	 * 执行任务主体
	 * 首先查询数据库中的最大时间的数据 
	 * 然后根据查询回来的数据时间找到第一条的数据进行对比 如果小于从数据库中查找出来的那么等待一分钟后重新执行检查 设置一个变量ture false  使其陷入循环中然后 执行
	 * 最后批量入库
	 * @author ASUS
	 * 创建时间  2017年8月27日 下午8:10:34
	 */
	@SuppressWarnings({ "all" })
	public void execute() {
		
		boolean isCycle =true;
		
		RestHttpClient  rest=new RestHttpClient();
		try {
			Map map=marketRealInfoService.getRealInfo();
			while(isCycle) {
				
				Map resultMap =rest.doGetMap(BaseUrl.YT_MARKET_REAL_INFO);
				String code = ObjectUtils.toString(resultMap.get("code"));
				try {
					if(code.equals(BaseCofig.YT_SUCCESS_CODE)) {
						Map dataMap=(Map) resultMap.get("data");
						List list=(List)dataMap.get("points");
						List listBean= new ArrayList<MarketRealInfoBean>(); 
						for(int index=0;index<list.size();index++) {
							
							Map listMap =JSONObject.parseObject(ObjectUtils.toString(list.get(index)), Map.class );
							MarketRealInfoBean bean= new MarketRealInfoBean();
							bean.setPrice(new BigDecimal(ObjectUtils.toString(listMap.get("price"))));
							String timeString=ObjectUtils.toString(listMap.get("time"));
							bean.setTime(DateUtil.getDateFromYYMMDDHHmmss(timeString) );
							bean.setTradeTime(ObjectUtils.toString(listMap.get("ticks")));
							bean.setTradeCount(new BigDecimal(listMap.get("vol").toString()) );
							listBean.add(bean);
						}
						
						
						if(map==null||map.isEmpty()) {
							marketRealInfoService.insertRealInfo(listBean);
							isCycle=false;
						}else {
							Long maxTradeTime=Long.valueOf(map.get("tradeTime").toString())  ;
							Map pointMap=(Map) list.get(0);
							Long tradeTime= Long.valueOf(ObjectUtils.toString(pointMap.get("ticks")));
							
							if(tradeTime<=maxTradeTime) {
								Thread.sleep(1000*30);
								continue;
							}
							marketRealInfoService.insertRealInfo(listBean);
							isCycle=false;
						}
					}
				} catch (Exception e) {
					Logger.error("执行错误。。。",e);
					Thread.sleep(1000*30);
				}
				
			}
			
		} catch (Exception e) {
			Logger.error("批量执行最近6个小时内容出现错误",e);
			e.printStackTrace();
		}
		
	}
	
}
