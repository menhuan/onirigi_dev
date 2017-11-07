package manage.thy.model.wechat.receive;

/**
 * 微信基类消息
 * @author dell
 *
 */
public class BaseWeChatMessage {
	
	/**
	 * 开发者微信号  。。消息发送给谁
	 */
	private String ToUserName ;
	
	/**
	 * 发送方的账号 openId  确定之后可以更改熟悉的代码
	 */
	private String FromUserName ;
	
	/**
	 * 消息创建时间
	 */
	private long CreateTime ;

	/**
	 * 消息类型（text/image /location/link）
	 */
	private String MsgType ;

	/**
	 * 消息id  64位整型
	 */
	private long MsgId ;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}

	
	
}
