package manage.thy.service.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import jnr.ffi.types.key_t;
import manage.thy.base.BaseConfig;
import manage.thy.model.tuling.RobotBase;
import manage.thy.util.BaseUtil;
import manage.thy.util.RestRequestClient;

/**
 * robotService
 * @author ASUS
 * 创建时间  2017年11月14日 下午10:05:05
 *
 */
@Service
public class RobotService  extends BaseConfig{

	/**
	 * 日志文件
	 */
	private static final Logger  logger = LoggerFactory.getLogger(RobotService.class);

	private String key ;
	

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
		RobotBase base = JSON.parseObject(result, RobotBase.class);
		
		
		return null ;
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

}
