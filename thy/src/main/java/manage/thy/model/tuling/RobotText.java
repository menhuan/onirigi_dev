package manage.thy.model.tuling;

import java.io.UnsupportedEncodingException;

/**
 * 图灵机器人回复文本信息
 * @author dell
 *
 */
public class RobotText  extends RobotBase{

	/**
	 * 回复的文本内容
	 */
	private String text;

	public String getText() {
		return text;
	}

	/**
	 * 文本这里是坑  在访问 图灵机器人 回复的内容的时候
	 * @author ASUS
	 * 创建时间  2017年11月16日 下午10:22:43
	 * @param text
	 * @throws Exception
	 */
	public void setText(String text) throws Exception {
		this.text =new String(text.getBytes("ISO-8859-1"),"utf-8")  ;
	}
	
	
}
