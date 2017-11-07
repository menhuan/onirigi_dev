package manage.thy.model.wechat.receive;

/**
 * 语音消息
 * @author ASUS
 * 创建时间  2017年11月7日 下午9:41:34
 *
 */
public class VoiceMessage extends BaseWeChatMessage{

	/**
	 * 媒体id
	 */
	private String MediaId;
	
	/**
	 * 语音格式
	 */
	private String Format;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}
	
	
}
