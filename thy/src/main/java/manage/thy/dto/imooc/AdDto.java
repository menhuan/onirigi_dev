package manage.thy.dto.imooc;

import org.springframework.web.multipart.MultipartFile;

import manage.thy.model.imooc.AdBean;

public class AdDto extends AdBean {

	/**
	 * 图片
	 */
	private String  img ;
	
	/**
	 * 文件
	 */
	private MultipartFile  imgFile ;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public MultipartFile getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}
	
	
}
