package com.test.demo.thread;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.test.demo.bean.common.EmailBean;
import com.test.demo.bean.common.EmailContentBean;
import com.test.demo.redis.RedisDao;
import com.test.demo.util.MailSenderUtil;

import static com.test.demo.redis.RedisKeyPre.*;

/**
 * 不停的监控 发送邮件 从
 * @author ASUS
 * 创建时间  2018年3月4日 下午9:39:48
 *
 */
@Component
public class SendEmailThread extends  Thread{
	
	/**
	 * 日志
	 */
	private static final Logger  logger  =LoggerFactory.getLogger(SendEmailThread.class);
	
	@Autowired
	RedisDao    redisDao;
	
	@Autowired
	MailSenderUtil   mailSenderUtil ;
	
	

	/**
	 * run方法
	 *
	 * @author ASUS
	 * 创建时间  2018年3月4日 下午9:42:03
	 */
	@Override
	public void run() {
		try {
			while(true) {
				sendEmail();
				Thread.sleep(1);
			}
		} catch (Exception e) {
			logger.error("出现异常");
		}
		
	}
	
	
	/**
	 * 循环监听 并且发送邮件
	 * @author ASUS
	 * 创建时间  2018年3月4日 下午9:46:25
	 */
	public void  sendEmail() {
		
	     String content =  redisDao.brpop(EMAIL_SEND_CONTEN);
	     logger.info("邮箱内容：" + content);
	     if(StringUtils.isNotBlank(content)) {
	    	 long emailSize = redisDao.pollListSize(EMAIL_ADDRESS_LIST);
	    	 boolean  result = false ;
	    	 for(int index = 0 ; index < emailSize ;index ++) {
	    		 mailSenderUtil.setMailSender();
	    		 EmailContentBean  contentBean = JSON.parseObject(content, EmailContentBean.class);
	    		 result =  mailSenderUtil.SendMailText(contentBean.getToEmail(), contentBean.getSubject(), contentBean.getContent());
	    		 if(result) {
	    			 logger.info("发送邮件成功,收件人 ：[{}]" +contentBean.getToEmail() );
	    			 break ;
	    		 }
	    	 }
	    	 
	     }
	     
	     
	     
	     
	     
	
	}
	
}
