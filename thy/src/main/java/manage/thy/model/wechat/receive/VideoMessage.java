package manage.thy.model.wechat.receive;

/**
 * 请求消息值视频消息
 * @author ASUS
 * 创建时间  2017年11月7日 下午9:42:43
 *
 */
public class VideoMessage  extends BaseWeChatMessage{

	/**
	 * 媒体id
	 */
	private String MediaId ;
	
	/**
	 * 语音格式
	 */
	private String ThumbMediaId ;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
	
	
}
