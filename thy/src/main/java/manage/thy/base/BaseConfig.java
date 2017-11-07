package manage.thy.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 配置
 * @author dell
 *
 */
public class BaseConfig {

	/**
	 * 短信api名称
	 */
	@Value("${sms.product}")
	public String  smsProduct ;

	/**
	 * 短信api产品域名
	 */
	@Value("${sms.domain}")
	public String smsDomain;

	/**
	 * 
	 */
	@Value("${sms.accessKeyId}")
	public String smsAccessKeyId;

	/**
	 * 云秘钥 
	 */
	@Value("${sms.accessKeySecret}")
	public String smsAccessKeySecret ;

	/**
	 * 发送地
	 */
	@Value("${sms.address.send}")
	public String smsAddressSend ;
	
	
	
}
