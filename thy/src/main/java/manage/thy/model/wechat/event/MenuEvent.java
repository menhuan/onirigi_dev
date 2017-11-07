package manage.thy.model.wechat.event;

/**
 * 自定义菜单事件
 * @author dell
 *
 */
public class MenuEvent extends BaseWeChatEvent {

	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	
}
