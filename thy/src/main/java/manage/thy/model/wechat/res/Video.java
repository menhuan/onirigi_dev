package manage.thy.model.wechat.res;

/**
 * 视频消息
 * @author dell
 *
 */
public class Video  {

	/**
	 * 媒体id
	 */
	private String mediaId;

	/**
	 * 语音格式
	 */
	private String thumbMediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	
}
