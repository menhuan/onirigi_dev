package manage.thy.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import manage.thy.model.wechat.res.TextResMessage;
import manage.thy.util.MessageUtil;


/**
 * 微信service
 * @author ASUS
 * 创建时间  2017年10月31日 下午8:57:57
 *
 */
@Service
public class WeChatService {

	/**
	 * 日志组件
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(WeChatService.class);
	
	/**
	 * 微信post
	 * @author ASUS
	 * 创建时间  2017年10月31日 下午8:52:32
	 * @param request
	 * @return
	 */
	public String weChatPost(HttpServletRequest request) {
		String resMessage = null ;
		try {
			Map<String , String> requestMap = MessageUtil.parseXmlWeChat(request);
			
			String fromUserName = requestMap.get("FromUserName");
			String toUserName = requestMap.get("ToUserName");
			String msgType = requestMap.get("MsgType");
			String content = requestMap.get("Content");
			
			resMessage=this.replyToContentByType(msgType,fromUserName,toUserName);
			LOGGER.info("resMessage:"+resMessage);
		} catch (Exception e) {
			LOGGER.error("微信post出错",e);
		}
		return resMessage;
		
	}
	
	/**
	 * 根据类型回复内容
	 * @author ASUS
	 * 创建时间  2017年10月31日 下午9:07:39
	 * @param msgType  收到的文本内容类型
	 * @return
	 */
	public String  replyToContentByType(String msgType,String fromUserName , String toUserName) {
		
		String resMessage = null ;
		
		/**
		 * 文本消息判断
		 */
		if(MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)) {
			resMessage =this.replyToText(msgType, fromUserName , toUserName);
			return resMessage ;
		}
		
		
		return resMessage ;
	}
	
	/**
	 * 回复文本内容  怎么回复 需要定义 。 人工还是自动 还是什么
	 * @author ASUS
	 * 创建时间  2017年10月31日 下午9:16:50
	 * @return
	 */
	public String replyToText(String msgType ,String fromUserName , String toUserName ) {
		TextResMessage textResMessage  = new TextResMessage();
		textResMessage.setContent("你好 我是你的用户");
		textResMessage.setFromUserName(toUserName);
		textResMessage.setToUserName(fromUserName);
		textResMessage.setMsgType(msgType);
		textResMessage.setCreateTime(System.currentTimeMillis());
		
	
		String resMessage = MessageUtil.messageToXml(textResMessage);
		return  resMessage ;
		
	}
	
}
