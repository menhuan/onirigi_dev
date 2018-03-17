package manage.thy.controller.wechat;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

import manage.thy.model.wechat.pay.PayWeBean;
import manage.thy.service.WeChatService;
import manage.thy.util.BaseUtil;
import manage.thy.util.MessageUtil;
import manage.thy.util.RestHttpClient;
import manage.thy.util.RestRequestClient;
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
	 * 微信商户号
	 */
	private static final String MCHID = "1487076782";
	
	/**
	 * 微信回调地址
	 */
	private static final String NOTIFYURL = " " ;
	
	/**
	 * 微信交易类型
	 */
	private static final String TRADETYPE = "web" ;
	
	/**
	 * 微信APIKEY
	 */
	private static final String APIKEY = "wx61576d34a3193a4d";
	
	
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
	
	
	/**
	 * 统一下单
	 * @return
	 * @throws Exception  
	 */
	@RequestMapping(value = "/uniformorder" ,method = RequestMethod.POST )
	public String  uniformorder(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		try {
			//app id 
			String  appid = request.getParameter("appid");
			/**
			 * 用户访问令牌
			 */
			String  accessToken = request.getParameter("accessToken");
			/**
			 * 订单编号
			 */
			String  orderNum = request.getParameter("orderNum");
			/**
			 * 消费金额	
			 */
			String  money = request.getParameter("money");
		
			/**
			 * 消费金额
			 */
			String subject = request.getParameter("subject") ;
			
			if(StringUtils.isEmpty(appid)){
				return "参数：appid 为空";
			}
			
			if(StringUtils.isEmpty(accessToken)){
				return "用户访问令牌为空";
			}
			
			if(StringUtils.isEmpty(money)){
				return "消费金额为空";
			}
			
			if(StringUtils.isEmpty(subject)){
				return "主题为空" ;
			}
			
			if(StringUtils.isEmpty(orderNum)){
				return "订单号为空";
			}
			
			
			SortedMap<String, Object> result =new TreeMap<String ,Object>();
			PayWeBean  bean = new PayWeBean();
			bean.setAppid(appid);
			bean.setMch_id(MCHID);
			bean.setNonce_str("123123");
			bean.setBody(subject);
			bean.setOut_trade_no(orderNum);
			bean.setTotal_fee(Integer.parseInt(BigDecimal.valueOf(Long.valueOf(money))
					.multiply(new BigDecimal(100)).toString()) );
			bean.setSpbill_create_ip("127.0.0.1");
			bean.setNotity_url(NOTIFYURL);  //回调地址
			bean.setTrade_type(TRADETYPE);  //交易类型
			String sign = SignUtil.createMD5Sign(bean);
			bean.setSign(sign);

			String xml = MessageUtil.messageToXml(bean);
			
			// 提交到微信的统一下单接口上
			RestRequestClient  rest  =new RestRequestClient() ;
			String content = rest.restSubmitString(BaseUtil.WECHAT_UNIFORMORDER, xml) ;
			
			boolean success=weChatService.checkWeChatSuccess(content);
			
			if(success) {
				JSONObject	object = MessageUtil.parseXMLtoJson(content);
				JSONObject  contentXML = object.getJSONObject("xml");
				String prepayId =contentXML.getString("prepay_id");
				
				return "成功";
			}else {
				return "FAIL";
			}
			
		} catch (Exception e) {
			logger.error("统一下单出现问题" , e);
		}
		
		return  null ;
	}
	
	/**
	 * 微信订单 回调接口
	 * @author ASUS
	 * 创建时间  2017年11月13日 下午10:02:05
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/notify" ,method = RequestMethod.POST)
	public void notify(HttpServletRequest request ,HttpServletResponse response) {
		
		try {
		  request.setCharacterEncoding("UTF-8");    
          response.setCharacterEncoding("UTF-8");    
          response.setContentType("text/html;charset=UTF-8");    
          response.setHeader("Access-Control-Allow-Origin", "*");
          
         
          String content=weChatService.getContentFromStream(request);//xml数据    
		  boolean isSuccess = weChatService.checkWeChatSuccess(content);
		  if(isSuccess) {
			  String result = weChatService.parse(content, "out_trade_no");
			  System.out.println("成果的结果订单号是："+result);
			  response.getWriter().write(weChatService.setXml("SUCCESS", "OK"));
		  }else {
			System.out.println("失败");
		  }
		
		} catch (Exception e) {
			logger.error("微信订单回调 失败。。",e);
		}
		
	}
	
//	@RequestMapping(value = "/notify" ,method = RequestMethod.POST)
//	public String notify() {
	
	
}
