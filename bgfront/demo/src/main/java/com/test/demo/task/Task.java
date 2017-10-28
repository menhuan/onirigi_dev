package com.test.demo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.test.demo.job.KrHtmlJob;
import com.test.demo.job.LotterDataJob;
import com.test.demo.job.MarketRealInfoJob;


/**
 * 任务service
 * @author dell
 *
 */

@Service
public class Task {
	
	private static final Logger log = LoggerFactory.getLogger(Task.class);


	@Autowired
	LotterDataJob  lotterDataJob;
	
	@Autowired
	MarketRealInfoJob  marketRealInfoJob;
	
	@Autowired
	KrHtmlJob  krHtmlJob;
	
	//@Scheduled(fixedRate=21600000)
	public void runLotterTask() {
		
		log.info("任务开始执行了"+System.currentTimeMillis());
		marketRealInfoJob.execute();
	}
	
	/**
	 * 运行爬虫信息
	 * @author ASUS
	 * 创建时间  2017年10月23日 下午8:51:52
	 */
//	@Scheduled(fixedRate=12*60*60*1000 )
	public void runKrParse() {
		try {
		//	krHtmlJob.parseHtml();
		} catch (Exception e) {
			log.error("爬虫失败",e);
		}
		
	}
	
}