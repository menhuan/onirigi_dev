package com.test.demo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
	
	//@Scheduled(fixedRate=21600000)
	public void runLotterTask() {
		log.info("任务开始执行了"+System.currentTimeMillis());
		marketRealInfoJob.execute();
	}
	
}
