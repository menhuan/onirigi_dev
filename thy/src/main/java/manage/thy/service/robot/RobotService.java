package manage.thy.service.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import manage.thy.base.BaseConfig;
import manage.thy.model.constant.TypeCode;
import manage.thy.model.tuling.RobotBase;
import manage.thy.model.tuling.RobotLink;
import manage.thy.model.tuling.RobotMenu;
import manage.thy.model.tuling.RobotNews;
import manage.thy.model.tuling.RobotText;
import manage.thy.util.BaseUtil;
import manage.thy.util.RestRequestClient;

/**
 * robotService
 * @author ASUS
 * 创建时间  2017年11月14日 下午10:05:05
 * @param <T>
 *
 */
@Service
public class RobotService<T>  extends BaseConfig{

	/**
	 * 日志文件
	 */
	private static final Logger  logger = LoggerFactory.getLogger(RobotService.class);


	/**
	 * 根据传输过来的内容  图灵机器人自动回复
	 * @author ASUS
	 * 创建时间  2017年11月14日 下午10:28:35
	 * @param content  用户传输的内容
	 * @return
	 */
	public String getResponseRobot(String content ,String userId ,String loc) {
		String  json =this.toJson(content, userId, loc);
		RestRequestClient rest =new RestRequestClient() ;
		String result = rest.restSubmitString(BaseUtil.TULING_ROBOT_API, json);
		
		return  JSON.toJSONString(this.parseContenttoBean(result));

	}	

	/**
	 * 发送的json内容
	 * @author ASUS
	 * 创建时间  2017年11月14日 下午10:33:15
	 * @param content  发送的内容
	 * @param userId  用户id 我们这里定义的
	 * @param loc  当请求地址位置是使用  不是必须的
	 * @return
	 */
	private String toJson(String content ,String userId ,String loc) {
		
		JSONObject object = new JSONObject();
		object.put("key", tulingRobotApiKey);
		object.put("info", content);
		object.put("loc", loc);
		object.put("userid", userId);
		
		return JSON.toJSONString(object);
	}

	/**
	 * 将 内容映射为不同的实体bean   
	 * 用泛型来实现
	 * @param content
	 * @return
	 */
	private <T> T parseContenttoBean (String content){
		RobotBase base = JSON.parseObject(content, RobotBase.class);
		
		String code = base.getCode() ;
		
		if(TypeCode.ROBOTTYPETEXT.getCode().equals(code)){
			RobotText text = JSON.parseObject(content , RobotText.class);
			return  (T) text ;
		}
		
		if(TypeCode.ROBOTTYPEMENU.getCode().equals(code)){
			RobotMenu menu = JSON.parseObject(content , RobotMenu.class);
			return  (T) menu ;
		}
		
		if(TypeCode.ROBOTTYPENEWS.getCode().equals(code)){
			RobotNews news = JSON.parseObject(content , RobotNews.class);
			return  (T) news ;
		}
		
		if(TypeCode.ROBOTTYPEURL.getCode().equals(code)){
			RobotLink link = JSON.parseObject(content , RobotLink.class);
			return  (T) link ;
		}
		
		if(TypeCode.ROBOTTYPEERRORKEY.getCode().equals(code)){
			return (T) TypeCode.enumMap.get(code); 
		}
		
		if(TypeCode.ROBOTTYPEERRORINFO.getCode().equals(code)){
			return (T) TypeCode.enumMap.get(code); 
		}
		
		if(TypeCode.ROBOTTYPEERRORDATA.getCode().equals(code)){
			return (T) TypeCode.enumMap.get(code); 
		}
		
		if(TypeCode.ROBOTTYPEERRORNUM.getCode().equals(code)){
			return (T) TypeCode.enumMap.get(code); 
		}
		
		return (T) "";
		
	}
}
