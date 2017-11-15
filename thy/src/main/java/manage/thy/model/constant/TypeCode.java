package manage.thy.model.constant;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 定义通用的类型枚举类型
 * @author dell
 *
 */
public enum TypeCode {

	/*********************图灵机器人类型*******************/
	ROBOTTYPETEXT("100000","文本"),
	ROBOTTYPENEWS("302000","新闻类"),
	ROBOTTYPEMENU("308000","菜谱类"),
	ROBOTTYPESING("313000","儿童版儿歌类"),
	ROBOTTYPEPOEM("314000","儿童版诗词类"),
	ROBOTTYPEURL("200000","连接类"),
	ROBOTTYPEERRORKEY("40001","参数key错误"),
	ROBOTTYPEERRORINFO("40002","请求内容info为空"),
	ROBOTTYPEERRORNUM("40004","当天请求次数已使用完"),
	ROBOTTYPEERRORDATA("40007","数据格式异常"),
	
	
	;
	String code = "" ;
	
	String type = "" ;

	private TypeCode(String code, String type) {
		this.code = code;
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public static Map<String , TypeCode> enumMap = new HashMap<String , TypeCode>();
	
	static {
		EnumSet<TypeCode> enumSet = EnumSet.allOf(TypeCode.class);
		for(TypeCode code : enumSet){
			if (!enumMap.containsKey(code.getCode())){
				enumMap.put(code.getCode(), code);
			}
		}
	}
	
	
	
	
}
