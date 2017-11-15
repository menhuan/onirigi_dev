package manage.thy.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import manage.thy.model.wechat.res.TextResMessage;
import manage.thy.service.robot.RobotService;
import manage.thy.util.ConditionUtil;
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
	
	@Autowired
	private RobotService robotService;
	
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
			
			resMessage=this.replyToContentByType(msgType,fromUserName,toUserName ,content);
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
	 * @param content  对方发过来的内容
	 * @return
	 */
	public String  replyToContentByType(String msgType,String fromUserName , String toUserName
			, String content ) {
		
		String resMessage = null ;
		
		/**
		 * 文本消息判断
		 */
		if(MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)) {
			
			String result = robotService.getResponseRobot(content,fromUserName , "");
			
			resMessage =this.replyToText(msgType, fromUserName , toUserName,result);
			return resMessage ;
		}
		
		
		
		return resMessage ;
	}
	
	/**
	 * 回复文本内容  怎么回复 需要定义 。 人工还是自动 还是什么
	 * @param msgType 文本信息
	 * @param fromUserName 来自谁的信息
	 * @param toUserName 发送个谁的
	 * @param content 发送的内容
	 * @author ASUS
	 * 创建时间  2017年10月31日 下午9:16:50
	 * @return
	 */
	public String replyToText(String msgType ,String fromUserName 
			, String toUserName ,String content ) {
		TextResMessage textResMessage  = new TextResMessage();
		textResMessage.setContent(content);
		textResMessage.setFromUserName(toUserName);
		textResMessage.setToUserName(fromUserName);
		textResMessage.setMsgType(msgType);
		textResMessage.setCreateTime(System.currentTimeMillis());
	
		String resMessage = MessageUtil.messageToXml(textResMessage);
		return  resMessage ;
		
	}
	
	/**
	 * 检查统一下单是否成功
	 * @author ASUS
	 * 创建时间  2017年11月13日 下午9:42:26
	 * @return  
	 * @throws Exception 
	 */
	public boolean checkWeChatSuccess(String content) throws Exception{
		
		JSONObject	object = MessageUtil.parseXMLtoJson(content);
		JSONObject  contentXML = object.getJSONObject("xml");
		String resultCode = contentXML.getString("result_code");
		
		if(ConditionUtil.WECHAT_SUCCESS_CODE.equalsIgnoreCase(resultCode)) {
			return true;
		}else if (ConditionUtil.WECHAT_FAILE_CODE.equalsIgnoreCase(resultCode)) {
			return false;
		}
		
		return false;
		
	}
	
	/**
	 * 根据字段获得解析内容
	 * @author ASUS
	 * 创建时间  2017年11月14日 下午8:48:36
	 * @param content xml文件内容
	 * @param parse 需要得到的字段内容
	 * @return 
	 * @throws Exception
	 */
	public String parse(String content , String parse) throws Exception{
		
		JSONObject	object = MessageUtil.parseXMLtoJson(content);
		JSONObject  contentXML = object.getJSONObject("xml");
		String result = contentXML.getString(parse);
		return result;
	}
	 
	/**
	 * 从请求中获得算出的内容
	 * @author ASUS
	 * 创建时间  2017年11月14日 下午8:52:48
	 * @param request
	 * @return
	 * @throws Exception  
	 */
	public String getContentFromStream(HttpServletRequest request) throws Exception {
       InputStream in=request.getInputStream();    
       ByteArrayOutputStream out=new ByteArrayOutputStream();    
       byte[] buffer =new byte[1024];    
       int len=0;    
       while((len=in.read(buffer))!=-1){    
          out.write(buffer, 0, len);    
       }    
       out.close();    
       in.close();    
       String content=new String(out.toByteArray(),"utf-8");//xml数据    
       return content;
	}
	
	/**
	 * 返回给微信 数据
	 * @author ASUS
	 * 创建时间  2017年11月14日 下午9:23:08
	 * @param return_code
	 * @param return_msg
	 * @return
	 */
	public static String setXml(String return_code,String return_msg){    
         return "<xml><return_code><![CDATA["+return_code+"]]></return_code><return_msg><![CDATA["+return_msg+"]]></return_msg></xml>";    
	}    
}    
	
