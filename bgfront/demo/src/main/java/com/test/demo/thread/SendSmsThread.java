package com.test.demo.thread;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.test.demo.bean.common.SmsBean;
import com.test.demo.redis.RedisDao;
import com.test.demo.sms.SmsSend;

import static com.test.demo.redis.RedisKeyPre.*;
/**
 * 发送短信 线程
 * @author ASUS
 * 创建时间  2018年3月10日 下午8:42:52
 *
 */
@Component
public class SendSmsThread  extends Thread{

	private static final Logger logger =LoggerFactory.getLogger(SendSmsThread.class);
	
	@Autowired
	RedisDao  redisDao;
	
	@Autowired
	SmsSend  smsSend ;
	
	@Override
	public void run() {
		
		while (true){
			try {
				sendSms();
				Thread.sleep(1);
			} catch (Exception e) {
				logger.error("睡眠异常",e);
			}
		}
	}
	
	/**
	 * 发送短信
	 * 1 从 redis 中读取 消息内容 。
	 * 2. 根据消息内容 发送 短信 
	 * 3. 失败 发入到失败队列中。 暂时 只记录 失败信息  不做处理
	 * @author ASUS
	 * 创建时间  2018年3月10日 下午8:51:51
	 */
	public void sendSms() {
		String  smsContent =redisDao.brpop(SMS_SEND_CONTENT);
		
		if(StringUtils.isNotEmpty(smsContent)) {
			SmsBean  smsBean = JSON.parseObject(smsContent,SmsBean.class);
			boolean  result  =  smsSend.sendSms(smsBean); 
		}

		
		
	}
}
