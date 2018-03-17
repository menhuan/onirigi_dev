package com.test.demo.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.test.demo.bean.common.SmsBean;

/**
 * 短信发送实体类
 * @author ASUS
 * 创建时间  2017年10月23日 下午10:39:48
 *
 */
@Component
public class SmsSend {
  
	/**
	 * 日志组件
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SmsSend.class);
	
    final String accessKeyId = "LTAI8irYKNanPHbP11";//你的accessKeyId,参考本文档步骤2
    final String accessKeySecret = "feD9Q27N2gaUZ111VJEa2nrZBsL2wM6N";//你的accessKeySecret，参考本文档步骤2
    //初始化ascClient需要的几个参数
    final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
    final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
	/**
	 * 发送短信验证码
	 * @author ASUS
	 * 创建时间  2017年10月23日 下午10:42:14
	 * @param phone 接收者 手机号
	 * @param signName 签名
	 * @param templateCode 短信模板code
	 * @param content  发送的内容 替换 
	 * @return
	 * @throws Exception
	 */
	public boolean sendSms(SmsBean smsBean)  {
	   
	    try {
	    	//初始化ascClient,暂时不支持多region（请勿修改）
	    	 IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
	    			    accessKeySecret);
			 DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			 IAcsClient acsClient = new DefaultAcsClient(profile);
		     //组装请求对象
		     SendSmsRequest request = new SendSmsRequest();
		     //使用post提交
		     request.setMethod(MethodType.POST);
		     //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		     request.setPhoneNumbers(smsBean.getPhone());
		     //必填:短信签名-可在短信控制台中找到
		     request.setSignName(smsBean.getSignName());
		     //必填:短信模板-可在短信控制台中找到
		     request.setTemplateCode(smsBean.getTemplateCode());
		     //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		     //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		     //"{\"name\":\"Tom\", \"code\":\"123\"}"
		     request.setTemplateParam(smsBean.getContent());
		     //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		     //request.setSmsUpExtendCode("90997");
		     //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		     request.setOutId("yourOutId");
		     // 请求失败这里会抛ClientException异常
		     SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		     if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
		    	return true;
		     }else{
		    	 LOGGER.error("发送短信失败 ,错误编码[{}] ,错误内容：[{}] " + sendSmsResponse.getCode() ,sendSmsResponse.getMessage());
		    	return false;
		     }
		} catch (Exception e) {
			LOGGER.error("发送短信失败。。。。。",e);
			return false ;
		}
		   
	}
	
	


}
