package com.test.demo.util;

import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sun.mail.util.MailSSLSocketFactory;
import com.test.demo.bean.common.EmailBean;
import com.test.demo.redis.RedisDao;
import static com.test.demo.redis.RedisKeyPre.*;


/**
 * 邮件发送 功能  具体实现可以参考 饭饭里面那个 轮训的方式
 * @author ASUS
 * 创建时间  2017年9月17日 下午8:14:29
 *
 */
@Component
public class MailSenderUtil {

	/**
	 * 日志组件
	 */
	private static final Logger Logger =LoggerFactory.getLogger(MailSenderUtil.class);
	
	private JavaMailSenderImpl  mailSender;
	
	@Autowired
	private RedisDao  redisDao;
	
	
	/**
	 * 在这里可以设置 用于发送邮件的地址 不过 在这里还可以从redis 中拿到或者从数据库中设计 
	 * 方便实现轮训的功能 防止邮箱被黑不能发送,还有一点是企业邮箱跟普通的邮箱设置不同， 需要添加证书的问题
	 * 
	 * 
	 * 
	 * @author ASUS
	 * 创建时间  2017年9月17日 下午8:16:32
	 */
	public void setMailSender()  {
		mailSender = new JavaMailSenderImpl();
		try {
			String   emailAddress = redisDao.pollAndPush(EMAIL_ADDRESS_LIST);
			EmailBean  emailBean  = JSON.parseObject(emailAddress, EmailBean.class);
			/**
			 * 设置发送的邮箱地址  可以用自己的
			 */
			mailSender.setUsername(emailBean.getAccount());
			/**
			 * 密码 可以从redis 中读取到
			 */
			mailSender.setPassword(emailBean.getPassword());
			mailSender.setHost("stmp.examil.qq.com");
			
			mailSender.setPort(465);
			mailSender.setProtocol("smtps");
			mailSender.setDefaultEncoding("UTF-8");
			Properties  javaMailProperties=new Properties();
			javaMailProperties.put("mail.stmp.ssl.enable", true);
			mailSender.setJavaMailProperties(javaMailProperties);
		} catch (Exception e) {
			Logger.error("设置email条件出现错误" ,e);
		}
		
		
	}
	
	
	/**
	 * 设置安全通道
	 * @author ASUS
	 * 创建时间  2018年3月4日 下午9:35:48
	 * @return
	 */
	public  Properties  getProperties(){
		 Properties prop = new Properties();
		  //开启安全协议
	        MailSSLSocketFactory sf = null;
	        try {
	            sf = new MailSSLSocketFactory();
	            sf.setTrustAllHosts(true);
	        } catch (GeneralSecurityException e) {
	        	Logger.error("开启ssl协议出错",e);
	        }
	       prop.put("mail.smtp.ssl.socketFactory", sf);
		return prop;
	}
	
	
	/**
	 * 发送邮件 ，这里不操作 填充内容。 直接 在调用的时候填充好 然后 直接发送就行
	 * @author ASUS
	 * 创建时间  2017年9月17日 下午8:31:43
	 * @param toMail  给谁发送
	 * @param subject  主题
	 * @param context  内容
	 * @return
	 */
	public boolean SendMailText(String toMail,String subject,String context) {
		try {
			String nick =MimeUtility.encodeText("");
			InternetAddress  from  =new InternetAddress(nick+"<ruiqi@study.com>" );
			MimeMessage  mimeMessage =mailSender.createMimeMessage();
			MimeMessageHelper  helper= new MimeMessageHelper(mimeMessage);
			/** 这里可以 直接  从传输过来的内容  然后 迭代 然后 我这直接发送内容   可以直接替换 或者写一个公用的方法 */	
			String result =context  ;
			helper.setTo(toMail);
			helper.setFrom(from);
			helper.setSubject(subject);
			helper.setText(result,true);
			mailSender.send(mimeMessage);
			return true;
			
		} catch (Exception e) {
			Logger.error("发送邮件失败",e);
			return false;
		}
	}

}
