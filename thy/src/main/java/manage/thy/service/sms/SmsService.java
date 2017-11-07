package manage.thy.service.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import manage.thy.base.BaseConfig;
import manage.thy.util.SpringBeanUtil;


/**
 * 发送短信  
 * @author dell
 */
@Service
public class SmsService  extends BaseConfig {

	/**
	 * 日志文件
	 */
	private  static final Logger logger = LoggerFactory.getLogger(SmsService.class);
	
	 /**
	  * 先检查 下是否能或得到值 如果获得不到咋解决
	  */
	
	private static IClientProfile profile ;
	
	private static IAcsClient acsClient ;
	
	
//	static{
//		profile = DefaultProfile.getProfile("cn-hangzhou", smsAccessKeyId,
//		base.smsAccessKeySecret);
// 	    try {
//			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", base.smsProduct, base.smsDomain);
//		} catch (ClientException e) {
//			logger.error("默认短信发送内容出错",e);
//		}
//        acsClient = new DefaultAcsClient(profile);
//	}

	/**
	 * 增加配置文件
	 * @author ASUS
	 * 创建时间  2017年11月7日 下午10:55:44
	 */
	private void setConfig() {
		profile = DefaultProfile.getProfile("cn-hangzhou", smsAccessKeyId,
		smsAccessKeySecret);
 	    try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", smsProduct, smsDomain);
		} catch (ClientException e) {
			logger.error("默认短信发送内容出错",e);
		}
        acsClient = new DefaultAcsClient(profile);
	}
	
	 /**
	  * 发送短信 统一方式
	  * @return
	  * @param request  发送的请求
	  * @throws Exception
	  */
     private boolean sendSms(SendSmsRequest request) {

	   try {
		  SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
 	      if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
 	    	 //请求成功
 	       	 logger.info("发送短信成功");
 	    	 return true ;
 	      }
    	 	    
		} catch (Exception e) {
			logger.error("发送短信失败");
		}
 	   
		return false;
     }  
     
     /**
      * 
      * @param content  需要发送的内容
      * @param phone  需要发送的手机号
      * @param templateCode  短信模板Code
      * @param signName  短信签名
      * @return  发送成功与否
      * @throws Exception
      */
     public boolean sendSms (String content ,String phone ,String templateCode ,String signName) throws Exception{
    	 
    	 if(profile == null || acsClient == null) {
    		 setConfig() ;
    	 }
    	 
    	  //组装请求对象
 	     SendSmsRequest request = new SendSmsRequest();
 	     //使用post提交
 	     request.setMethod(MethodType.POST);
 	     //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
 	     request.setPhoneNumbers(phone);
 	     //必填:短信签名-可在短信控制台中找到
 	     request.setSignName(signName);
 	     //必填:短信模板-可在短信控制台中找到
 	     request.setTemplateCode(templateCode);
 	     //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
 	     //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
 	     //"{\"name\":\"Tom\", \"code\":\"123\"}"
 	     request.setTemplateParam(content);
 	     //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
 	     //request.setSmsUpExtendCode("90997");
 	     //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
 	     //request.setOutId("yourOutId");
 	     Boolean result =this.sendSms(request);
 	     return result;
     }
}

