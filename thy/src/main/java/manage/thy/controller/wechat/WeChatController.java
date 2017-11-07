package manage.thy.controller.wechat;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import manage.thy.service.WeChatService;
import manage.thy.util.SignUtil;


/**
 * 微信Controller
 * @author dell
 *
 */
@Controller
@RequestMapping("weChat")
public class WeChatController {

	/**
	 * 日志组件
	 */
	private static final Logger logger = LoggerFactory.getLogger(WeChatController.class);
	
	@Autowired
	private WeChatService  weChatService;
	
	/**
	 * 连接微信
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "connect",method = {RequestMethod.GET,RequestMethod.POST})
	public void connectWeChat(HttpServletRequest request,HttpServletResponse response){
		try {
			 // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
	        request.setCharacterEncoding("UTF-8");  //微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
	        response.setCharacterEncoding("UTF-8"); //在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
	    
	        boolean isGet = request.getMethod().toLowerCase().equals("get"); 
	        PrintWriter out = response.getWriter();
	        
	        if (isGet) {
	        	logger.info("开始验证");
	        	// 微信加密签名   ,signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	            String signature = request.getParameter("signature");
	            String timestamp = request.getParameter("timestamp");// 时间戳  
	            String nonce = request.getParameter("nonce");// 随机数  
	            
	            
	            String echostr = request.getParameter("echostr");//随机字符串  
	            
	            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败 
	            String  DNBX_TOKEN = "123123";
	            if (SignUtil.checkSign(DNBX_TOKEN, signature, timestamp, nonce)) {  
	        
	                response.getWriter().write(echostr);  
	            } else {  
	            	logger.info("验证失败");
	            }
	        }else{
	            String respMessage = "异常消息！";
	            
	            try {
	            	respMessage = weChatService.weChatPost(request);
	                out.write(respMessage);
	            	logger.info("respMessage:"+respMessage);
	            } catch (Exception e) {
	            	logger.info("验证异常",e);
	            }
	            
	        }
        } catch (Exception e) {
        	logger.error("链接错误",e);
        }
	}
	
	
	
}
