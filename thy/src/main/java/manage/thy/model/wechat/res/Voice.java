package manage.thy.model.wechat.res;

import manage.thy.model.wechat.receive.BaseWeChatMessage;

/**
 * 语音消息实体
 * @author dell
 *
 */
public class Voice extends BaseWeChatMessage {
	
	/**
	 *  媒体ID
	 */
	private String MediaId;	
	
	/**
	 * 语音格式
	 */
	private String Format ;


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
