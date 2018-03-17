package manage.thy.model.imooc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import manage.thy.model.PageBean;

@JsonInclude(Include.NON_NULL)
public class AdBean extends PageBean {

	/**
	 * 主键id
	 */
	private Long id;

	/**
	 * 广告的标题
	 */
	private String title;

	/**
	 * 图片的文件名字
	 */
	private String imgFileName;

	/**
	 * 链接
	 */
	private String link;

	/**
	 * 宽度
	 */
	private Long weight;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

}
