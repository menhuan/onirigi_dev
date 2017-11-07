package manage.thy.model.wechat.event;

/**
 * 扫描带参数的二维码事件
 * @author dell
 */
public class QRCodeEvent extends BaseWeChatEvent {

	/**
	 * 事件Key值
	 */
	private String EventKey;
	
	/**
	 * 用于换取二维码图片
	 */
	private String Ticket;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}

	
}
