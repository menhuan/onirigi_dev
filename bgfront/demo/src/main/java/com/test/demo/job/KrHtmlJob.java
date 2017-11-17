package com.test.demo.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.demo.service.ReptileService;
import com.test.demo.service.impl.ReptileServiceImpl;
import com.test.demo.task.JobExecuteSub;

/**
 * 定时任务 这个写完之后研究下 定时任务改造 或者用开源框架
 * @author ASUS
 * 创建时间  2017年10月23日 下午8:27:13
 *
 */
@Component
public class KrHtmlJob  implements JobExecuteSub{

	private static final Logger Logger = LoggerFactory.getLogger(KrHtmlJob.class);
	
	/**
	 * 任务执行 爬虫
	 */
	@Autowired
	private  ReptileService   reptileServiceImpl;
	
	/**
	 * 采集爬虫任务
	 * @author ASUS
	 * 创建时间  2017年10月23日 下午8:30:34
	 * @throws Exception 
	 */
	public  void parseHtml() throws Exception {
		
		reptileServiceImpl.reptileIndex();
		reptileServiceImpl.reptileHtml();
		
		
	}

	@Override
	public void run() throws Exception {
		this.parseHtml();
	}
	
}
